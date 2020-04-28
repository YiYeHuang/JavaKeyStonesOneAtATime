package simulation;

public class Block {
    public int i;
    public int j;
    public int value;

    public Block(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + i + "," + j + " " + value + "]";
    }
}
