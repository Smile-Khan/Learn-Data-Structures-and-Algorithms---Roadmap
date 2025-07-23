QUESTION: 525. Contiguous Array
LINK: https://leetcode.com/problems/contiguous-array/description/

SOLUTION:

class Solution {
    public int findMaxLength(int[] nums) {

        // Convert 0 to -1;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] == 0)
                nums[i] = -1;
        }

        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLen = 0;

        for(int i = 0; i < nums.length; i++)
        {
            prefixSum += nums[i];
            if(map.containsKey(prefixSum))
            {
                int prevInd = map.get(prefixSum);
                maxLen = Math.max(maxLen, i - prevInd);
            }
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);  // Store only the first occurrence!
            }
        }
        return maxLen;
    }
}