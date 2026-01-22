// QUESTION: 3507. Minimum Pair Removal to Sort Array I
// LINK: https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/description/?envType=daily-question&envId=2026-01-22

// SOLUTION: 

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int x : nums) list.add(x);

        int operations = 0;

        while(!isSorted(list))
        {
            int minSum = Integer.MAX_VALUE;
            int minIndex = -1;

            for(int i = 0; i < list.size() - 1; i++)
            {
                int currentSum = list.get(i) + list.get(i + 1);

                if(currentSum < minSum)
                {
                    minSum = currentSum;
                    minIndex = i;
                }
            }

            int combinedPower = list.get(minIndex) + list.get(minIndex + 1);
            list.set(minIndex, combinedPower);
            list.remove(minIndex + 1);

            operations++;
        }
        return operations;
    }
    private boolean isSorted(List<Integer> list)
    {
        for(int i = 0; i < list.size() - 1; i++)
        {
            if(list.get(i) > list.get(i + 1)) return false;
        }
        return true;
    }
}