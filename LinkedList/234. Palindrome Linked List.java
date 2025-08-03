// QUESTION: 234. Palindrome Linked List
// LINK: https://leetcode.com/problems/palindrome-linked-list/

// SOLUTION: 

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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true; // Single node or empty is palindrome
        }
        
        // Step 1: Find the middle of the linked list
        ListNode middle = findMiddle(head);
        
        // Step 2: Reverse the second half
        ListNode secondHalf = reverseList(middle);
        
        // Step 3: Compare first half with reversed second half
        ListNode firstHalf = head;
        ListNode secondHalfCopy = secondHalf; // Keep copy for restoration
        
        boolean isPalin = true;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                isPalin = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        // Step 4: Restore the list (optional but good practice)
        reverseList(secondHalfCopy);
        
        return isPalin;
    }
    
    // Find middle using tortoise and hare
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow; // Returns second middle for even length
    }
    
    // Reverse linked list iteratively
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev; // New head of reversed list
    
    }
}