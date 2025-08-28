// QUESTION: Maximize Number of 1's
// LINK: https://www.geeksforgeeks.org/problems/maximize-number-of-1s0905/1

// SOLUTION:


class Solution {
    public int maxOnes(int arr[], int k) {
        // code here
        int n = arr.length;
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;
        
        for(int right = 0; right < n; right++)
        {
            if(arr[right] == 0)
            {
                zeroCount++;
            }
            
            
            while(zeroCount > k)
            {
                if(arr[left] == 0)
                {
                    zeroCount--;
                }
                left++;                 // Always move left forward when shrinking
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}