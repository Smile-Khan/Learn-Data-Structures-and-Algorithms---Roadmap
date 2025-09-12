// QUESTION: 3227. Vowels Game in a String
// LINK: https://leetcode.com/problems/vowels-game-in-a-string/description/?envType=daily-question&envId=2025-09-12

// SOLUTION: 

class Solution {
    public boolean doesAliceWin(String s) {
        
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
        }
        return false;
    }
}