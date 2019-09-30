package dataStructure.tree;

public class FenwickTree {
    private int[] counts;
    private int size;

    public FenwickTree(int n) {
        size = n + 1;
        counts = new int[size];
    }

    public void update(int i, int val) {
        ++i;
        while (i < size) {
            counts[i] += val;
            i += Integer.lowestOneBit(i);
        }
    }

    public int sum(int i) {
        ++i;
        int ans = 0;
        while (i > 0) {
            ans += counts[i];
            i -= Integer.lowestOneBit(i);
        }
        return ans;
    }

    public int query(int i, int j) {
        return sum(j) - sum(i - 1);
    }
}