// QUESTION: 1935. Maximum Number of Words You Can Type
// LINK: https://leetcode.com/problems/maximum-number-of-words-you-can-type/description/?envType=daily-question&envId=2025-09-15

// SOLUTION: 

class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        // Step 1: create a set of Broken Characters
        Set<Character> broken = new HashSet<>();
        for(char ch : brokenLetters.toCharArray())
        {
            broken.add(ch);
        }

        // Step 2: Split the text into words
        String[] words = text.split(" ");  // This splits the string by spaces

        int typeableCount = 0;

        for(String word : words)
        {
            boolean canBeType = true;
            
            // Loop through each character of the current word
            for(char letter : word.toCharArray())
            {
                // Check if this letter is in the broken set
                if(broken.contains(letter))
                {
                    // If it is , this word is not typeable
                    canBeType = false;
                    break; // exit
                }
            }

            if(canBeType)
            {
                typeableCount++;
            }
        }
        return typeableCount;
    }
}