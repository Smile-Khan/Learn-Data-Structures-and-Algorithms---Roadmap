// QUESTION: Bus Conductor
// LINK: https://www.geeksforgeeks.org/problems/bus-conductor--170647/1

// SOLUTION:

class Solution {
    public int findMoves(int[] chairs, int[] passengers) {
        // code here
        int n = chairs.length;
        
        // Sort both arrays
        Arrays.sort(chairs);
        Arrays.sort(passengers);
        
        // Calculate minimum total moves
        long totalMoves = 0; // Use long to avoid overflow
        for (int i = 0; i < n; i++) {
            totalMoves += Math.abs((long)chairs[i] - passengers[i]);
        }
        
        return (int)totalMoves;
    
    }
}
