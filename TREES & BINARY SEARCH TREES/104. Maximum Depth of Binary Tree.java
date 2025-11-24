// QUESTION: 104. Maximum Depth of Binary Tree
// LINK: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/


// SOLUTION: 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return helper(root, 0);
    }
    private int helper(TreeNode root, int depth)
    {
        if(root == null)
        {
            return depth;
        }

        int left = helper(root.left, depth + 1);
        int right = helper(root.right, depth + 1);
        return Math.max(left, right);
    }
}


// Approach 2: Iterative DFS (Stack)

public class Solution {
    public int maxDepth(TreeNode root)
    {
        if(root == null) 
        return 0;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();


        nodeStack.push(root);
        depthStack.push(1);

        int maxDepth = 0;

        while(!nodeStack.isEmpty())
        {
            TreeNode node = nodeStack.pop();
            int currentDepth = depthStack.pop();

            maxDepth = Math.max(maxDepth, currentDepth);

            if(node.left != null)
            {
                nodeStack.push(node.left);
                depthStack.push(currentDepth + 1);
            }

            if(node.right != null)
            {
                nodeStack.push(node.right);
                depthStack.push(currentDepth + 1);
            }
        }
        return maxDepth;
    }
}

// Approach 3: Iterative BFS (Level Order)

public class Solution{
    pubic int maxDepth(TreeNode root)
    {
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        while(!queue.isEmpty())
        {
            int levelSize = queue.size();
            depth++;
        

        // Process all nodes at current level
        for(int i = 0; i < levelSize; i++)
        {
            TreeNode node = queue.poll();

            if(node.left != null)
            {
                queue.ofrer(node.left);
            }
            if(node.right != null)
            {
                queue.offer(node.right);
            }
        }
    }
    return depth;
    }
}

// Approach 4: One-liner Recursive

public class Solution {
    public int maxDepth(TreeNode root)
    {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}