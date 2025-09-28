// QUESTION: 976. Largest Perimeter Triangle
// LINK: https://leetcode.com/problems/largest-perimeter-triangle/description/?envType=daily-question&envId=2025-09-28

// SOLUTION: 

class Solution {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; --i)
            if (A[i] + A[i+1] > A[i+2])
                return A[i] + A[i+1] + A[i+2];
        return 0;
    }
}