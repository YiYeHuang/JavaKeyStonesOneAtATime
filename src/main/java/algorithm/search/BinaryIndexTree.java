package algorithm.search;

public class BinaryIndexTree {
    private int[] sums;
    private int size;

    public BinaryIndexTree(int n) {
        size = n + 1;
        sums = new int[size];
    }

    public void update(int i, int val) {
        // skip the initial 0
        ++i;
        while (i < size) {
            sums[i] += val;
            i += Integer.lowestOneBit(i);
        }
    }

    public int sum(int i) {
        // skip the initial 0
        ++i;
        int ans = 0;
        while (i > 0) {
            ans += sums[i];
            i -= Integer.lowestOneBit(i);
        }
        return ans;
    }

    public int query(int i, int j) {
        return sum(j) - sum(i - 1);
    }
}
