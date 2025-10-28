// QUESTION: 3354. Make Array Elements Equal to Zero
// LINK: https://leetcode.com/problems/make-array-elements-equal-to-zero/description/?envType=daily-question&envId=2025-10-28

// SOLUTION: 

class Solution {
    public int countValidSelections(int[] nums) {
        int count = 0;

        // Try all positions with value 0
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] == 0)
            {
                // Try left direction
                if(simulate(nums, i , -1))
                {
                    count++;
                }
                // Try right direction
                if(simulate(nums, i , 1))
                {
                    count++;
                }
            }
        }
        return count;
    }
    private boolean simulate(int[] nums, int start, int direction)
    {

    
    int[] arr = nums.clone();   // Make a copy
    int curr = start;
    int dir = direction;        // 1 for right, -1 for left

    while(curr >= 0 && curr < arr.length)
    {
        if(arr[curr] == 0)
        {
            // Move in current direction
            curr += dir;
        }
        else 
        {
            // Decrement, reverse direction, move
            arr[curr]--;
            dir = -dir;     // reverse
            curr += dir;
        }
    }
    // Check if all elements are 0
    for(int num : arr)
    {
        if(num != 0)
        {
            return false;
        }
        
    }
    return true;
    }
}