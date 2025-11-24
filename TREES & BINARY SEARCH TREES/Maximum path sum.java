// QUESTION: Maximum path sum
// LINK: https://www.geeksforgeeks.org/problems/maximum-path-sum-from-any-node/1

// SOLUTION: 

/*
class Node{
    int data;
    Node left, right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
*/

class Solution {
    // Global variable
    int maxPathSum = Integer.MIN_VALUE;
    
    int findMaxSum(Node root) {
        // code here
        findMaxPath(root);
        return maxPathSum;
    }
    private int findMaxPath(Node node)
    {
        if(node == null)
        {
            return 0;
        }
        
        int leftGain = Math.max(0, findMaxPath(node.left));
        int rightGain = Math.max(0, findMaxPath(node.right));
        
        int currentPathSum = node.data + leftGain + rightGain;
        
        maxPathSum = Math.max(maxPathSum, currentPathSum);
        
        return node.data + Math.max(leftGain, rightGain);
    }
}