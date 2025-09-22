// QUESTION: 3005. Count Elements With Maximum Frequency
// LINK: https://leetcode.com/problems/count-elements-with-maximum-frequency/description/?envType=daily-question&envId=2025-09-22

// SOLUTION: 

class Solution {
    public int maxFrequencyElements(int[] nums) {
        
        
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums)
        {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        for(int freq : freqMap.values())
        {
            maxFreq = Math.max(maxFreq, freq);
        }

        int totalCount = 0;
        for(int freq : freqMap.values())
        {
            if(freq == maxFreq)
            {
                totalCount += freq;
            }
        }
        return totalCount;
    }
}