//QUESTION: 238. Product of Array Except Self
//LINK: https://leetcode.com/problems/product-of-array-except-self/description/
//
//SOLUTION : PREFIX & SUFFIX
//TIME & SPACE : O(N) , O(1)

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // step 1: build prefix product in result arr
        result[0] = 1;
        for(int i = 1; i < n; i++)
        {
            result[i] = result[i-1] * nums[i-1];
        }

        // Step 2: multi with suffix product from right to left
        int suffix = 1;
        for(int i = n-1; i >= 0; i--)
        {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }
}