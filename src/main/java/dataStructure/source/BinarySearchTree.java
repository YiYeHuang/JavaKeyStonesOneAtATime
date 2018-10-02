package dataStructure.source;

public class BinarySearchTree {

    private BSTNode root = null;

    public BinarySearchTree() {
    }

    public void insert(int value) {
        if (root == null) {
            root = new BSTNode(value);
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

            return true;
        } else {
            return traceDelete(value, this.root);
        }
    }

    public int getHeight() {
        return getCurrentheight(root);
    }

    public void printTree() {
        int currentheight = getCurrentheight(this.root);

        for (int i = 1; i <= currentheight; i++) {
            levelPrint(i, this.root);
            System.out.println("");
        }
    }

    public int findMax() {
        return findBiggest(this.root).value;
    }

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

    private void traceInsert(BSTNode currentRoot, int value) {
        if (value > currentRoot.value) {
            if (null == currentRoot.right) {
                currentRoot.right = new BSTNode(value);
                currentRoot.right.parent = currentRoot;
            } else {
                traceInsert(currentRoot.right, value);
            }
        } else if (value < currentRoot.value) {
            if (null == currentRoot.left) {
                currentRoot.left = new BSTNode(value);
                currentRoot.left.parent = currentRoot;
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
        } else {

            if (larger) {
                parentNode.right = null;
            }
            else {
                parentNode.left = null;
            }
            tobeDelete = null;
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

        bst.delete(4);


        bst.printTree();
    }
}
