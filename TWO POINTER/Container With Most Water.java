// QUESTION: Container With Most Water
// LINK: https://www.geeksforgeeks.org/problems/container-with-most-water0535/1

// SOLUTION: TWO POINTER
// TIME: O(N)
// SPACE: O(1)

class Solution{
    pulic int maxWater(int arr[])
    {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int maxArea = 0;

        while(left < right)
        {
            int widht = right - left;
            int height = Math.min(arr[left], arr[right]);
            int area = width * height;

            maxArea = Math.max(maxArea, area);

            if(arr[left] < arr[right])
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