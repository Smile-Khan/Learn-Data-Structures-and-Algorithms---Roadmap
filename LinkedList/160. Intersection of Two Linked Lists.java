// QUESTION: 160. Intersection of Two Linked Lists
// LINK: https://leetcode.com/problems/intersection-of-two-linked-lists/description/

// SOLUTION: 

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        
        // When pointer reaches end, redirect to other list's head
        while (pointerA != pointerB) {
            // If A reaches end, start from headB; otherwise continue in A
            pointerA = (pointerA == null) ? headB : pointerA.next;
            
            // If B reaches end, start from headA; otherwise continue in B  
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }
        
        // Either intersection node or null (if no intersection)
        return pointerA;
    }
}