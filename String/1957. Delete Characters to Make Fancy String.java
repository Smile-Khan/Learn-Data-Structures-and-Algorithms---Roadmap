QUESTION: 1957. Delete Characters to Make Fancy String
LINK:- https://leetcode.com/problems/delete-characters-to-make-fancy-string/description/?envType=daily-question&envId=2025-07-21


SOLUTION:


class Solution {
    public String makeFancyString(String s) {
        char prev = s.charAt(0);
        int freq = 1;

        StringBuilder ans = new StringBuilder();

        ans.append(s.charAt(0));

        for(int i = 1; i < s.length(); i++)
        {
            if(s.charAt(i) == prev)
            {
                // If the current charActer is equal to the previous character increment the frequency
                freq++;
            }
            else
            {
                // Otherwise, we can restart the frequency counter with 1, and store the current characters value in prev

                prev = s.charAt(i);
                freq = 1;
            }

            // If the frequency counter has value less than 3, add the character to the answer string
            if(freq < 3)
                ans.append(s.charAt(i));

        }
        return ans.toString();
    }
}