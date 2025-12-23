// QUESTION: 2054. Two Best Non-Overlapping Events
// LINK: https://leetcode.com/problems/two-best-non-overlapping-events/description/?envType=daily-question&envId=2025-12-23

// SOLUTION: HEAP (PRIORITY QUEUE) + SORTING + ARRAY 


class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a,b) -> Integer.compare(a[0], b[0]));

        int maxProfit = 0;
        int maxValue = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for(int[] event : events)
        {
            int start = event[0];
            int end = event[1];
            int value = event[2];

            while(!pq.isEmpty() && pq.peek()[1] < start)
            {
                maxValue = Math.max(maxValue, pq.poll()[2]);
            }

            maxProfit = Math.max(maxProfit, value + maxValue);

            pq.offer(event);
        }
        return maxProfit;
    }
}