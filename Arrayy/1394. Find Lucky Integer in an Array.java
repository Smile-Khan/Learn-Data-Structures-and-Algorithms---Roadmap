

//PROBLEM TITLE: 1394. Find Lucky Integer in an Array
//LINK: https://leetcode.com/problems/find-lucky-integer-in-an-array/description/?envType=daily-question&envId=2025-07-05


SOLUTION :- USING HASHMAP O(N)

class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        // count freq of each number
        for(int num : arr)
        {
            map.put(num, map.getOrDefault(num,0)+1);
        }

        int maxLucky = -1;

        // Check each number in the map
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            int num = entry.getKey();
            int freq = entry.getValue();

            if(num == freq)
            {
                if(num > maxLucky)
                {
                    maxLucky = num;
                }
            }
        }
        return maxLucky;
    }
}