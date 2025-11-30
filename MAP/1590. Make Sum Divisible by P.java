// QUESTION: 1590. Make Sum Divisible by P
// LINK: https://leetcode.com/problems/make-sum-divisible-by-p/description/?envType=daily-question&envId=2025-11-30


// SOLUTION: Prefix Sum + HashMap 

class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;

        // Find the target Remainder
        long totalSum = 0;
        for(int num : nums)
        {
            totalSum += num;
        }

        int rem = (int)(totalSum % p);

        // If the sum is already divisible, the fight is over
        if(rem == 0)
        {
            return 0;
        }

        // Hunt for the shortest subArray
        Map<Integer,Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);    // Base case: a sum of 0 exists at index -1

        int minLength = n;  // Initialize to max possible length
        long currentSum = 0;    // Use a 'long' for the running sum

        // One loop. One pass
        for(int j = 0; j < n; j++)
        {
            currentSum += nums[j];

            int currentRem = (int)(currentSum % p);

            // Calculate the remainder we're looking for
            int targetRem = (currentRem - rem + p) % p;

            // Check if we've seen this target remainder before
            if(remainderMap.containsKey(targetRem))
            {
                int i = remainderMap.get(targetRem);
                
                minLength = Math.min(minLength, j - i);
            }
            // Record the current remainder and its index
            remainderMap.put(currentRem, j);
        }

        // If minLength is stil n, it means the only solution
        // was to remove the whole array, which is not allowed

        if(minLength == n)
        {
            return -1;
        }

        return minLength;
    }
}