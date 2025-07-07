//QUESTION :- 560. Subarray Sum Equals K
//LINK :- https://leetcode.com/problems/subarray-sum-equals-k/description/

SOLUTION:-
O(n)
O(1)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;

        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0,1);
        for(int num : nums)
        {
            prefixSum += num;

            if(prefixSumMap.containsKey(prefixSum - k))
            {
                count += prefixSumMap.get(prefixSum - k);
            }

            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum,0)+1);
        }
        return count;
    }
}