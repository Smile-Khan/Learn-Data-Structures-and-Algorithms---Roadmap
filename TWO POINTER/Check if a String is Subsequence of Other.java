// QUESTION: Check if a String is Subsequence of Other
// LINK: https://www.geeksforgeeks.org/problems/given-two-strings-find-if-first-string-is-a-subsequence-of-second/1

// SOLUTION: TWO POINTER 
// TIME: O(N2)
// SPACE: O(1) 

class Solution {
    public boolean isSubSeq(String s1, String s2) {
        // code here
        int left = 0;
        int right = 0;
        
        int n = s1.length();
        int m = s2.length();
        
        while(left < n && right < m)
        {
            if(s1.charAt(left) == s2.charAt(right))
            {
                left++;
            }
            right++;
        }
        return (left == n);
    }
};