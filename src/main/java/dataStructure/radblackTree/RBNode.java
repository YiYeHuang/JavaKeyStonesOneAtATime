package dataStructure.radblackTree;

public class RBNode {

    private boolean isRed = false;

    private int value;

    public RBNode left;

    public RBNode right;

    public RBNode(int input, boolean isRed) {
        this.isRed = isRed;
        this.value = input;
    }

    public int getValue() {
        return value;
    }

    public boolean isRed() {
        return isRed;
    }

    public boolean isBlack() {
        return !isRed;
    }
}
