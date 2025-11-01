// QUESTION: 3217. Delete Nodes From Linked List Present in Array
// LINK: https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/description/?envType=daily-question&envId=2025-11-01


// SOLUTION: HASHSET


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
    public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;

        HashSet<Integer> toRemove = new HashSet<>();
        for(int num : nums)
        {
            toRemove.add(num);
        }

        while(current != null)
        {
            if(toRemove.contains(current.val))
            {
                prev.next = current.next;
            }
            else 
            {
                prev = current;
            }
            current = current.next;
        }
        return dummy.next;
    }
}