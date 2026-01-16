QUESTION: 2975. Maximum Square Area by Removing Fences From a Field
LINK: https://leetcode.com/problems/maximum-square-area-by-removing-fences-from-a-field/description/?envType=daily-question&envId=2026-01-16

SOLUTION: class Solution {
    private static final int MOD = 1_000_000_007;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Step 1: Combine horizontal internal fences with boundaries
        int[] hLines = new int[hFences.length + 2];
        hLines[0] = 1;
        hLines[1] = m;
        for (int i = 0; i < hFences.length; i++) hLines[i + 2] = hFences[i];

        // Step 2: Store all possible horizontal side lengths in a Set
        Set<Integer> hDiffs = new HashSet<>();
        for (int i = 0; i < hLines.length; i++) {
            for (int j = i + 1; j < hLines.length; j++) {
                hDiffs.add(Math.abs(hLines[i] - hLines[j]));
            }
        }

        // Step 3: Combine vertical internal fences with boundaries
        int[] vLines = new int[vFences.length + 2];
        vLines[0] = 1;
        vLines[1] = n;
        for (int i = 0; i < vFences.length; i++) vLines[i + 2] = vFences[i];

        // Step 4: Check vertical side lengths against the horizontal set
        long maxSide = -1;
        for (int i = 0; i < vLines.length; i++) {
            for (int j = i + 1; j < vLines.length; j++) {
                int diff = Math.abs(vLines[i] - vLines[j]);
                if (hDiffs.contains(diff)) {
                    maxSide = Math.max(maxSide, (long) diff);
                }
            }
        }

        // Return the area or -1 if no square can be forged
        return (maxSide == -1) ? -1 : (int) ((maxSide * maxSide) % MOD);
    }
}