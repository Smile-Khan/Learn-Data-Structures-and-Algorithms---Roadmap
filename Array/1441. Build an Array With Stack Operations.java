QUESTION: 1441. Build an Array With Stack Operations
LINK: https://leetcode.com/problems/build-an-array-with-stack-operations/description/

SOLUTION: SIMULATION

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> operations = new ArrayList<>();
        int stream = 1;

        // We only care about the numbers that lead us to our targets
        for(int t : target)
        {
            // While the stream hasn't caught up to the target,
            // We must perform the "Push-Pop" dance for the weak numbers.
            while(stream < t)
            {
                operations.add("Push");
                operations.add("Pop");
                stream++;
            }
            // Now the stream matches the target-keep it
            operations.add("Push");
            stream++;
        }
        return operations;
    }
}