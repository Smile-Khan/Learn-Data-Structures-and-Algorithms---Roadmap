QUESTION: 3487. Maximum Unique Subarray Sum After Deletion
LINK: https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/description/?envType=daily-question&envId=2025-07-25

SOLUTION: HASHSET
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maxSum(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        boolean negative = true;
        int maxValue = Integer.MIN_VALUE;

        // Check if all are negative
        for(int num : nums)
        {
            if(num >= 0)
            {
                negative = false;
            }
            if(num > maxValue)
            {
                maxValue = num;
            }
        }

        if(negative)
        {
            return maxValue;
        }
        // set to remove duplicates
        for(int num : nums)
        {
            seen.add(num);
        }

        // sum all positive numbers from set
        int sum = 0;
        for(int val: seen)
        {
            if(val > 0)
            {
                sum += val;
            }
        }
        return sum;
    }
}


SOLUTION: CONSTANT TIME USING BOOLEAN ARRAY O(N) O(1)

        import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maxSum(int[] nums) {
        // CONSTANT TIME APPROACH
        boolean[] seen = new boolean[101];
        boolean negative = true;
        int maxValue = Integer.MIN_VALUE;

        // Check if all are negative
        for(int num : nums)
        {
            if(num >= 0)
            {
                negative = false;
            }
            if(num > maxValue)
            {
                maxValue = num;
            }
        }

        if(negative)
        {
            return maxValue;
        }

        for(int num : nums)
        {
            if(num >= 0 && num < 101)
            {
                seen[num] = true;
            }
        }


        int sum = 0;
        for(int i = 1; i < 101; i++)
        {
            if(seen[i])
            {
                sum += i;
            }
        }
        return sum;
    }
}
