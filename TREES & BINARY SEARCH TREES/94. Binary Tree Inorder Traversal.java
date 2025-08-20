// QUESTION: 94. Binary Tree Inorder Traversal
// LINK: https://leetcode.com/problems/binary-tree-inorder-traversal/description/

// SOLUTION: RECURSIVE 

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
    public List<Integer> inorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while(current != null || !stack.isEmpty())
        {
            // Step 1: go as far left as possible
            while(current != null)
            {
                stack.push(current);
                current = current.left;
            }

            // Step 2: process top of stack
            current = stack.pop();
            result.add(current.val);

            // Step 3: move to right child
            current = current.right;
        }
        return result;
    }
}



// SOLUTION: ITERATIVE (STACK)

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
    public List<Integer> inorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while(current != null || !stack.isEmpty())
        {
            // Step 1: go as far left as possible
            while(current != null)
            {
                stack.push(current);
                current = current.left;
            }

            // Step 2: process top of stack
            current = stack.pop();
            result.add(current.val);

            // Step 3: move to right child
            current = current.right;
        }
        return result;
    }
}