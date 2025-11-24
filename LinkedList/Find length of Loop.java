QUESTION: Find length of Loop
LINK: https://www.geeksforgeeks.org/problems/find-length-of-loop/1

SOLUTION: /*
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}
*/

class Solution {
    public int lengthOfLoop(Node head) {
        // code here
        if(head == null || head.next == null)
        {
            return 0;
        }
        
        Node meetingNode = detectLoop(head);
        if(meetingNode == null)
        {
            return 0;
        }
        
        return getLoopLength(meetingNode);
    }
    private Node detectLoop(Node head)
    {
        Node slow = head;
        Node fast = head;
        
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast)
            {
                return slow;
            }
        }
        return null;
    }
    private int getLoopLength(Node meetingNode)
    {
        int length = 1;
        
        Node current = meetingNode.next;
        while(current != meetingNode)
        {
            current = current.next;
            length++;
        }
        return length;
    }
}

