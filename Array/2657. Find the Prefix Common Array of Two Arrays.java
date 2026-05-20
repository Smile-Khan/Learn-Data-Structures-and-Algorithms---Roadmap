// QUESTION: 2657. Find the Prefix Common Array of Two Arrays
// LINK: https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/description/?envType=daily-question&envId=2026-05-20

// SOLUTION: USING HASHSET


class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];

        Set<Integer> seenA = new HashSet<>();
        Set<Integer> seenB = new HashSet<>();

        for(int i = 0; i < n; i++)
        {
            seenA.add(A[i]);
            seenB.add(B[i]);

            int count = 0;
            for(int num : seenA)
            {
                if(seenB.contains(num)) count++;
            }

            result[i] = count;
        }
        return result;
    }
}


// SOLUTION: USING FREQUENCY ARRAY

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        int[] freq = new int[n + 1];

        int common = 0;

        for(int i = 0; i < n; i++)
        {
            freq[A[i]]++;
            if(freq[A[i]] == 2) common++;

            freq[B[i]]++;
            if(freq[B[i]] == 2) common++;

            result[i] = common;
        }
        return result;
    }
}