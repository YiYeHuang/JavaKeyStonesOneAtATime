package leetcode.dfs.ValidateBST;


import leetcode.basicDto.TreeNode;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ValidateBST_MultipleThread {

  public static void main(String[] args) {

    TreeNode treeNode = new TreeNode(1);

    ForkJoinPool pool = new ForkJoinPool();
    ForkJoinTask<Boolean> task = new ValidTreeSubTask(treeNode, null, null);
    boolean result = pool.invoke(task);
    pool.shutdown();
  }

  public static class ValidTreeSubTask extends RecursiveTask<Boolean> {

    Integer max;
    Integer min;
    TreeNode root;

    public ValidTreeSubTask(TreeNode root, Integer max, Integer min) {
      this.root = root;
      this.min = min;
      this.max = max;
    }

    @Override
    protected Boolean compute() {
      if (root == null) {
        return true;
      }

      //  right child check                      left child check
      if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
        return false;
      }

      ValidTreeSubTask leftResult = new ValidTreeSubTask(root.left, min, root.val);
      ValidTreeSubTask rightResult = new ValidTreeSubTask(root.right, root.val, max);

      invokeAll(leftResult, rightResult);


      return leftResult.join() && rightResult.join();
    }
  }
}
