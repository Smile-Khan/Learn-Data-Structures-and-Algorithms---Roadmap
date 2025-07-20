QUESTION :- 1248. Count Number of Nice Subarrays
LINK: https://leetcode.com/problems/count-number-of-nice-subarrays/description/

SOLUTION: PREFIXSUM + HASHMAP

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0,1);

        int prefixSum = 0;
        int count = 0;

        for(int num : nums)
        {
            prefixSum += (num % 2);


            count += prefixCount.getOrDefault(prefixSum - k, 0);

            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0)+1);
        }
        return count;
    }
}


SOLUTION : SLIDING WINDOW (OPTIMIZED)
    O(N)
    O(1)

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums , k - 1);
    }

    private int atMost(int[] nums, int k)
    {
        if(k < 0) return 0;

        int left = 0;
        int sum = 0;
        int count = 0;

        for(int right = 0; right < nums.length; right++)
        {
            sum += (nums[right] % 2);

            while(sum > k)
            {
                sum -= nums[left] % 2;
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }
}