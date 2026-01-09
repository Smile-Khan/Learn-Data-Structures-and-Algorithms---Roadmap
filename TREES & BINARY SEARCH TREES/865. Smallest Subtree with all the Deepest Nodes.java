// QUESTION: 865. Smallest Subtree with all the Deepest Nodes
// LINK: 865. Smallest Subtree with all the Deepest Nodes

// APPROACH 1: 

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
    /*
    BRUTE FORCE APPROACH (Theoretical - NOt Recommended):

    HOW IT WORKS:
    1. Find all deepest nodes using BFS/DFS
    2. For each node in tree, check if its subtree contains all deepest nodes
    3. Among all nodes whose subtrees contain deepest nodes, find smallest

    WHY IT'S BRUTE FORCE
    - For each node (O(n)), check if subtree contains all deepest nodes (O(n))
    - Total O(n^2) time complexity
    - Lots of repeated subtree traversals

    COMPLEXITY:
    TIME: O(n^2) where n is number of nodes
    SPACE: O(n) for storing deepest nodes

    GOOD FOR: Undertanding the problem only
    */

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return null;

        // Step 1: Find all deepest nodes
        List<TreeNode> deepestNodes = new ArrayList<>();
        int maxDepth = findDeepestNodes(root, deepestNodes);

        // Step 2: Check each node's subtree
        TreeNode result = null;
        int minSize = Integer.MAX_VALUE;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty())
        {
            TreeNode node = queue.poll();

            // Count nodes in this subtree
            int subtreeSize = countNodes(node);

            // Check if this subtree contains all deepest nodes
            if(containsAllDeepest(node, deepestNodes) && subtreeSize < minSize)
            {
                minSize = subtreeSize;
                result = node;
            }

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return result;
    }
    private int findDeepestNodes(TreeNode node, List<TreeNode> deepestNodes)
    {
        // BFS to find deepest level nodes
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 0;

        while(!queue.isEmpty())
        {
            int size = queue.size();
            deepestNodes.clear();   // Clear previous level

            for(int i = 0; i < size; i++)
            {
                TreeNode current = queue.poll();
                deepestNodes.add(current);

                if(current.left != null) queue.offer(current.left);
                if(current.right != null) queue.offer(current.right);
            }
            depth++;
        }
        return depth;
    }
    private int countNodes(TreeNode node)
    {
        if(node == null) return 0;

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private boolean containsAllDeepest(TreeNode root, List<TreeNode> deepestNodes)
    {
        Set<TreeNode> subtreeNodes = new HashSet<>();
        collectNodes(root, subtreeNodes);

        for(TreeNode deepNode : deepestNodes)
        {
            if(!subtreeNodes.contains(deepNode))
            {
                return false;
            }
        }
        return true;
    }

    private void collectNodes(TreeNode node, Set<TreeNode> nodes)
    {
        if(node == null) return;

        nodes.add(node);

        collectNodes(node.left, nodes);
        collectNodes(node.right, nodes);
    }
}