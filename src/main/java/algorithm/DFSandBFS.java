package algorithm;

import baseObj.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * DFS is stack, BFS is queue
 * 
 * 5 3 8 1 4 7 10 6 9 11 12
 */
public class DFSandBFS
{
    public static void main(String[] args)
    {
        TreeNode head1 = new TreeNode(5);

        head1.left = new TreeNode(3);
        head1.right = new TreeNode(8);
        head1.left.left = new TreeNode(1);
        head1.right.right = new TreeNode(10);
        head1.left.right = new TreeNode(4);
        head1.right.left = new TreeNode(7);
        head1.right.left.left = new TreeNode(6);
        head1.right.right.left = new TreeNode(9);
        head1.right.right.right = new TreeNode(11);
        head1.right.right.right.right = new TreeNode(12);

        System.out.println("Max node value: " + max(head1));
        System.out.println("Min node value: " + min(head1));
        System.out.println("Tree depth value: " + treeMaxDepth(head1));
        System.out.println("In order print");
        inorder(head1);
        System.out.println("");
        System.out.println("pre order print");
        preorder(head1);
        System.out.println("");
        System.out.println("post order print");
        postorder(head1);
        System.out.println("");
        System.out.println("Given level print");
        printGivenLevel(0, head1);
        System.out.println("");

        TreeNode head2 = new TreeNode(5);

        head2.left = new TreeNode(3);
        head2.right = new TreeNode(8);
        head2.left.left = new TreeNode(1);
        head2.right.right = new TreeNode(10);
        head2.left.right = new TreeNode(4);
        head2.right.left = new TreeNode(7);
        head2.right.left.left = new TreeNode(6);
        head2.right.right.left = new TreeNode(9);
        head2.right.right.right = new TreeNode(11);
        head2.right.right.right.right = new TreeNode(12);

        System.out.println("compare tree");
        System.out.println(compareTree(head1, head2));
        System.out.println("valid tree");
        System.out.println(validBST(head1));
        preorder(head1);
    }

    public void insert(int value, TreeNode head)
    {
        TreeNode inNode = new TreeNode(value);

        if (null == head)
        {
            return;
        }
        else
        {
            insertValue(head, inNode);
        }
    }

    private void insertValue(TreeNode lastest, TreeNode valueNode)
    {
        if (lastest.value > valueNode.value)
        {
            if (null == lastest.left)
            {
                lastest.left = valueNode;
                return;
            }
            else
            {
                insertValue(lastest.left, valueNode);
            }
        } else
        {
            if (null == lastest.right)
            {
                lastest.right = valueNode;
                return;
            } else
            {
                insertValue(lastest.right, valueNode);
            }
        }
    }

    public static int max(TreeNode head)
    {
        if (null == head)
        {
            return 0;
        }
        TreeNode ptr = head;
        while (null != ptr.right)
        {
            ptr = ptr.right;
        }
        return ptr.value;
    }

    public static int min(TreeNode head)
    {
        if (null == head)
        {
            return 0;
        }
        TreeNode ptr = head;
        while (null != ptr.left)
        {
            ptr = ptr.left;
        }
        return ptr.value;
    }

    public static int treeMaxDepth(TreeNode head)
    {
        if (null == head)
        {
            return 0;
        } else
        {
            return 1 + Math.max(treeMaxDepth(head.left), treeMaxDepth(head.right));
        }
    }

    /**
     * sorted order
     */
    public static void inorder(TreeNode input)
    {
        if (null == input)
        {
            return;
        } else
        {
            inorder(input.left);
            System.out.print(input.value + " ");
            inorder(input.right);
        }
    }

    /**
     * cover to the deep left and right
     */
    public static void postorder(TreeNode input)
    {
        if (null == input)
        {
            return;
        } else
        {
            postorder(input.left);
            postorder(input.right);
            System.out.print(input.value + " ");
        }
    }

    /**
     * insert order
     */
    public static void preorder(TreeNode input)
    {
        if (null == input)
        {
            return;
        } else
        {
            System.out.print(input.value + " ");
            preorder(input.left);
            preorder(input.right);
        }
    }

    public static void printGivenLevel(int level, TreeNode head)
    {
        if (null == head)
        {
            return;
        } else
        {
            findandPrint(level, head);
        }
    }

    private static void findandPrint(int level, TreeNode current)
    {
        if (level == 0)
        {
            System.out.print(current.value + " ");
        } else
        {
            if (null != current.left)
            {
                findandPrint(level - 1, current.left);
            }
            if (null != current.right)
            {
                findandPrint(level - 1, current.right);
            }
        }
    }

    public static boolean compareTree(TreeNode node1, TreeNode node2)
    {
        if (node1 == null && node2 == null)
        {
            return true;
        } else
        {
            return node1 != null && node2 != null && node1.value == node2.value && compareTree(node1.left, node2.left)
                    && compareTree(node1.right, node2.right);
        }
    }

    public static boolean validBST(TreeNode root)
    {
        List<Integer> aa = new ArrayList<Integer>();
        load(root, aa);
        for (int i = 0; i + 1 < aa.size(); i++)
        {
            if (aa.get(i) > aa.get(i + 1))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This is a in order, but added into a tree
     */
    public static void load(TreeNode root, List<Integer> aa)
    {
        if (null == root)
        {
            return;
        } else
        {
            load(root.left, aa);
            aa.add(root.value);
            load(root.right, aa);
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.value);
            root = root.right;
        }
        return list;
    }
}
