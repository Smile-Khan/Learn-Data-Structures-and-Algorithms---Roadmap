// QUESTION: 2904. Shortest and Lexicographically Smallest Beautiful String
// LINK: https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/description/?envType=daily-question&envId=2026-08-26

// SOLUTION: SLIDING WINDOW

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        int onesCount = 0;
        int left = 0;

        int minLen = Integer.MAX_VALUE;
        String ans = "";

        for(int right = 0; right < n; right++)
        {
            if(s.charAt(right) == '1')
            {
                onesCount++;
            }


            // Shrink window from left until we have at most k ones
            // and s.charAt(left) is '1'
            while(onesCount == k)
            {
                // Remove leading '0's to make the substring shorter
                while(s.charAt(left) == '0')
                {
                    left++;
                }

                int currentLen = right - left + 1;
                String currentSub = s.substring(left, right + 1);

                if(currentLen < minLen)
                {
                    minLen = currentLen;
                    ans = currentSub;
                }
                else if(currentLen == minLen)
                {
                    if(currentSub.compareTo(ans) < 0)
                    {
                        ans = currentSub;
                    }
                }

                // Move left past the first '1' to find the next valid window
                left++;
                onesCount--;
            }
        }
        return ans;
        
    }
}