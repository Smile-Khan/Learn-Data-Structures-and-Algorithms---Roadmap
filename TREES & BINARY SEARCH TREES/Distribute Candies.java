// QUESTION: Distribute Candies
// LINK: https://www.geeksforgeeks.org/problems/distribute-candies-in-a-binary-tree/1

// SOLUTION: DFS Approach (Recursion) 

/*
class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    int totalMoves = 0;
    public int distCandy(Node root) {
        // code here
        
        
        dfs(root);
        return totalMoves;
    }
    private int dfs(Node root)
    {
        if(root == null)
        {
            return 0;
        }
        
        int leftBalance = dfs(root.left);
        int rightBalance = dfs(root.right);
        
        totalMoves += Math.abs(leftBalance) + Math.abs(rightBalance);
        
        return (root.data - 1) + leftBalance + rightBalance;
    }
}