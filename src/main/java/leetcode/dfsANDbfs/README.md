## DFS and BFS LeetCode Notes

BFS Level Order Template
```java 
    public void compute(List<List<Integer>> ans,TreeNode curr,int level)
    {
        if(curr==null) return;
        
        if(ans.size()==level) {
            // Do sth
        }
        
        compute(ans,curr.left,level+1);
        compute(ans,curr.right,level+1);
    }
```