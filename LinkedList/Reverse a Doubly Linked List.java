QUESTION: Reverse a Doubly Linked List
LINK: https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1

SOLUTION: 
/*
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
*/
class Solution {
    public Node reverse(Node head) {
        // code here
        Node current = head;
        Node temp = null;
        
        while (current != null) {
            // Swap the next and prev pointers
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            
            // Move to the next node (which is now in prev because we swapped)
            current = current.prev;
        }
        
        // Before changing the head, check for the empty list
        if (temp != null) {
            head = temp.prev;
        }
        
        return head;
    }
}