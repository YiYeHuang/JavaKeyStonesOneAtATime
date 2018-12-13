## Tree Leetcode Notes

BFS Level Order Template
```java 
    public void compute(TreeNode root,int level)
    {
        if(root==null) return;
        
        if( [reach level logic]) {
            // Do something
        }
        
        compute(curr.left,level+1);
        compute(curr.right,level+1);
    }
```
example
```java
    public void compute(List<List<Integer>> ans,TreeNode curr,int level)
    {
        if(curr==null) return;
        
        if(ans.size()==level) 
            ans.add(new ArrayList<Integer>());
        
        ans.get(level).add(curr.val);
        
        compute(ans,curr.left,level+1);
        compute(ans,curr.right,level+1);
     }
```

DFS with stack template
```java
    public static List<Integer> dfsTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.value);
            root = root.right;
        }
        return list;
    }
```
