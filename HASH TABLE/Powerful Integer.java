QUESTION: Powerful Integer
LINK: https://www.geeksforgeeks.org/problems/powerfull-integer--170647/1


SOLUTION: 

class Solution {
    public int powerfulInteger(int[][] intervals, int k) {
        // code here
        TreeMap<Integer, Integer> events = new TreeMap<>();

        // Step 1: Build events (+1 at start, -1 at end+1)
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            events.put(start, events.getOrDefault(start, 0) + 1);
            events.put(end + 1, events.getOrDefault(end + 1, 0) - 1);
        }

        int active = 0;
        int answer = -1;

        // Step 2: Sweep through positions
        int prevPos = -1;
        for (Map.Entry<Integer, Integer> entry : events.entrySet()) {
            int pos = entry.getKey();

            // if active >= k before updating, mark [prevPos, pos-1] as powerful
            if (active >= k && prevPos != -1) {
                answer = Math.max(answer, pos - 1);
            }

            // update active count at this event
            active += entry.getValue();

            prevPos = pos;
        }

        return answer;

    }
}