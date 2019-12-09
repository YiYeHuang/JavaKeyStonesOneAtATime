package baseObj;


public class TreeNode
{
    public int val;
    public TreeNode right;
    public TreeNode left;

    public TreeNode(int value)
    {
        this.val = value;
    }

    @Override
    public String toString() {
        return "Node: " + val;
    }
}