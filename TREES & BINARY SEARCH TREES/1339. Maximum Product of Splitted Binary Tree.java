// QUESTION: 1339. Maximum Product of Splitted Binary Tree
// LINK: https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/?envType=daily-question&envId=2026-01-07

// SOLUTION: 1

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
    BRUTE FORCE APPROACH (Theoretical - Not Recommended):

    HOW IT WORKS:
    1. Generate all possible edges to cut (n - 1 edges in tree)
    2. For each edge cut, physically split the tree
    3. Calculate sum of both resulting subtrees
    4. Compute product and track maximum

    WHY IT'S BRUTE FORCE:
    - O(n^2) time: For each edge cut, we traverse entire tree to compute sums
    - Inefficient recomputation of sums

    COMPLEXITY:
    Time: O(n^2) - n edges * O(n) sum computation each
    Space: O(h) - recursion for sum computation

    GOOD FOR: Understanding the problem, but not for actual implementation
    */

    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        if(root == null) return 0;

        long maxProduct = 0;

        // Get all edges (represented by child nodes)
        List<TreeNode> allNodes = new ArrayList<>();
        collectAllNodes(root, allNodes);

        // Try removing edge above each node (except root)
        for(TreeNode node : allNodes)
        {
            if(node != root)
            {
                // Cut edge above this node
                // In reality, we need to know parent to cut edge
                // This is why brute force is complex for trees

                // Instead, we'll implement conceptual approach:
                // Sum of subtree rooted at 'node' is one part
                // Total sum minus that sum is other part
                long subtreeSum = getSubtreeSum(node);
                long totalSum = getSubtreeSum(root);
                long product = subtreeSum * (totalSum - subtreeSum);
                maxProduct = Math.max(maxProduct, product);
            }
        }

        return (int) (maxProduct % MOD);
    }
    private void collectAllNodes(TreeNode node, List<TreeNode> nodes)
    {
        if(node == null) return;
        nodes.add(node);
        collectAllNodes(node.left, nodes);
        collectAllNodes(node.right, nodes);
    }

    private long getSubtreeSum(TreeNode node)
    {
        if(node == null) return 0;
        return node.val + getSubtreeSum(node.left) + getSubtreeSum(node.right);
    }
}


// SOLUTION 2: 

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
    APPROACH 2: TWO-PASS DFS 

    HOW IT WORKS:
    1. First DFS: Calculate total sum of tree
    2. Second DFS: For each node (except root), compute product
        Product = subtreeSum * (totalSum - subtreeSum)

    KEY INSIGHT:
    - Removing edge above node 'X' splits tree into:
        1. Subtree rooted at X
        2. Remaining Tree (totalSum - subtreeSum)
    - We don't actually cut edges, just calculate as if we did

    OPTIMIZATION OVER BRUTE FORCE
    - O(n) time instead of O(n^2)
    - No repeated sum calculations

    COMPLEXITY:
    TIME: O(n) - two traversals
    SPACE: O(h) - recursion depth
    */

    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // First pass: compute total sum
        totalSum = computeTotalSum(root);

        // Second pass: find max product
        findMaxProductDFS(root);

        return (int)(maxProduct % MOD);
    }
    private long computeTotalSum(TreeNode node)
    {
        if(node == null) return 0;
        return node.val + computeTotalSum(node.left) + computeTotalSum(node.right);
    }

    private long findMaxProductDFS(TreeNode node)
    {
        if(node == null) return 0;

        long leftSum = findMaxProductDFS(node.left);
        long rightSum = findMaxProductDFS(node.right);
        long subtreeSum = node.val + leftSum + rightSum;

        // Check product if we cut edge above this node
        // Skip root because cutting above root means no edge removed
        if(subtreeSum != totalSum)
        {
            long product = subtreeSum * (totalSum - subtreeSum);
            maxProduct = Math.max(maxProduct, product);
        }
        return subtreeSum;
    }
} 


