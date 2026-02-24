// QUESTION: 1022. Sum of Root To Leaf Binary Numbers
// LINK: https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/description/?envType=daily-question&envId=2026-02-24

// SOLUTION: DFS

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
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode node, int currentValue)
    {
        if(node == null) return 0;

        // Update current value: shift left and add current node's value
        currentValue = (currentValue << 1) | node.val;

        // If it's a leaf node, return the accumulated value
        if(node.left == null && node.right == null)
        {
            return currentValue;
        }

        // Otherwise, continue DFS on left and right
        return dfs(node.left, currentValue) + dfs(node.right, currentValue);
    }
}

// APPROACH 2: STACK

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
    public int sumRootToLeaf(TreeNode root) {
        if(root == null) return 0;

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 0));
        int total = 0;

        while(!stack.isEmpty())
        {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            int currentValue = pair.getValue();

            // Update current value
            currentValue = (currentValue << 1) | node.val;

            // If leaf, add to total
            if(node.left == null && node.right == null)
            {
                total += currentValue;
            }
            else
            {
                if(node.right != null) stack.push(new Pair<>(node.right, currentValue));

                if(node.left != null) stack.push(new Pair<>(node.left, currentValue));
            }
        }
        return total;
    }
}


// APPROACH 3: BFS

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
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int total = 0;
        
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int currentValue = pair.getValue();
            
            currentValue = (currentValue << 1) | node.val;
            
            if (node.left == null && node.right == null) {
                total += currentValue;
            } else {
                if (node.left != null) queue.offer(new Pair<>(node.left, currentValue));
                if (node.right != null) queue.offer(new Pair<>(node.right, currentValue));
            }
        }
        
        return total;
    }
}