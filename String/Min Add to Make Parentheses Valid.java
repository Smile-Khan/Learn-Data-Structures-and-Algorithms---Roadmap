// QUESTION: Min Add to Make Parentheses Valid
// LINK: https://www.geeksforgeeks.org/problems/min-add-to-make-parentheses-valid/1

// SOLUTION: 
class Solution {
    public int minParentheses(String s) {
        // code here
        int n = s.length();
        int unmatchedOpen = 0;
        int needOpen = 0;
        
        for(int i = 0; i < n; i++)
        {
            char ch = s.charAt(i);
            
            if(ch == '(')
            {
                unmatchedOpen++;
            }
            else if(ch == ')')
            {
                if(unmatchedOpen > 0)
                {
                    unmatchedOpen--;
                }
                else 
                {
                    needOpen++;
                }
            }
            else
            {
                return 0;
            }
        }
        return unmatchedOpen + needOpen;
    }
}