// SOLUTION 3: 

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
    APPROACH 3: POST - ORDER WITH SUBTREE SUMS LIST (Clean Separation):

    HOW IT WORKS:
    1. Perform post-order traversal to compute all subtree sums
    2. Store all subtree sums in a list
    3. Root's subtree sum = total sum
    4. Iterate through list to find max product

    ADVANTAGES:
    - Clear separation: sum computation vs product calculation
    - Easy to debug: can print all subtree sums
    - No global variables needed for product calculation

    DISADVANTAGE:
    - Extra O(n) space for the list

    COMPLEXITY:
    TIME: O(n) for DFS + O(n) for iteration = O(n)
    SPACE: O(h) recursion + O(n) list
    */

    private List<Long> subtreeSums = new ArrayList<>();
    private static final int MOD = 1_000_000_007;


    public int maxProduct(TreeNode root) {
        // Compute all subtree sums via post-order traversal
        long totalSum = postOrderDFS(root);

        // Find maximum product among all possible splits
        long maxProduct = 0;
        for(long sum : subtreeSums)
        {
            // We cannot cut edge above root, so skip total sum
            if(sum != totalSum)
            {
                long product = sum * (totalSum - sum);
                maxProduct = Math.max(maxProduct, product);
            }
        }

        return (int)(maxProduct % MOD);
    }
    private long postOrderDFS(TreeNode node)
    {
        if(node == null) return 0;

        long leftSum = postOrderDFS(node.left);
        long rightSum = postOrderDFS(node.right);
        long currentSum = node.val + leftSum + rightSum;

        // Store this subtree sum
        subtreeSums.add(currentSum);

        return currentSum;
    }
}



// SOLUTION 4: 

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
    APPROACH 4: SINGLE PASS DFS (Optimal - Best Space):

    HOW IT WORKS:
    1. Single DFS that returns subtree sum
    2. As we return from recursion, we immediately check product
    3. No need to store all subtree sums

    KEY OPTIMIZATION:
    - Compute and check product during the same DFS
    - Only need total sum, which we get from root's return value

    TRICK:
    We need total sum before we can compute products.
    Solution: First call gets total sum, then we use it.

    COMPLEXITY:
    TIME: O(n) - single DFS with product checks
    SPACE: O(h) - recursion only
    */

    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;
    private long totalSum = 0;

    public int maxProduct(TreeNode root) {
        // Get total sum by calling DFS
        totalSum = dfsWithProductCheck(root);

        // Reset and compute max product
        // Note: We could combine, but need totalSum first
        // So we do a second call (still O(n))
        dfsComputeMax(root);

        return (int)(maxProduct % MOD);    
    }
    private long dfsWithProductCheck(TreeNode node)
    {
        if(node == null) return 0;

        long leftSum = dfsWithProductCheck(node.left);
        long rightSum = dfsWithProductCheck(node.right);
        long subtreeSum = node.val + leftSum + rightSum;

        // We can't check product here because we don't know totalSum yet
        // This call just computes sums

        return subtreeSum;
    }

    private long dfsComputeMax(TreeNode node)
    {
        if(node == null) return 0;

        long leftSum = dfsComputeMax(node.left);
        long rightSum = dfsComputeMax(node.right);
        long subtreeSum = node.val + leftSum + rightSum;

        // Now we know totalSum, so we can check product
        if(subtreeSum != totalSum)
        {
            maxProduct = Math.max(maxProduct, subtreeSum * (totalSum - subtreeSum));
        }
        return subtreeSum;
    }
}  

// SOLTUION 5: 

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
    APPROACH 5: DFS WITH TARGET OPTIMIZATION (Mathematical insight):

    HOW IT WORKS:
    1. Recognize that product = s * (T - S) is maximized when s = T/2
    2. Find subtree sum closest to T/2
    3. Product with that sum will be maximum

    MATHEMATICAL PROOF:
    Let f(s) = s * (T - s) = -s^2 + Ts
    Derivative: f'(s) = -2s + T
    Set to 0: s = T/2
    This is maximum (concave down parabola)

    PRACTICAL IMPLEMENTATION:
    - Find total sum T
    - Find subtree sum closest to T/2
    - Compute product with that sum

    COMPLEXITY:
    Timee: O(n) - two DFS passes
    Space: O(h) - recursion

    NOTE: This is theoritcally optimal, but in practice,
    we still check all subtrees to find closest to T/2
    */

    private static final int MOD = 1_000_000_007;
    private long totalSum = 0;
    private long bestSubtreeSum = 0;
    private long maxProduct = 0;


    public int maxProduct(TreeNode root) {
        // Step 1: Get total sum
        totalSum = computeSum(root);

        // Step 2: Ideal target is T/2
        long target = totalSum / 2;
        bestSubtreeSum = 0;

        // Step 3: Find subtree sum closest to target
        findClosestToTarget(root, target);

        // Step 4: Compute max product with the best sum
        maxProduct = bestSubtreeSum * (totalSum - bestSubtreeSum);

        return (int)(maxProduct % MOD);
    }

    private long computeSum(TreeNode node)
    {
        if(node == null) return 0;
        return node.val + computeSum(node.left) + computeSum(node.right);
    }

    private long findClosestToTarget(TreeNode node, long target)
    {
        if(node == null) return 0;

        long leftSum = findClosestToTarget(node.left, target);
        long rightSum = findClosestToTarget(node.right, target);
        long subtreeSum = node.val + leftSum + rightSum;

        // Check if this subtree sum is closer to target
        // Skip the whole tree (root's subtree)

        if(subtreeSum != totalSum)
        {
            long currentDiff = Math.abs(subtreeSum - target);
            long bestDiff = Math.abs(bestSubtreeSum - target);

            if(currentDiff < bestDiff || (currentDiff == bestDiff && subtreeSum > bestSubtreeSum))

            {
                bestSubtreeSum = subtreeSum;
            }
        }
        return subtreeSum;
    }
} 


