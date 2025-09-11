// QUESTION: 2785. Sort Vowels in a String
// LINK: https://leetcode.com/problems/sort-vowels-in-a-string/description/?envType=daily-question&envId=2025-09-11


// SOLUTION: 


public class Solution {
    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }

    public String sortVowels(String s) {
        StringBuilder temp = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                temp.append(ch);
            }
        }

        char[] tempArray = temp.toString().toCharArray();
        Arrays.sort(tempArray);

        int j = 0;
        char[] resultArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                resultArray[i] = tempArray[j];
                j++;
            }
        }

        return new String(resultArray);
    }
}