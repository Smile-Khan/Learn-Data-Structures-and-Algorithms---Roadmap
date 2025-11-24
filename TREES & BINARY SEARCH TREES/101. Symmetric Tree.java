// QUESTION: 101. Symmetric Tree
// LINK: https://leetcode.com/problems/symmetric-tree/description/

// SOLUTION 1: Recursive Approach

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {this.val = val;}
    TreeNode(int val, TreeNode left, TreeNode right)
    {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


class Solution {
    public boolean isSymmetric(TreeNode root)
    {
        if(root == null) return true;

        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right)
    {
        // Base case
        if(left == null && right == null) 
        {
            return true;
        }

        if(left == null || right == null)
        {
            return false;
        }

        // Check current nodes and recurse
        return(left.val == right.val)
        && isMirror(left.left, right.right)     // Left's left with Right's right
        && isMirror(left.right, right.left);    // Left's right with Right's left
    }
}

//SOLUTION 2: Iterative Approach(Queue/ Stack)

import java.util.*;

class Solution {
    public boolean isSymmetric(TreeNode root)
    {
        if(root == null)
        {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while(!queue.isEmpty())
        {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();


            // Both null - continue;
            if(left == null && right == null)
            {
                continue;
            }

            // One null or values don't match - not symmetric
            if(left == null || right == null || left.val != right.val)
            {
                return false;
            }

            // Add children in mirror order
            queue.offer(left.left);         // Left's left child
            queue.offer(right.right);       // Right's right child
            queue.offer(left.right);        // Left's right child
            queue.offer(right.left);        // Right's left child
        }
        return true;
    }
}