// SOLUTION 6:

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
    APPROACH 6: ITERATIVE DFS WITH STACK (Avoid Recursion):

    HOW IT WORKS:
    1. Use explicit stack for post-order traversal
    2. First pass: compute total sum using stack
    3. Second pass: compute subtree sums and track max product

    ADVANTAGES:
    - No recursion stack overflow for very deep trees
    - More control over traversal order
    - Can handle any tree depth (within memory limits)

    COMPLEXITY:
    TIME: O(n) - two iterations
    SPACE: O(n) - stack and map for subtree sums
    */

    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // Get total sum iteratively
        long totalSum = getTotalSumIterative(root);

        // Find max prodcut iteratively
        return (int) (getMaxProductIterative(root, totalSum) % MOD);
    }

    private long getTotalSumIterative(TreeNode root)
    {
        if(root == null) return 0;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        long sum = 0;

        // Simple iterative pre-order for total sum
        while(!stack.isEmpty())
        {
            TreeNode node = stack.pop();
            sum += node.val;

            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        return sum;
    }

    private long getMaxProductIterative(TreeNode root, long totalSum)
    {
        if(root == null) return 0;

        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Long> nodeSums = new HashMap<>();
        Set<TreeNode> visited = new HashSet<>();
        long maxProduct = 0;

        stack.push(root);

        while(!stack.isEmpty())
        {
            TreeNode node = stack.peek();

            // Process left child first if not visited
            if(node.left != null && !visited.contains(node.left))
            {
                stack.push(node.left);
            }

            // Then process right child if not visited
            else if(node.right != null && !visited.contains(node.right))
            {
                stack.push(node.right);
            }

            else 
            {
                // Both children processed (or null), process this node
                stack.pop();
                visited.add(node);

                long leftSum = node.left != null ? nodeSums.get(node.left) : 0;
                long rightSum = node.right != null ? nodeSums.get(node.right) : 0;
                long subtreeSum = node.val + leftSum + rightSum;

                nodeSums.put(node, subtreeSum);

                // Check product if this is not the whole tree
                if(subtreeSum != totalSum)
                {
                    maxProduct = Math.max(maxProduct, subtreeSum * (totalSum - subtreeSum));
                }
            }
        }
        return maxProduct;
    }
}


// SOLUTION 7:

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
    APPROACH 8: MOST OPTIMAL (Two - Pass DFS with Early Return):

    FINAL OPTIMAL SOLUTION:
    1.Single DFS computes total sum and stores all subtree sums
    2.During same DFS, track maximum product
    3. Return result

    TRIC: Use array to pass total sum by reference
    This allows single DFS with product checking

    COMPLEXITY:
    TIME: O(N) - SINGLE DFS
    SPACE: O(H) - RECURSION STACK

    BEST FOR: Interview and production use
    */

    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // Use array to simulate pass-by-reference for total sum
        long[] totalSum = new long[1];
        long[] maxProd = new long[1];

        // This DFS computes sum and checks products
        // But it needs totalSum to check products...
        // We can't know totalSum until we finish

        // Alternative: Compute total sum first, then single DFS
        totalSum[0] = computeTotalSum(root);
        computeMaxProduct(root, totalSum[0], maxProd);

        return (int)(maxProd[0] % MOD);        
    }

    private long computeTotalSum(TreeNode node)
    {
        if(node == null) return 0;
        return node.val + computeTotalSum(node.left) + computeTotalSum(node.right);
    }

    private long computeMaxProduct(TreeNode node, long totalSum, long[] maxProd)
    {
        if(node == null) return 0;

        long leftSum = computeMaxProduct(node.left, totalSum, maxProd);
        long rightSum = computeMaxProduct(node.right, totalSum, maxProd);
        long subtreeSum = node.val + leftSum + rightSum;

        // Check product if this is not the whole tree
        if(subtreeSum != totalSum)
        {
            maxProd[0] = Math.max(maxProd[0], subtreeSum * (totalSum - subtreeSum));
        }

        return subtreeSum;
    }
}