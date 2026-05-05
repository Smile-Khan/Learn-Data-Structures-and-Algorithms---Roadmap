// QUESTION: 61. Rotate List
// LINK: https://leetcode.com/problems/rotate-list/description/?envType=daily-question&envId=2026-05-05

// SOLUTION: Approach 1: Two Passes (O(n) time, O(1) space)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;

        // Find length
        int length = 1;
        ListNode tail = head;
        while(tail.next != null)
        {
            tail = tail.next;
            length++;
        }

        k = k % length;
        if(k == 0) return head;

        // Two pointers 
        ListNode slow = head;
        ListNode fast = head;

        // Move fasst k steps ahead
        for(int i = 0; i < k; i++)
        {
            fast = fast.next;
        }

        // Move both until fast reaches end
        while(fast.next != null)
        {
            slow = slow.next;
            fast = fast.next;
        }

        // Rotate 
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
    }
}


// APPROACH 2: Make Circular + Break

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0)
        {
            return head;
        }

        ListNode tail = head;
        int length = 1;

        while(tail.next != null)
        {
            tail = tail.next;
            length++;
        }

        k = k % length;
        if(k == 0) return head; 

        // Connect tail to head (make circular)
        tail.next = head;

        // Find new head (at position length - k)
        int stepsToNewHead = length - k;
        ListNode newTail = head;

        for(int i = 1; i < stepsToNewHead; i++)
        {
            newTail = newTail.next;
        }

        // Break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}