// QUESTION: Postorder Traversal
// LINK: https://www.geeksforgeeks.org/problems/postorder-traversal/1

// SOLUTION: Recursive + Iterative

/*
class Node {
    int data;
    Node left, right;
    Node(int val){
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    public ArrayList<Integer> postOrder(Node root) {
        // code here
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        
        stack1.push(root);
        
        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);
            
            // Push left child first, then right child
            // So they'll be popped in reverse order from stack2
            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }
        
        // stack2 now contains nodes in reverse postorder
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().data);
        }
        
        return result;
    }
}

// APPROACH 2:

import java.util.*;

class Solution {
    public ArrayList<Integer> postOrder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node lastVisited = null;
        
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node peekNode = stack.peek();
                
                // If right child exists and hasn't been processed yet
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    // Process the node
                    result.add(peekNode.data);
                    lastVisited = stack.pop();
                }
            }
        }
        
        return result;
    }
}