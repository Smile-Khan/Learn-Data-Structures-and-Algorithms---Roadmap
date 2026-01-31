// QUESTION: 744. Find Smallest Letter Greater Than Target
// LINK: https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/?envType=daily-question&envId=2026-01-31

// SOLUTION: BINARY SEARCH

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;

        while(left <= right)
        {
            int mid = left + (right - left) / 2;

            if(letters[mid] > target)
            {
                right = mid - 1;
            }
            else 
            {
                left = mid + 1;
            }
        }
        return letters[left % letters.length];
    }
}