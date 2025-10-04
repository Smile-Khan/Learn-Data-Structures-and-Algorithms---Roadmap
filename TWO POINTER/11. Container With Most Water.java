// QUESTION: 11. Container With Most Water
// LINK: https://leetcode.com/problems/container-with-most-water/description/?envType=daily-question&envId=2025-10-04

// SOLUTION: TWO POINTER 


class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;

        int maxArea = 0;
        while(left <= right)
        {
            int width = right - left;
            int minHeight = Math.min(height[left] , height[right]);
            int area = width * minHeight;
            maxArea = Math.max(maxArea, area);
        
            if(height[left] < height[right])
            {
                left++;
            }
            else 
            {
                right--;
            }

        }
        return maxArea;
    }
}