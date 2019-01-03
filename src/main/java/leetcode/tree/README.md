## Tree Leetcode Notes

### BFS: Level order Questions,  Level Order using List
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

#### 102. Binary Tree Level Order Traversal
BFS Level Order with List in a list
- using the nature of arraylist
	- when size is match level, initial the level list
	- added the number in the level list
	- recursively call and increment the level

#### 107. Binary Tree Level Order Traversal II: reverse order
BFS Level Order with List in a list
- using the nature of arraylist
	- when size is match level, initial the level list, keep push new level list at index 0, so level 0 will be at 
	the end
	- added the number in the level list: get level list with size - level -1
	- recursively call and increment the level

```java
	public void compute(List<List<Integer>> ans,TreeNode curr,int level)
	{
		if(curr==null) return;

		// first in, push to the end
		if(ans.size()==level)
			ans.add(0,new ArrayList<Integer>());

		ans.get(ans.size()-level-1).add(curr.value);

		compute(ans,curr.left,level+1);
		compute(ans,curr.right,level+1);
	}
```
#### 199. Binary Tree Right Side View
BFS Level Order with a list:
- recursive call
	- when size is match level, initial each level value with any value
	- update the number in each (since it is bfs, the last update will be the most right)
	- recursively call and increment the level

#### 515. Find Largest Value in Each Tree Row
BFS Level Order with a list:
- recursive call
	- when size is match level, initial each level value with Integer min
	- update the number in each level if larger than current value.
	- recursively call and increment the level

#### 637. Average of Levels in Binary Tree
BFS Level Order with two list: count and sum
- recursive call
	- when size is match level, initial each level value
	- added the number in the level into sum, and increment the count
	- recursively call and increment the level

## BFS: using queue



## DFS questions using a stack
template: for each node, pushing left to the end, whenever poping a left, push ONE right
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
