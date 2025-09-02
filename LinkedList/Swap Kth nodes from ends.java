// QUESTION: Swap Kth nodes from ends
// LINK: https://www.geeksforgeeks.org/problems/swap-kth-node-from-beginning-and-kth-node-from-end-in-a-singly-linked-list/1

// SOLUTION: 
/*
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
    public Node swapKth(Node head, int k) {
        if (head == null) return head;

        // Step 1: find the size of the linked list
        int n = 0;
        Node curr = head;
        while (curr != null) {
            n++;
            curr = curr.next;
        }

        // Step 2: validate k
        if (k > n) return head;

        // Step 3: if both nodes are same, no need to swap
        if (k == n - k + 1) return head;

        // Step 4: find kth node from start
        Node first = head;
        for (int i = 1; i < k; i++) {
            first = first.next;
        }

        // Step 5: find kth node from end => (n-k+1)th from start
        Node second = head;
        for (int i = 1; i < n - k + 1; i++) {
            second = second.next;
        }

        // Step 6: swap the data
        int temp = first.data;
        first.data = second.data;
        second.data = temp;

        return head;
    }
}


// SOLUTION: pointer swapping version

/*
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
    public Node swapKth(Node head, int k) {
        if (head == null) return head;

        // Step 1: count the nodes
        int n = 0;
        Node curr = head;
        while (curr != null) {
            n++;
            curr = curr.next;
        }

        // If k is more than number of nodes
        if (k > n) return head;

        // If kth node from start and end are same
        if (2 * k - 1 == n) return head;

        // Step 2: find kth node from start (currX) and its previous (prevX)
        Node prevX = null, currX = head;
        for (int i = 1; i < k; i++) {
            prevX = currX;
            currX = currX.next;
        }

        // Step 3: find kth node from end (currY) and its previous (prevY)
        Node prevY = null, currY = head;
        for (int i = 1; i < n - k + 1; i++) {
            prevY = currY;
            currY = currY.next;
        }

        // Step 4: if prevX exists, link it to currY else update head
        if (prevX != null) {
            prevX.next = currY;
        } else {
            head = currY;
        }

        // Step 5: if prevY exists, link it to currX else update head
        if (prevY != null) {
            prevY.next = currX;
        } else {
            head = currX;
        }

        // Step 6: swap next pointers
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;

        return head;
    }
}
