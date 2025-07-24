QUESTION: Last Moment Before All Ants Fall Out
LINK: https://www.geeksforgeeks.org/problems/last-moment-before-all-ants-fall-out-of-a-plank/1


SOLUTION:

class Solution {
    public int getLastMoment(int n, int left[], int right[]) {
        // code here
        int maxLeft = 0;
        int maxRight = 0;

        if (left.length > 0) {
            maxLeft = Arrays.stream(left).max().getAsInt();
        }

        if (right.length > 0) {
            maxRight = n - Arrays.stream(right).min().getAsInt();
        }

        return Math.max(maxLeft, maxRight);
    }
}