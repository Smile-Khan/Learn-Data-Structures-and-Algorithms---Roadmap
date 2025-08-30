// QUESTION: 98. Validate Binary Search Tree
// LINK: https://leetcode.com/problems/validate-binary-search-tree/

// SOLUTION: 
// Approach 1: Boundary Validation (Recommended for Interviews)

class Solution {
    public boolean isValidBST(TreeNode root)
    {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALEU);
    }

    private boolean validate(TreeNode node, long min, long max)
    {
        if(node == null)
        return true;

         // check if current node violates BST properly
         if(node.val <= min || node.val >= max)
         {
            return false;
         }

         // recursively validate left and right subtrees with updated bounds
         return validate(node.left, min, node.va) && 
                validate(node.right, node.val, max);
    }
}


// Approach 2: In-Order Traversal

class Solution {
    private TreeNode prev = null;

    public boolean isValidBST(TreeNode root)
    {
        return inorder(root);
    }

    private boolean inorder(TreeNode node)
    {
        if(node == null) return true;

        // visit left subtree
        if(!inorder(node.left))
        return false;

        // check current node(should be > previous in in-order)
        if(prev != null && node.val <= prev.val)
        {
            return false;
        }

        prev = node;

        // visit right subtree
        return inorder(node.right);
    }
}

