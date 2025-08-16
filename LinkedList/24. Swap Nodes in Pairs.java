// QUESTION: 24. Swap Nodes in Pairs
// LINK: https://leetcode.com/problems/swap-nodes-in-pairs/description/

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
    public ListNode swapPairs(ListNode head) {
        // Base case
        if(head == null || head.next == null)
        {
            return head;
        }
        // Nodes to be swapped
        ListNode first = head;
        ListNode second = head.next;

        // Recursively swap the remaining list
        first.next = swapPairs(second.next);

        // Complete the swap
        second.next = first;

        // New head is the second node
        return second;
    }
}