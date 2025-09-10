// QUESTION: 108. Convert Sorted Array to Binary Search Tree
// LINK: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/

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
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildBST(int[] nums, int left, int right) {
        // Base case: invalid range
        if (left > right) {
            return null;
        }
        
        // Choose middle element as root
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        // Recursively build left and right subtrees
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);
        
        return root;
    }
}

// SOLUTION: Approach 2: Alternative Middle Calculation


class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) return null;
        
        // Two ways to choose middle:
        // Option 1: Always choose left-middle for even length
        int mid = start + (end - start) / 2;
        
        // Option 2: Always choose right-middle for even length  
        // int mid = start + (end - start + 1) / 2;
        
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, start, mid - 1);
        node.right = helper(nums, mid + 1, end);
        
        return node;
    }
}