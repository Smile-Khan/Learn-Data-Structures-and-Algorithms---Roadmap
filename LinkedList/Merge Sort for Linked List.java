// QUESTION: Merge Sort for Linked List
// LINK: https://www.geeksforgeeks.org/problems/sort-a-linked-list/1

// SOLUTION: 

/*
class Node {
    int data;
    Node next;

    Node(int key) {
        data = key;
        next = null;
    }
}
*/

class Solution {
    public Node mergeSort(Node head) {
        // code here
       // Base case : Empty or Single node list
       if(head == null || head.next == null)
       {
           return head;
       }
       
       // Find the middle node
       Node middle = findMiddle(head);
       
       // Split into two halves
       Node left = head;
       Node right = middle.next;
       middle.next = null; // break the connection
       
       // Recursively sort both halves
       Node leftSorted = mergeSort(left);
       Node rightSorted = mergeSort(right);
       
       // Merge the sorted halves
       return merge(leftSorted, rightSorted);
    }
    
    private Node findMiddle(Node head)
    {
        if(head == null) 
        {
            return null;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Move fast 2 steps, slow 1 steps
        while(fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;  // slow is the middle node
    }
    
    private Node merge(Node left, Node right)
    {
        // Create a dummy node to build the merged list
        Node dummy = new Node(0);
        Node current = dummy;
        
        // Compare and merge
        while(left != null && right != null)
        {
            if(left.data <= right.data)
            {
                current.next = left;
                left = left.next;
            }
            else 
            {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        // Attach remaining nodes
        if(left != null)
        {
            current.next = left;
        }
        else 
        {
            current.next = right;
        }
        
        return dummy.next;
    }
}