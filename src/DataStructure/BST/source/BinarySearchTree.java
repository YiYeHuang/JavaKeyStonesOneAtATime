package DataStructure.BST.source;

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

    public Integer delete(int value) {
        return null;
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

    private int getCurrentheight(TreeNode root) {
        if (null == root)
        {
            return 0;
        } else {
            return 1 + Math.max(getCurrentheight(root.left), getCurrentheight(root.right));
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(10);
        bst.insert(4);
        bst.insert(15);
        bst.insert(20);
        bst.insert(1);
        bst.insert(6);
        bst.insert(11);
        bst.printTree();
        System.out.println(bst.search(1));
    }
}
