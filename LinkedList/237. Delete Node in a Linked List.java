QUESTION: 237. Delete Node in a Linked List
LINK: https://leetcode.com/problems/delete-node-in-a-linked-list/description/

SOLUTION: O(1) TIME, O(1) SPACE

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public void deleteNode(ListNode node) {
        // Copy the value from next node to current node
        node.val = node.next.val;
        
        // Skip the next node (effectively deleting it)
        node.next = node.next.next;
        
        // That's it! The current node now "represents" what the next node was
    }
}

