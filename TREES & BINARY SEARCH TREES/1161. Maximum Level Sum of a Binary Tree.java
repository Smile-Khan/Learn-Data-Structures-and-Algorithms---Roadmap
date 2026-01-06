// QUESTION: 1161. Maximum Level Sum of a Binary Tree
// LINK: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/?envType=daily-question&envId=2026-01-06

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
    public int maxLevelSum(TreeNode root) {
        /*
        BFS (LEVEL ORDER TRAVERSAL) APPROACH:

        HOW IT WORKS:
        1. Use queue for BFS level-order traversal
        2. Process one level at a time, sum all node values at that level
        3. Track maximum sum and corresponding level

        TIME COMPLEXITY: O(n) - each node processed once
        SPACE COMPLEXITY: O(w) where w = maximum width of tree
                In worst case (complete tree), w = n/2 ≈ O(n)

        
        WHY IT'S OPTIMAL:
        - Natural fit for level-based problems
        - No recursion overhead
        - Easy to understand and implement
        */

        if(root == null)
        {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int maxLevel = 1;   // Current level with maximum sum
        int maxSum = root.val;  // Maximum sum found so far
        int currentLevel = 1;   // Track which level we're processing

        while(!queue.isEmpty())
        {
            int levelSize = queue.size();   // Number of nodes at current level
            int levelSum = 0;

            // Process all nodes at current level
            for(int i = 0; i < levelSize; i++)
            {
                TreeNode currentNode = queue.poll();
                levelSum += currentNode.val;

                // Add children to queue for next level
                if(currentNode.left != null)
                {
                    queue.offer(currentNode.left);
                }

                if(currentNode.right != null)
                {
                    queue.offer(currentNode.right);
                }
            }

            // Update maximum sum and level if needed
            if(levelSum > maxSum)
            {
                maxSum = levelSum;
                maxLevel = currentLevel;
            }

            // Move to next level
            currentLevel++;
        }
        return maxLevel;
    }
}


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
    public int maxLevelSum(TreeNode root) {
        /*
        DFS WITH ARRAY LIST APPROACH:

        HOW IT WORKS:

        1. Use DFS to traverse tree
        2. Store level sums in ArrayList (index = level - 1)
        3. If ArrayList size < level, add new level sums

        ADVANTAGES OVER HASHMAP:
        - No hasing overhead
        - Sequential access for levels
        - More cache-friendly

        TIME COMPLEXITY: O(n);
        SPACE COMPLEXITY: O(h) recursion + O(L) for level sums
        */

        // List to store sums for each level
        // levelSums.get(i) = sum of nodes at level (i + 1)

        List<Integer> levelSums = new ArrayList<>();

        // Perform DFS to populate level sums
        dfsWithList(root, 0, levelSums);

        // Find level with maximum sum (smallest level if tie)
        int maxLevel = 0;   // 0-based index for array list
        for(int i = 1; i < levelSums.size(); i++)
        {
            if(levelSums.get(i) > levelSums.get(maxLevel))
            {
                maxLevel = i;
            }
        }


        // Convert to 1 based level numbering
        return maxLevel + 1;
    }

    private void dfsWithList(TreeNode node, int level, List<Integer> levelSums)
    {
        if(node == null)
        {
            return;
        }


        // Ensure list has space for current level
        if(level >= levelSums.size())
        {
            levelSums.add(0);
        }

        // Update sum for current level
        levelSums.set(level, levelSums.get(level) + node.val);

        // Process children at next level
        dfsWithList(node.left, level + 1, levelSums);
        dfsWithList(node.right, level + 1, levelSums);
    }
}


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
    public int maxLevelSum(TreeNode root) {
        /*
        BFS WITH TWO QUEUES APPROACH:

        HOW IT WORKS:
        1. Use two queues: currentLevel and nextLevel
        2. Process all nodes in currentLevel, sum their values
        3. Add children to nextLevel
        4. Swap queues and repeat


        ADVANTAGES:
        - Clear separation between levels
        - No need to track level size explicitly

        DISADVANTAGES:
        - Uses more space (two queues)
        - Slightly more complex
        */

        if(root == null) return 0;

        Queue<TreeNode> currentLevel = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();
        currentLevel.offer(root);

        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;
        int currentLevelNum = 1;

        while(!currentLevel.isEmpty())
        {
            int levelSum = 0;

            // Process all nodes in current level
            while(!currentLevel.isEmpty())
            {
                TreeNode node = currentLevel.poll();
                levelSum += node.val;

                // Add children to next level
                if(node.left != null) nextLevel.offer(node.left);
                if(node.right != null) nextLevel.offer(node.right);
            }


            // Update maximum sum and level
            if(levelSum > maxSum)
            {
                maxSum = levelSum;
                maxLevel = currentLevelNum;
            }


            // Swap queues for next level
            Queue<TreeNode> temp = currentLevel;
            currentLevel = nextLevel;
            nextLevel = temp;

            // Increment level counter
            currentLevelNum++;
        }

        return maxLevel;
    }
}