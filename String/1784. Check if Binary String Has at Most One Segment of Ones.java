QUESTION: 1784. Check if Binary String Has at Most One Segment of Ones
LINK: https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/description/?envType=daily-question&envId=2026-03-06

SOLUTION: 

class Solution {
    public boolean checkOnesSegment(String s) {
        
        boolean foundZeroAfterOne = false;
        for(int i = 0; i < s.length()-1; i++)
        {
            if(s.charAt(i) == '1' && s.charAt(i+1) == '0')
            {
                foundZeroAfterOne = true;
            }

            if(foundZeroAfterOne && s.charAt(i) == '0' && s.charAt(i + 1) == '1')
            {
                return false;
            }
        }
        return true;
    }
}


// APPROACH 2: 

class Solution {
    public boolean checkOnesSegment(String s) {
        // If we find "01" and later find "1", then it's invalid

        int firstZero = s.indexOf('0');
        if(firstZero == -1) return true;    // No zeros, all ones

        // After first zero, there should be no more ones
        return s.indexOf('1', firstZero + 1) == -1;
    }
}