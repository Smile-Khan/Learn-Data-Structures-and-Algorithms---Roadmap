QUESTION: 138. Copy List with Random Pointer
LINK : https://leetcode.com/problems/copy-list-with-random-pointer/description/

SOLUTION: HASHMAP

class Node {
    int val;
    Node next;
    Node random;
    
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // HashMap to store mapping: original -> copy
        Map<Node, Node> map = new HashMap<>();
        
        // First pass: Create all new nodes and store mapping
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }
        
        // Second pass: Set next and random pointers
        current = head;
        while (current != null) {
            Node copyNode = map.get(current);
            
            // Set next pointer
            copyNode.next = map.get(current.next);
            
            // Set random pointer  
            copyNode.random = map.get(current.random);
            
            current = current.next;
        }
        
        return map.get(head);
    }
}

// APPROACH 2 : INTERWEAVING (O(1) SPACE)

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // Step 1: Create copy nodes and interweave with original
        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next; // Move to next original node
        }
        
        // Step 2: Set random pointers for copy nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next; // Skip copy node
        }
        
        // Step 3: Extract copy nodes to form new list
        Node dummy = new Node(0);
        Node copyPrev = dummy;
        current = head;
        
        while (current != null) {
            Node copy = current.next;
            
            // Restore original list
            current.next = copy.next;
            
            // Build copy list
            copyPrev.next = copy;
            copyPrev = copy;
            
            current = current.next;
        }
        
        return dummy.next;
    }
}

// APPROACH 3 : RECURSIVE WITH MEMOIZATION

class Solution {
    private Map<Node, Node> memo = new HashMap<>();
    
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // If already created, return cached copy
        if (memo.containsKey(head)) {
            return memo.get(head);
        }
        
        // Create new node
        Node copy = new Node(head.val);
        memo.put(head, copy);
        
        // Recursively set pointers
        copy.next = copyRandomList(head.next);
        copy.random = copyRandomList(head.random);
        
        return copy;
    }
}