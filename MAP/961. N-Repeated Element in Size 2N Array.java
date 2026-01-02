// QUESTION: 961. N-Repeated Element in Size 2N Array
// LINK: https://leetcode.com/problems/n-repeated-element-in-size-2n-array/description/?envType=daily-question&envId=2026-01-02


// SOLUTION: HASHMAP 

class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums)
        {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if(entry.getValue() > 1)

            {
                return entry.getKey();
            }
        }
        return -1;

    }
}


//SOLUTION: SET


class Solution {
    public int repeatedNTimes(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for(int num : nums)
        {
            if(!seen.add(num))
            {
                return num;
            }
        }
        return -1;
    }
}


// SOLUTION: OPTIMAL - NO EXTRA SPACE

class Solution {
    public int repeatedNTimes(int[] nums) {
        for(int i = 2; i < nums.length; i++)
        {
            if(nums[i] == nums[i - 1] || nums[i] == nums[i - 2])
            {
                return nums[i];
            }
        }
        return nums[0];
    }
}