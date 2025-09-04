// QUESTION: 236. Lowest Common Ancestor of a Binary Tree
// LINK: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/


// SOLUTION: Recursive Post-Order (Most common)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        // Base case : Reached null or found one of the target nodes
        if(root == null || root == p || root == q)
        {
            return root;
        }

        // Recursively search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);


        // Decision logic based on what subtrees returned
        if(left != null && right != null)
        {
            // found split point both p and q exist
            return root;
        }

        // Return whichever subtree found a target (or null if neither)
        return left != null ? left : right;
    }
}