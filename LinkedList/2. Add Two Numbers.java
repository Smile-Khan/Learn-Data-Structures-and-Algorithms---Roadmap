// QUESTION: 2. Add Two Numbers
// LINK: https://leetcode.com/problems/add-two-numbers/description/

// SOLUTION: O(MAX(m,n))

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Dummy node to simplify result construction
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        // Process both lists while either has nodes or carry exists
        while (l1 != null || l2 != null || carry != 0) {
            // Get current digit values (0 if node is null)
            int digit1 = (l1 != null) ? l1.val : 0;
            int digit2 = (l2 != null) ? l2.val : 0;
            
            // Calculate sum of current digits plus carry
            int sum = digit1 + digit2 + carry;
            
            // Update carry for next iteration
            carry = sum / 10;
            
            // Create new node with current digit (sum % 10)
            current.next = new ListNode(sum % 10);
            current = current.next;
            
            // Move to next nodes if they exist
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        // Return result (skip dummy node)
        return dummy.next;
    }
}