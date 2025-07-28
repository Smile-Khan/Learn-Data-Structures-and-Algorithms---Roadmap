// QUESTION: 25. Reverse Nodes in k-Group
// LINK: https://leetcode.com/problems/reverse-nodes-in-k-group/description/

SOLUTION:

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Dummy head for easier handling
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;
        
        while (hasKNodes(prevGroupEnd.next, k)) {
            // Store the start of current group
            ListNode groupStart = prevGroupEnd.next;
            
            // Reverse k nodes starting from groupStart
            ListNode groupEnd = reverseKNodes(groupStart, k);
            
            // Connect previous group to reversed group
            prevGroupEnd.next = groupEnd;
            
            // groupStart is now the end of reversed group
            // Connect it to the next part of list
            ListNode nextGroupStart = groupStart.next;
            groupStart.next = nextGroupStart;
            
            // Update prevGroupEnd for next iteration
            prevGroupEnd = groupStart;
        }
        
        return dummy.next;
    }
    
    // Check if k nodes are available starting from node
    private boolean hasKNodes(ListNode node, int k) {
        int count = 0;
        while (node != null && count < k) {
            node = node.next;
            count++;
        }
        return count == k;
    }
    
    // Reverse exactly k nodes starting from head
    // Returns the new head of reversed group
    private ListNode reverseKNodes(ListNode head, int k) {
        ListNode prev = null;
        ListNode current = head;
        
        for (int i = 0; i < k; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        // prev is now the new head, current is the next group
        head.next = current; // Connect to remaining list
        return prev; // Return new head of reversed group
    }
}