// QUESTION: Max Circular Subarray Sum

// LINK: https://www.geeksforgeeks.org/problems/max-circular-subarray-sum-1587115620/1

// SOLUTION: 

class Solution {
    public int maxCircularSum(int arr[]) {
        // code here
        int totalSum = 0;
        int currMax = arr[0];
        int currMin = arr[0];
        int maxSum = arr[0];
        int minSum = arr[0];
        
        for(int i = 1; i < arr.length; i++)
        {
            int num = arr[i];
            totalSum += num;
            
            // max subarray
            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);
            
            // min subarray
            currMin = Math.min(num, currMin + num);
            minSum = Math.min(minSum, currMin);
        }
        totalSum += arr[0];
        
        if(maxSum < 0) return maxSum;
        
        return Math.max(maxSum, totalSum - minSum);
    }
}
