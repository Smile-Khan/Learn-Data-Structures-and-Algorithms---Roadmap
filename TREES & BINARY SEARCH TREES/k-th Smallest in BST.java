// QUESTION: k-th Smallest in BST

// LINK: https://www.geeksforgeeks.org/problems/find-k-th-smallest-element-in-bst/1

// SOLUTION: Inorder Traversal (Iterative Approach using Stack)

/*
class Node {
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    public int kthSmallest(Node root, int k) {
        // code here
        Stack<Node> stack = new Stack<>();
        Node current = root;
        int count = 0;
        
        while (current != null || !stack.isEmpty()) {
            // Go to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Process node
            current = stack.pop();
            count++;
            
            if (count == k) {
                return current.data;
            }
            
            // Move to right subtree
            current = current.right;
        }
        
        return -1; // k is larger than number of nodes
    
    }
}