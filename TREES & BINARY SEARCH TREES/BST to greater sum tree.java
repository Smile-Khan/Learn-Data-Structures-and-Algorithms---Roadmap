// QUESTION: BST to greater sum tree
// LINK:https://www.geeksforgeeks.org/problems/bst-to-greater-sum-tree/1

// SOLUTION: Reverse Inorder Traversal

/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
} */

class Solution {
    public static void transformTree(Node root) {
        if (root == null) return;
        
        int sum = 0;
        Node current = root;
        Stack<Node> stack = new Stack<>();
        
        while (current != null || !stack.isEmpty()) {
            // Go to the rightmost node
            while (current != null) {
                stack.push(current);
                current = current.right;
            }
            
            // Process the node
            current = stack.pop();
            
            // Store original value and update node
            int temp = current.data;
            current.data = sum;
            sum += temp;
            
            // Move to left subtree
            current = current.left;
        }
    }
}