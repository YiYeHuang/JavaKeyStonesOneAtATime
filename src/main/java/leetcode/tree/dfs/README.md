## DFS LeetCode Notes

### Tricks and Templates

### DFS with stack template
```java
    public static List<Integer> dfsTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty())
        {
            // push all the way to left most
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            // checking each right node of left node
            root = stack.pop();
            list.add(root.value);
            root = root.right;
        }
        return list;
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
        /* logic here */
        result.add(node.value);
        load(node.right, result);
    }
```
