// QUESTION: 235. Lowest Common Ancestor of a Binary Search Tree
// LINK: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

// SOLUTION: RECURSIVE (MOST INTUITIVE)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        // If both p and q are smaller than root, LCA is in left subtree
        if(p.val < root.val && q.val < root.val)
        {
            return lowestCommonAncestor(root.left, p, q);
        }

        // If both p and q are greaer than root, LCA is in right subtree
        if(p.val > root.val && q.val > root.val)
        {
            return  lowestCommonAncestor(root.right, p, q);
        }

        // If we reach here, we have found the split point
        // Either root is one of p and q, or p and q are on different sides
        return root;
    }
}

// Approach 2: Iterative (space optimized)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {

        TreeNode current = root;

        while(current != null)
        {
            // Both in left subtree
            if(p.val < current.val && q.val < current.val)
            {
                current = current.val;
            }

            // Both in right subtree
            else if(p.val > current.val && q.val > current.val)
            {
                current = current.right;
            }
            // Found the LCA (split point)
            else 
            {
                return current;
            }
        }
        return null;
    }
}


// APPROACH 3: Detailed with All Cases

class Solution{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        int rootVal = root.val;
        int pVal = p.val;
        int qVal = q.val;

        // Case 1: Both nodes are in left subtree
        if(pVal < rootVal && qVal < rootVal)
        {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Case 2: Both nodes are in right subtree
        if(pVal > rootVal && qVal > rootVal)
        {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Case 3: Split point found(LCA);
        // This handles: root == p, root == q, or p and q on different sides
        else 
        {
            return root;
        }
    }
}