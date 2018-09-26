package dataStructure.BST.source;

import basic.TreeNode;

public class BinarySearchTree {

    private TreeNode root = null;

    public BinarySearchTree() {
    }

    public void insert(int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            traceInsert(root, value);
        }
    }

    public boolean search(int value) {
        return traceSearch(value, this.root);
    }

    public boolean delete(int value) {
        if (null != this.root && this.root.value == value) {
            if (null != root.right && null != root.left) {
                TreeNode currentMax = findBiggest(this.root.left);
                TreeNode tempRoot = this.root;
                currentMax.right = this.root.right;
                this.root = this.root.left;
                tempRoot = null;
            } else if (null != root.right) {
                TreeNode tempRoot = this.root;
                this.root = root.right;
                tempRoot = null;
            } else if (null != root.left) {
                TreeNode tempRoot = this.root;
                this.root = root.left;
                tempRoot = null;
            }
        }

        return true;
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

    private void levelPrint(int level, TreeNode currentRoot) {
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

    private void traceInsert(TreeNode currentRoot, int value) {
        if (value > currentRoot.value) {
            if (null == currentRoot.right) {
                currentRoot.right = new TreeNode(value);
            } else {
                traceInsert(currentRoot.right, value);
            }
        } else if (value < currentRoot.value) {
            if (null == currentRoot.left) {
                currentRoot.left = new TreeNode(value);
            } else {
                traceInsert(currentRoot.left, value);
            }
        } else {
            // duplicate;
        }
    }

    private boolean traceSearch(int value, TreeNode currentRoot) {
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

    private boolean traceDelete(int value, TreeNode currentRoot) {
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

    private int getCurrentheight(TreeNode root) {
        if (null == root)
        {
            return 0;
        } else {
            return 1 + Math.max(getCurrentheight(root.left), getCurrentheight(root.right));
        }
    }

    private TreeNode findBiggest(TreeNode currentRoot) {
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

        bst.delete(5);

        bst.printTree();
    }
}
