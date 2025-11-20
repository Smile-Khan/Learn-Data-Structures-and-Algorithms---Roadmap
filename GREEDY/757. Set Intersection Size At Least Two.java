// QUESTION: 757. Set Intersection Size At Least Two
// LINK: https://leetcode.com/problems/set-intersection-size-at-least-two/description/?envType=daily-question&envId=2025-11-20

// SOLUTION: Greedy Algorithm + Sorting

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Sort intervals by end point (ascending)
        // If same end point, sort by start point (descending)

        Arrays.sort(intervals, (a, b) -> {
            if(a[1] != b[1])
            {
                return Integer.compare(a[1], b[1]);
            }
            else 
            {
                return Integer.compare(b[0], a[0]);
            }
        });

        int totalPoints = 0;
        int first = -1;
        int second = -1;

        for(int[] interval : intervals)
        {
            int start = interval[0];
            int end = interval[1];

            if(start > second)
            {
                // Need to add two new points for this interval
                totalPoints += 2;
                first = end - 1;
                second = end;
            }
            else if(start > first)
            {
                // Need to add one new point for this interval
                totalPoints++;
                first = second;
                second = end;
            }
        }
        return totalPoints;
    }
}