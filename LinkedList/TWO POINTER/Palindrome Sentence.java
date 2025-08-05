// QUESTION: Palindrome Sentence
// LINK: https://www.geeksforgeeks.org/problems/string-palindromic-ignoring-spaces4723/1


// SOLUTION : TWO POINTER 

class Solution {
    public boolean isPalinSent(String s) {
        // code here
        int n = s.length();
        int left = 0;
        int right = n-1;
        
        while(left < right)
        {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left)))
            {
                left++;
            }
            // skip non alpha numeric form right
            
            while(left < right && !Character.isLetterOrDigit(s.charAt(right)))
            {
                right--;
            }
            // Compare lower case 
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
            {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}