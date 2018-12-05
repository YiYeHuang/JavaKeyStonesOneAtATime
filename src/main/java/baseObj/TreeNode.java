package baseObj;


public class TreeNode
{
    public int value;
    public TreeNode right;
    public TreeNode left;

    public TreeNode(int value)
    {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node: " + value;
    }
}