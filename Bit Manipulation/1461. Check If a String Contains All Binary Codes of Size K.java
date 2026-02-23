// QUESTION: 1461. Check If a String Contains All Binary Codes of Size K
// LINK: https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/description/?envType=daily-question&envId=2026-02-23

// SOLUTION: HASHSET

class Solution {
    public boolean hasAllCodes(String s, int k) {
        // If s is too short, impossible to have all codes
        if(s.length() < k) return false;

        Set<String> set = new HashSet<>();

        // Add all substrings of length k to set
        for(int i = 0; i <= s.length() - k; i++)
        {
            set.add(s.substring(i, i + k));
        }

        // Check if we have all 2^k codes
        return set.size() == (1 << k);
    }
}