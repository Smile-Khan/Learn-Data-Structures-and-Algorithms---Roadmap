// QUESTION: 2839. Check if Strings Can be Made Equal With Operations I
// LINK: https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-i/description/?envType=daily-question&envId=2026-03-29

// SOLUTION: BRUTE 

class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Get characters at even indices (0 and 2)
        char[] even1 = {s1.charAt(0), s1.charAt(2)};
        char[] even2 = {s2.charAt(0), s2.charAt(2)};

        // Get characters at odd indices (1 and 3)
        char[] odd1 = {s1.charAt(1), s1.charAt(3)};
        char[] odd2 = {s2.charAt(1), s2.charAt(3)};

        // sort both arrays to compare
        Arrays.sort(even1);
        Arrays.sort(even2);
        Arrays.sort(odd1);
        Arrays.sort(odd2);


        // Check if both even and odd sets match
        return Arrays.equals(even1, even2) && Arrays.equals(odd1, odd2);
    }
}

// ALTERNATIVE SOLUTION: OPTMIZED

class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Check even positions: Indices 0 and 2
        boolean evenMatch = (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) || (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0));


        // Check odd positions: indices 1 and 3
        boolean oddMatch = (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) || (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));

        return evenMatch && oddMatch;
    }
}