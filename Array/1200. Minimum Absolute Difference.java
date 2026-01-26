// QUESTION : 1200. Minimum Absolute Difference
// LINK: https://leetcode.com/problems/minimum-absolute-difference/description/?envType=daily-question&envId=2026-01-26

// SOLUTION: 

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);

        List<List<Integer>> result = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length - 1; i++)
        {
            int currentDiff = arr[i + 1] - arr[i];

            if(currentDiff < minDiff)
            {
                minDiff = currentDiff;
                result.clear();
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
            else if(currentDiff == minDiff) 
            {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        return result;
    }
}