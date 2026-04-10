// QUESTION: 3740. Minimum Distance Between Three Equal Elements I
// LINK: https://leetcode.com/problems/minimum-distance-between-three-equal-elements-i/description/?envType=daily-question&envId=2026-04-10

// SOLUTION: BRUTE FORCE

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int minDistance = Integer.MAX_VALUE;

        // Check all possible triples (i, j , k) with i < j < k
        for(int i = 0; i < n - 2; i++)
        {
            for(int j = i + 1; j < n - 1; j++)
            {
                // Early termination: if nums[i] != nums[j]
                if(nums[i] != nums[j]) continue;

                for(int k = j + 1; k < n; k++)
                {
                    // Check if all three are equal
                    if(nums[i] == nums[j] && nums[j] == nums[k])
                    {
                        int distance = Math.abs(i - j) + Math.abs(j - k) + Math.abs(k - i);

                        minDistance = Math.min(minDistance, distance);
                    }
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}

// APPROACH 2: HASHMAP

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int answer = Integer.MAX_VALUE;

        // Step 1: Store indices for each value
        for(int i = 0; i < nums.length; i++)
        {
            if(!indexMap.containsKey(nums[i]))
            {
                indexMap.put(nums[i], new ArrayList<>());
            }
            indexMap.get(nums[i]).add(i);
        }

        for(int key : indexMap.keySet())
        {
            List<Integer> indices = indexMap.get(key);
            if(indices.size() < 3) continue;
            
            for(int i = 0; i < indices.size() - 2; i++)
            {
                int distance = 2 * (indices.get(i + 2) - indices.get(i));
                answer = Math.min(answer, distance);
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}