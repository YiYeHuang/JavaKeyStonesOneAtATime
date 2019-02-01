## BFS LeetCode Notes

### Tricks and Templates

### Push Queue, and consume. Common for binary tree and graph
```java
    public void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // only stop when queue is empty
        while (!queue.isEmpty()) {
            int size = queue.size();
            // consume all the current level node, which means one level is passed, level++
            while (size > 0) {
                root = queue.poll();
                size --;
                /* logic for when and order for pushing left or right node */
                /* or array of child node */
            }
            
            /* level ending logic here, for construct result or calculating numbers */
        }
    }
```


### Level order recursive template. When need full result in array
```java
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        compute(ans,root,0);
        return ans;
    }

    public void compute(List<List<Integer>> ans,TreeNode curr,int level)
    {
        if(curr==null) return;

        if(ans.size()==level) {
            /* initial structure */
            ans.add(new ArrayList<Integer>());
        }

        /* computing logic  */

        compute(ans,curr.left,level+1);
        compute(ans,curr.right,level+1);
    }
```

### In order traversal recursive template. With help of global variable
```java

	public static List<Integer> inorderTraversalRec(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		load(root, result);
		return result;
	}
	
	private static void load(TreeNode node, List<Integer> result) {
		if (node == null) return;

		load(node.left, result);
		/* computing logic here */
		result.add(node.value);
		load(node.right, result);
	}
```

