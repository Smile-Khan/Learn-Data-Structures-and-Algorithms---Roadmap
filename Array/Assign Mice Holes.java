// QUESTION: Assign Mice Holes
// LINK: https://www.geeksforgeeks.org/problems/assign-mice-holes3053/1

// SOLUTION:

class Solution {
    public int assignHole(int[] mices, int[] holes) {
        // code here
        Arrays.sort(mices);
        Arrays.sort(holes);
        int maxTime = 0;
        for (int i = 0; i < mices.length; i++) {
            int time = Math.abs(mices[i] - holes[i]);
            if (time > maxTime) {
                maxTime = time;
            }
        }
        return maxTime;
    }
};