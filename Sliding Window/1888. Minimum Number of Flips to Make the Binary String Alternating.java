// QUESTION: 1888. Minimum Number of Flips to Make the Binary String Alternating
// LINK: https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/?envType=daily-question&envId=2026-03-07

// SOLUTION: SLIDING WINDOW

class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String doubled = s + s;

        // Built target pattern of length 2n
        StringBuilder pattern0 = new StringBuilder();
        StringBuilder pattern1 = new StringBuilder();

        for(int i = 0; i < 2 * n; i++)
        {
            pattern0.append(i % 2 == 0 ? '0' : '1');
            pattern1.append(i % 2 == 0 ? '1' : '0');
        }

        // initialize first window
        int diff0 = 0; 
        int diff1 = 0;

        for(int i = 0; i < n; i++)
        {
            if(doubled.charAt(i) != pattern0.charAt(i)) diff0++;

            if(doubled.charAt(i) != pattern1.charAt(i)) diff1++;
        }

        int minFlips = Math.min(diff0, diff1);

        //Slide the window
        for(int i = n; i < 2 * n; i++)
        {
            // Remove leftmost character of previous window
            if (doubled.charAt(i - n) != pattern0.charAt(i - n)) diff0--;
            if (doubled.charAt(i - n) != pattern1.charAt(i - n)) diff1--;
            
            // Add new character
            if (doubled.charAt(i) != pattern0.charAt(i)) diff0++;
            if (doubled.charAt(i) != pattern1.charAt(i)) diff1++;
            
            minFlips = Math.min(minFlips, Math.min(diff0, diff1));
        }
        return minFlips;
    }
}