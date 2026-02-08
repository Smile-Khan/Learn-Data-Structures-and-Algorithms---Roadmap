// QUESTION: 110. Balanced Binary Tree
// LINK: https://leetcode.com/problems/balanced-binary-tree/?envType=daily-question&envId=2026-02-08

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
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;

        // Check if current node is balanced
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if(Math.abs(leftHeight - rightHeight) > 1)
        {
            return false;
        }

        // Check if both subtrees are balanced
        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int height(TreeNode node)
    {
        if(node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}