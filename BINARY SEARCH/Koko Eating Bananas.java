// QUESTION: Koko Eating Bananas
// LINK: https://www.geeksforgeeks.org/problems/koko-eating-bananas/1

// SOLUTION: 

class Solution {
    public int kokoEat(int[] arr, int k) {
        // code here
        int max = 0;
        for(int bananas : arr)
        {
            max = Math.max(max, bananas);
        }
        
        int left = 1;
        int right = max;
        int answer = max;
        
        while(left <= right)
        {
            int mid = left + (right - left) / 2;
            
            if(canFinish(arr, mid, k))
            {
                answer = mid;
                right = mid - 1;
            }
            else
            {
                left = mid + 1;
            }
        }
        return answer;
    }
    private boolean canFinish(int[] arr, int s, int k)
    {
        long totalHours = 0;
        
        for(int pile : arr)
        {
            totalHours += (pile + s - 1) / s;
            if(totalHours > k)
            {
                return false;
            }
        }
        return totalHours <= k;
    }
}
