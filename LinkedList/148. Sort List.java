// QUESTION: 148. Sort List
// LINK: https://leetcode.com/problems/sort-list/description/

// SOLUTION: Bottom up approach

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
    public ListNode sortList(ListNode head) {
        // Base Case: if list has 0 or 1 nodes
        if(head == null || head.next == null)
        {
            return head;
        }

        // Get length of the list
        int length = getLength(head);

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Bottom-up approach: merge sublist of size 1, 2, 4, 8...
        for(int size = 1; size < length; size *= 2)
        {
            ListNode prev = dummy;
            ListNode curr = dummy.next;

            while(curr != null)
            {
                // Split into two sublists of given size
                ListNode left = curr;
                ListNode right = split(left, size);
                curr = split(right, size);

                // Merge and connect
                prev = merge(prev, left, right);
            }
        }
        return dummy.next;
    }
    private int getLength(ListNode head)
    {
        int length = 0;
        while(head != null)
        {
            length++;
            head = head.next;
        }
        return length;
    }
    private ListNode split(ListNode head, int size)
    {
        for(int i = 1; i < size && head != null; i++)
        {
            head = head.next;
        }
        
        if(head == null) return null;

        ListNode next = head.next;
        head.next = null;
        return next;
    }
    private ListNode merge(ListNode prev, ListNode l1, ListNode l2)
    {
        while(l1 != null && l2 != null)
        {
            if(l1.val <= l2.val)
            {
                prev.next = l1;
                l1 = l1.next;
            }
            else 
            {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = (l1 != null) ? l1 : l2;
        while(prev.next != null)
        {
        prev = prev.next;
        }

        return prev;
    }
}

// MergeSort 
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
    public ListNode sortList(ListNode head) {
        // Base Case: if list has 0 or 1 nodes
        if(head == null || head.next == null)
        {
            return head;
        }

        // Step 1: split the list into two halves using slow and fast pointers
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Cut the list into tow halves
        ListNode second = slow.next;
        slow.next = null;

        // Step 3: sort each half recursively
        ListNode left = sortList(head);
        ListNode right = sortList(second);

        // Step 4: merge the two sorted values
        return merge(left, right);
    }
    private ListNode merge(ListNode l1, ListNode l2)
    {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                current.next = l1;
                l1 = l1.next;
            }
            else 
            {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if(l1 != null) current.next = l1;
        if(l2 != null) current.next = l2;

        return dummy.next;
    }
}

