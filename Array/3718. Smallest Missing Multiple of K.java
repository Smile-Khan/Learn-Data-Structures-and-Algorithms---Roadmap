// QUESTION: 3718. Smallest Missing Multiple of K
// LINK: https://leetcode.com/problems/smallest-missing-multiple-of-k/description/

// SOLUTION: GREEDY

class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums)
        {
            set.add(num);
        }

        int currentMultiple = k;

        while(true)
        {
            if(set.contains(currentMultiple))
            {
                currentMultiple = currentMultiple + k;
            }
            else 
            {
            return currentMultiple;
            }
        }
    }
}