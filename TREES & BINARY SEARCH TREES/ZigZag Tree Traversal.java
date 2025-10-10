QUESTION: ZigZag Tree Traversal
LINK: https://www.geeksforgeeks.org/problems/zigzag-tree-traversal/1

SOLUTION: Iterative Approach (Using 2 Stacks)

/*
class Node {
    int data;
    Node left,right;
    Node(int d)
    {
        data=d;
        left=right=null;
    }
}
*/

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root) {
        // code here
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        
        Stack<Node> currentLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();
        
        currentLevel.push(root);
        boolean leftToRight = true;
        
        while(!currentLevel.isEmpty())
        {
            Node currentNode = currentLevel.pop();
            result.add(currentNode.data);
            
            if(leftToRight)
            {
                // Add left then right (will be popped in reverse order)
                if(currentNode.left != null)
                {
                    nextLevel.push(currentNode.left);
                }
                if(currentNode.right != null)
                {
                    nextLevel.push(currentNode.right);
                }
            }
            else 
            {
                // Add right then left (will be popped in reverse order)
                if(currentNode.right != null)
                {
                    nextLevel.push(currentNode.right);
                }
                
                if(currentNode.left != null)
                {
                    nextLevel.push(currentNode.left);
                }
            }
            
            // Move to next level when current level is empty
            if(currentLevel.isEmpty())
            {
                leftToRight = !leftToRight;
                Stack<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
        return result;
    }
}


// SOLUTION 2: Iterative Approach (Using queUE)

import java.util.*;

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true; // Start with left to right
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            ArrayList<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                currentLevel.add(currentNode.data);
                
                // Add children to queue for next level
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            
            // Add current level to result based on direction
            if (leftToRight) {
                // Add in normal order (left to right)
                result.addAll(currentLevel);
            } else {
                // Reverse and add (right to left)
                Collections.reverse(currentLevel);
                result.addAll(currentLevel);
            }
            
            // Toggle direction for next level
            leftToRight = !leftToRight;
        }
        
        return result;
    }
}