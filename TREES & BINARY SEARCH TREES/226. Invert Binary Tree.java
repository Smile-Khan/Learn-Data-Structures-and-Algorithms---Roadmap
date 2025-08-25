// // QUESTION: 226. Invert Binary Tree
// // LINK: https://leetcode.com/problems/invert-binary-tree/description/


// // SOLUTION:

// Approach 1: Recursive (Post-order) - Most Common

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

public class Solution {
    public TreeNode invertTree(TreeNode root)
    {
        // Base case: empty node
        if(root == null)
        {
            return null;
        }

        // Recusively invert left and right subtrees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // Swap the inverted subtrees
        root.left = right;
        root.right = left;

        return root;
    }
}

// Approach 2: Recursive (Pre-order)- swap first

public class Solution {
    public TreeNode invertTree(TreeNode root)
    {
        if(root == null)
        return null;

        // Swap children first
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Then recursively invert subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}


//Approach 3: One-liner Recursive

public class Solution{
    public TreeNode invertTree(TreeNode root)
    {
        if(root == null)
        {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }
}

// Approach 4: Iterative (BFS with Queue)

}
import java.util.*;

public class Solution {
    public TreeNode invertTree(TreeNode root)
    {
        if(root == null)
        {
            return null;
        } 

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty())
        {
            TreeNode node = queue.poll();

            // swap left and right children
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            // Add children to queue for processing
            if(node.left != null)
            {
                queue.offer(node.left);
            }

            if(node.right != null)
            {
                queue.offer(node.right);
            }
        }
        return root;
    }
}


// Approach 5: Iterative (DFS with Stack)

import java.util.*;

public class Solution {
    public TreeNode invertTree(TreeNode root)
    {
        if(root == null)
        {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty())
        {
            TreeNode node = stack.pop();

            // swap left and right children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // Add children to stack

            if(node.left != null)
            {
                stack.push(node.left);
            }

            if(node.right != null)
            {
                stack.push(node.right);
            }
        }
        return root;
    }
}