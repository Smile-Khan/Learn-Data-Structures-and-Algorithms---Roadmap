// QUESTION: Sort a linked list of 0s, 1s and 2s

// LINK: https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1

// SOLUTION: 

/*
class Node {
    int data;
    Node next;

    Node(int d)
    {
        data = d;
        next = null;
    }
}*/

class Solution {
    public Node segregate(Node head) {
        // code here
         if (head == null) return null;
        
        int count0 = 0, count1 = 0, count2 = 0;
        Node current = head;
        
        // Count the number of 0s, 1s, and 2s
        while (current != null) {
            if (current.data == 0) {
                count0++;
            } else if (current.data == 1) {
                count1++;
            } else if (current.data == 2) {
                count2++;
            }
            current = current.next;
        }
        
        current = head;
        
        // Set the first count0 nodes to 0
        while (count0 > 0) {
            current.data = 0;
            current = current.next;
            count0--;
        }
        
        // Set the next count1 nodes to 1
        while (count1 > 0) {
            current.data = 1;
            current = current.next;
            count1--;
        }
        
        // Set the remaining nodes to 2
        while (count2 > 0) {
            current.data = 2;
            current = current.next;
            count2--;
        }
        
        return head;
    }
}