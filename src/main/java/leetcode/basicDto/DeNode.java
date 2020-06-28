package leetcode.basicDto;

import java.util.LinkedHashSet;

public class DeNode {

    public int count = 0;
    public LinkedHashSet<Integer> keys = null;
    public DeNode prev = null;
    public DeNode next = null;

    public DeNode(int count) {
        this.count = count;
        keys = new LinkedHashSet<Integer>();
        prev = next = null;
    }
}
