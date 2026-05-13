QUESTION: 1674. Minimum Moves to Make Array Complementary
LINK: https://leetcode.com/problems/minimum-moves-to-make-array-complementary/description/?envType=daily-question&envId=2026-05-13

SOLUTION: class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int pairs = n / 2;
        int[] diff = new int[2 * limit + 5];

        for(int i = 0; i < pairs; i++)
        {
            int a = nums[i];
            int b = nums[n - 1 - i];
            int sum = a + b;
            int low = Math.min(a, b) + 1;
            int high = Math.max(a, b) + limit;

            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            diff[low] -= 1;
            diff[high + 1] += 1;

            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }
        int minMoves = Integer.MAX_VALUE;
        int curr = 0;
        for(int s = 2; s <= 2 * limit; s++)
        {
            curr += diff[s];
            minMoves = Math.min(minMoves , curr);
        }
        return minMoves;
    }
}
