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

// APPROACH 2: TWO PASS DFS-BFS

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
    APPROACH 2: TWO-PASS BFS/DFS (First Practial Solution):

    HOW IT WORKS:
    1. First BFS: Find deepest level and all nodes at that level
    2. Second DFS: Find Lowest Common Ancestor (LCA) of all deepest nodes

    KEY INSIGHT:
    - Smallest subtree containing all deepest nodes = LCA of all deepest nodes
    - We don't need to check all nodes, just find LCA

    ALGORITHM:
    1. BFS to get all deepest nodes
    2. Recursively find LCA of all deepest nodes

    COMPLEXITY:
    TIME: O(N) - TWO TRAVERSALS
    SPACE: O(N) - STORE DEEPEST NODES + RECURSION
    */

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return null;

        // Step 1: Get all deepest nodes using BFS

        List<TreeNode> deepestNodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty())
        {
            int size = queue.size();
            deepestNodes.clear();   // Clear previous level

            for(int i = 0; i < size; i++)
            {
                TreeNode node = queue.poll();
                deepestNodes.add(node);

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        // Step 2: Find LCA of all deepest nodes
        return findLCA(root, deepestNodes);
    }
    private TreeNode findLCA(TreeNode root, List<TreeNode> nodes)
    {
        if(root == null || nodes.contains(root))
        {
            return root;
        }

        TreeNode left = findLCA(root.left, nodes);
        TreeNode right = findLCA(root.right, nodes);

        if(left != null && right != null)
        {
            return root;    // This is the LCA
        }

        return left != null ? left : right;
    }
}

// APPROACH 3: RECURSIVE DEPTH CALCULATION (Clean DFS):

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
    APPROACH 3: RECURSION DEPTH CALCULATION (Clean DFS):

    HOW IT WORKS:
    1. For each node, calculate depth of its left and right subtrees
    2. If both subtrees have same max depth, current node is LCA
    3. If one subtree is deeper, continue search in that subtree

    KEY INSIGHT:
    - The LCA of deepest nodes is where left and right depths are equal
    - Or if one side is deeper, LCA is in that deeper side

    ALGORITHM:
    DFS returns both node and depth:
    - Return (node, depth) where node is potential LCA

    COMPLEXITY:
    TIME: O(N) - SINGLE TRAVERSAL
    SPACE: O(H) - RECURSION STACK
    */

    class NodeDepth{
        TreeNode node;
        int depth;
        NodeDepth(TreeNode n, int d)
        {
            node = n;
            depth = d;
        }
    }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private NodeDepth dfs(TreeNode node)
    {
        if(node == null)
        {
            return new NodeDepth(null, 0);
        }

        NodeDepth left = dfs(node.left);
        NodeDepth right = dfs(node.right);

        if(left.depth > right.depth)
        {
            return new NodeDepth(left.node, left.depth + 1);
        }

        if(right.depth > left.depth)
        {
            return new NodeDepth(right.node, right.depth + 1);
        }

        // Both sides have same depth, current node is LCA
        return new NodeDepth(node, left.depth + 1);
    }
}

// APPROACH 4: DFS WITH SIMPLE RETURN (Most Elegant):

class Solution {
    /*
    APPROACH 4: DFS WITH SIMPLE RETURN (Most Elegant):
    
    HOW IT WORKS:
    1. Simple recursive function returns TreeNode and depth
    2. Compare depths of left and right subtrees
    3. Return deeper subtree's result, or current node if equal
    
    BEAUTY OF THIS APPROACH:
    - No extra classes needed (use int[] for depth)
    - Very clean and concise
    - Easy to understand
    
    COMPLEXITY:
    Time: O(n)
    Space: O(h)
    */
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return helper(root)[0];
    }
    
    // Returns [TreeNode, depth]
    private TreeNode[] helper(TreeNode node) {
        if (node == null) {
            return new TreeNode[]{null, new int[]{0}};
        }
        
        TreeNode[] left = helper(node.left);
        TreeNode[] right = helper(node.right);
        
        if (left[1][0] > right[1][0]) {
            return new TreeNode[]{left[0], new int[]{left[1][0] + 1}};
        }
        
        if (right[1][0] > left[1][0]) {
            return new TreeNode[]{right[0], new int[]{right[1][0] + 1}};
        }
        
        // Equal depth, current node is LCA
        return new TreeNode[]{node, new int[]{left[1][0] + 1}};
    }
}