// QUESTION: 611. Valid Triangle Number
// LINK: https://leetcode.com/problems/valid-triangle-number/description/?envType=daily-question&envId=2025-09-26

// SOLUTION: BINARY SEARCH

class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int count = 0;

        for(int i= 0; i < n - 2; i++)
        {
            for(int j = i + 1; j < n-1; j++)
            {
                // Find rightmost position where nums[k] <nums[i] + nums[j]
                int sum = nums[i] + nums[j];
                int rightMostValid = findRightMostLess(nums, j+1, n-1, sum);
                if(rightMostValid >= j + 1)
                {
                    count += (rightMostValid - j);
                }
            }
        }
        return count;
    }
    private int findRightMostLess(int[] nums, int left, int right, int target)
    {
        int result = left - 1; // No valid element found;

        while(left <= right)
        {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target)
            {
                result = mid;       // This position is valid
                left = mid + 1;     // Look for larget valid positions
            } 
            else 
            {
                right = mid - 1;
            }
        }
        return result;
    }
}

// SOLUTION 2: TWO POINTERS

class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        int count = 0;
        Arrays.sort(nums);
        
        for(int i = n-1; i >= 2; i--)
        {
            int left = 0;
            int right = i-1;

            while(left < right)
            {
                if(nums[left] + nums[right] > nums[i])
                {
                    count += (right - left);
                    right--;
                }
                else 
                {
                    left++;
                }
            }
        }
        return count;
    }
}