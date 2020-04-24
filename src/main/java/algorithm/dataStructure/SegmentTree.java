package algorithm.dataStructure;

public class SegmentTree {

    static class SegmentTreeNode {
        int start;
        int end;
        SegmentTreeNode left;
        SegmentTreeNode right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

    private SegmentTreeNode root = null;

    public SegmentTree(int[] nums) {

    }

    /**
     * Build tree from root, root has full start and end
     * split range in half and build child node until all child node has same start and end
     */
//    public SegmentTreeNode build(int[] nums, int start, int end) {
//        if (start > end) {
//            return null;
//        } else {
//
//        }
//    }

}
