// QUESTION: Minimum Cost to Cut a Stick of length N
// LINK: https://www.geeksforgeeks.org/problems/minimum-cost-to-cut-a-stick-of-length-n/1

// SOLUTION: Memoization (Top-Down DP)


class Solution {
    public int minCutCost(int n, int[] cuts) {
        List<Integer> cutList = new ArrayList<>();
        for (int cut : cuts) cutList.add(cut);
        cutList.add(0);
        cutList.add(n);
        Collections.sort(cutList);
        
        int m = cutList.size();
        Integer[][] memo = new Integer[m][m];
        return memoHelper(cutList, 0, m - 1, memo);
    }
    
    private int memoHelper(List<Integer> cuts, int left, int right, Integer[][] memo) {
        if (right - left <= 1) return 0;
        if (memo[left][right] != null) return memo[left][right];
        
        int minCost = Integer.MAX_VALUE;
        for (int i = left + 1; i < right; i++) {
            int currentCost = cuts.get(right) - cuts.get(left);
            int leftCost = memoHelper(cuts, left, i, memo);
            int rightCost = memoHelper(cuts, i, right, memo);
            minCost = Math.min(minCost, currentCost + leftCost + rightCost);
        }
        
        return memo[left][right] = minCost;
    }
}