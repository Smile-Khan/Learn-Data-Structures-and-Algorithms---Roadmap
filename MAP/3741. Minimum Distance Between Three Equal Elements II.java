// QUESTION: 3741. Minimum Distance Between Three Equal Elements II
// LINK: https://leetcode.com/problems/minimum-distance-between-three-equal-elements-ii/description/?envType=daily-question&envId=2026-04-11

// SOLUTION: HASHMAP


class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

        Map<Integer, List<Integer>> positions = new HashMap<>();

        for(int i = 0; i < n; i++)
        {
            positions.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDistance = Integer.MAX_VALUE;

        // For each number that appears at least 3 times

        for(List<Integer> indices : positions.values())
        {
            if(indices.size() >= 3)
            {
                // Check every window of 3 consecutive indices
                for(int i = 0; i <= indices.size() - 3; i++)
                {
                    int first = indices.get(i);
                    int third = indices.get(i + 2);
                    int distance = 2 * (third - first);

                    minDistance = Math.min(minDistance, distance);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}