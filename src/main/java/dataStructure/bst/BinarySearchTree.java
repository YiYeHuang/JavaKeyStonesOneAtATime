package dataStructure.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {

    private BSTNode root = null;
    private long size;

    public BinarySearchTree() {
        this.size = 0;
    }

    public void insert(int value) {
        if (root == null) {
            root = new BSTNode(value);
            size++;
        } else {
            traceInsert(root, value);
        }
    }

    public boolean search(int value) {
        return traceSearch(value, this.root);
    }

    public boolean delete(int value) {

        if (null != this.root && this.root.value == value) {

            // Root has both child
            if (null != root.right && null != root.left) {
                BSTNode currentMaxLeft = findBiggest(this.root.left);

                // rebuild tree
                currentMaxLeft.right = this.root.right;
                this.root.right.parent = currentMaxLeft.right;
                this.root = this.root.left;
            } else if (null != root.right) {
                this.root = root.right;
            } else if (null != root.left) {
                this.root = root.left;
            } else {
                this.root = null;
            }

            size--;

            return true;
        } else {
            return traceDelete(value, this.root);
        }
    }

    public int getHeight() {
        return getCurrentheight(root);
    }

    public long getSize() {
        return this.size;
    }

    public void printTree() {
        List<List<String>> structure = levelPrint(this.root);

        for (int level = 0; level < structure.size() ; level++) {

            List<String> currentLevel = structure.get(level);
            for (int element = 0; element < currentLevel.size(); element++) {
                if (currentLevel.get(element).equalsIgnoreCase("")) {
                    System.out.print(" ");
                } else {
                    System.out.print(currentLevel.get(element));
                }
            }
            System.out.println();
        }
    }

    public Integer findMax() {
        if (null == this.root) {
            return null;
        }
        return findBiggest(this.root).value;
    }

    public Integer findMin() {
        if (null == this.root) {
            return null;
        }
        return findSmallest(this.root).value;
    }

    /**
     * private =========================================================================================================
     */

    private void levelPrint(int level, BSTNode currentRoot) {
        if (level == 1)
        {
            System.out.print(currentRoot.value + " ");
        } else
        {
            if (null != currentRoot.left)
            {
                levelPrint(level - 1, currentRoot.left);
            }
            if (null != currentRoot.right)
            {
                levelPrint(level - 1, currentRoot.right);
            }
        }
    }

    private List<List<String>> levelPrint(BSTNode inputNode) {
        List<List<String>> res = new ArrayList<>();
        if (inputNode == null) {
            return res;
        }

        int rows = getCurrentheight(inputNode);
        int cols = (int)Math.pow(2, rows) - 1;
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add("");
            }
            res.add(row);
        }

        Queue<BSTNode> queue = new LinkedList<>();
        Queue<int[]> indexQ = new LinkedList<>();
        queue.offer(root);
        indexQ.offer(new int[] { 0, cols - 1 });
        int row = -1;
        while (!queue.isEmpty()) {
            row++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BSTNode cur = queue.poll();
                int[] indices = indexQ.poll();

                if (cur == null) {
                    continue;
                }

                int left = indices[0];
                int right = indices[1];
                int mid = left + (right - left) / 2;
                res.get(row).set(mid, String.valueOf(cur.value));

                queue.offer(cur.left);
                queue.offer(cur.right);
                indexQ.offer(new int[] { left, mid - 1 });
                indexQ.offer(new int[] { mid + 1, right });
            }
        }

        return res;
    }

    private void traceInsert(BSTNode currentRoot, int value) {
        if (value > currentRoot.value) {
            if (null == currentRoot.right) {
                currentRoot.right = new BSTNode(value);
                currentRoot.right.parent = currentRoot;
                size++;
            } else {
                traceInsert(currentRoot.right, value);
            }
        } else if (value < currentRoot.value) {
            if (null == currentRoot.left) {
                currentRoot.left = new BSTNode(value);
                currentRoot.left.parent = currentRoot;
                size++;
            } else {
                traceInsert(currentRoot.left, value);
            }
        } else {
            // duplicate;
        }
    }

    private boolean traceSearch(int value, BSTNode currentRoot) {
        if (null == currentRoot) {
            return false;
        } else if (value == currentRoot.value) {
            return true;
        } else if (value > currentRoot.value) {
            return traceSearch(value, currentRoot.right);
        } else if (value < currentRoot.value) {
            return traceSearch(value, currentRoot.left);
        } else {
            return false;
        }
    }

    private boolean traceDelete(int value, BSTNode currentRoot) {
        if (value > currentRoot.value) {

            if (null != currentRoot.right) {

                BSTNode currentCheck = currentRoot.right;
                if (value == currentCheck.value) {

                    doDelete(currentRoot, currentRoot.right, true);
                    return true;
                } else {
                    return traceDelete(value, currentCheck);
                }
            } else {
                return false;
            }
        } else if (value < currentRoot.value) {
            if (null != currentRoot.left) {

                BSTNode currentCheck = currentRoot.left;
                if (value == currentCheck.value) {

                    doDelete(currentRoot, currentRoot.left, false);
                    return true;
                } else {
                    return traceDelete(value, currentCheck);
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void doDelete(BSTNode parentNode, BSTNode tobeDelete, boolean larger) {

        if ( null != tobeDelete.left && null != tobeDelete.right) {
            // find local max
            BSTNode currentMaxLeft = findBiggest(tobeDelete.left);

            // rebuild tree
            currentMaxLeft.right = tobeDelete.right;
            tobeDelete.right.parent = currentMaxLeft.right;

            if (larger) {
                parentNode.right = tobeDelete.left;
            }
            else {
                parentNode.left = tobeDelete.left;
            }

            currentMaxLeft.parent = parentNode;
            tobeDelete = null;
            size--;

        } else if (null != tobeDelete.right) {

            if (larger) {
                parentNode.right = tobeDelete.right;
                tobeDelete.right.parent = parentNode;
            }
            else {
                parentNode.left = tobeDelete.right;
                tobeDelete.right.parent = parentNode;
            }
            tobeDelete = null;
            size--;
        } else if (null != tobeDelete.left) {
            if (larger) {
                parentNode.right = tobeDelete.left;
                tobeDelete.left.parent = parentNode;
            }
            else {
                parentNode.left = tobeDelete.left;
                tobeDelete.left.parent = parentNode;
            }
            tobeDelete = null;
            size--;
        } else {

            if (larger) {
                parentNode.right = null;
            }
            else {
                parentNode.left = null;
            }
            tobeDelete = null;
            size--;
        }
    }

    private int getCurrentheight(BSTNode root) {
        if (null == root)
        {
            return 0;
        } else {
            return 1 + Math.max(getCurrentheight(root.left), getCurrentheight(root.right));
        }
    }

    private BSTNode findBiggest(BSTNode currentRoot) {
        while (null != currentRoot.right) {
            currentRoot = currentRoot.right;
        }

        return currentRoot;
    }

    private BSTNode findSmallest(BSTNode currentRoot) {
        while (null != currentRoot.left) {
            currentRoot = currentRoot.left;
        }

        return currentRoot;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(5);
        bst.insert(10);
        bst.insert(7);
        bst.insert(6);
        bst.insert(12);
        bst.insert(13);
        bst.insert(3);
        bst.insert(1);
        bst.insert(4);

        bst.printTree();

        bst.delete(10);
        bst.printTree();
    }
}
