QUESTION: 3510. Minimum Pair Removal to Sort Array II
LINK: https://leetcode.com/problems/minimum-pair-removal-to-sort-array-ii/description/?envType=daily-question&envId=2026-01-23

SOLUTION: 

import java.util.PriorityQueue;

class Solution {
    class Node {
        long val;
        int originalIndex;
        Node prev, next;
        boolean deleted;
        
        Node(long val, int idx) {
            this.val = val;
            this.originalIndex = idx;
            this.deleted = false;
        }
    }

    class PairSum implements Comparable<PairSum> {
        long sum;
        Node left, right;
        int version; // To handle stale entries
        
        PairSum(long sum, Node left, Node right, int version) {
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.version = version;
        }
        
        @Override
        public int compareTo(PairSum other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            // If sums equal, prioritize by original index for stability
            return Integer.compare(this.left.originalIndex, other.left.originalIndex);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        // 1. Initialize DLL
        Node[] nodes = new Node[n];
        Node head = null, tail = null;
        
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
            if (head == null) {
                head = nodes[i];
                tail = nodes[i];
            } else {
                tail.next = nodes[i];
                nodes[i].prev = tail;
                tail = nodes[i];
            }
        }
        
        // 2. Count initial violations
        int violations = 0;
        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val > curr.next.val) {
                violations++;
            }
            curr = curr.next;
        }
        
        // 3. Initialize PriorityQueue
        PriorityQueue<PairSum> pq = new PriorityQueue<>();
        curr = head;
        int version = 0;
        
        while (curr != null && curr.next != null) {
            pq.offer(new PairSum(curr.val + curr.next.val, curr, curr.next, version++));
            curr = curr.next;
        }
        
        int operations = 0;
        
        // 4. Process while violations > 0
        while (violations > 0) {
            // Pop the min sum pair
            PairSum pair = pq.poll();
            
            // Validate - check if nodes are still active (not deleted)
            if (pair.left.deleted || pair.right.deleted) {
                continue; // Stale entry, skip
            }
            
            // Check if this pair is still adjacent (important!)
            if (pair.left.next != pair.right) {
                continue; // No longer adjacent, stale entry
            }
            
            // Perform merge operation
            operations++;
            
            // Create new merged node
            long newVal = pair.sum;
            Node merged = new Node(newVal, pair.left.originalIndex);
            merged.deleted = false;
            
            // Update DLL connections
            Node leftNeighbor = pair.left.prev;
            Node rightNeighbor = pair.right.next;
            
            // Mark old nodes as deleted
            pair.left.deleted = true;
            pair.right.deleted = true;
            
            // Insert merged node
            merged.prev = leftNeighbor;
            merged.next = rightNeighbor;
            
            if (leftNeighbor != null) {
                leftNeighbor.next = merged;
            } else {
                head = merged; // Update head if needed
            }
            
            if (rightNeighbor != null) {
                rightNeighbor.prev = merged;
            } else {
                tail = merged; // Update tail if needed
            }
            
            // 5. Update violations count
            // Remove old violations involving the merged nodes
            
            // Check left neighbor -> pair.left
            if (leftNeighbor != null && pair.left != null) {
                if (leftNeighbor.val > pair.left.val) {
                    violations--;
                }
            }
            
            // Check pair.left -> pair.right
            if (pair.left.val > pair.right.val) {
                violations--;
            }
            
            // Check pair.right -> rightNeighbor
            if (pair.right != null && rightNeighbor != null) {
                if (pair.right.val > rightNeighbor.val) {
                    violations--;
                }
            }
            
            // Add new violations involving merged node
            
            // Check leftNeighbor -> merged
            if (leftNeighbor != null) {
                if (leftNeighbor.val > merged.val) {
                    violations++;
                }
            }
            
            // Check merged -> rightNeighbor
            if (rightNeighbor != null) {
                if (merged.val > rightNeighbor.val) {
                    violations++;
                }
            }
            
            // 6. Push new adjacent sums to heap
            if (leftNeighbor != null) {
                pq.offer(new PairSum(leftNeighbor.val + merged.val, leftNeighbor, merged, version++));
            }
            if (rightNeighbor != null) {
                pq.offer(new PairSum(merged.val + rightNeighbor.val, merged, rightNeighbor, version++));
            }
        }
        
        return operations;
    }
}