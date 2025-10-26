// QUESTION: 966. Vowel Spellchecker
// LINK: https://leetcode.com/problems/vowel-spellchecker/description/?envType=daily-question&envId=2025-09-14

// SOLUTION: 

import java.util.*;

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactMatch = new HashSet<>();
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelError = new HashMap<>();
        
        for (String word : wordlist) {
            exactMatch.add(word);
            
            String lowerCase = word.toLowerCase();
            caseInsensitive.putIfAbsent(lowerCase, word);
            
            String vowelKey = replaceVowels(lowerCase);
            vowelError.putIfAbsent(vowelKey, word);
        }
        
        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (exactMatch.contains(query)) {
                result[i] = query;
                continue;
            }
            
            String lowerQuery = query.toLowerCase();
            if (caseInsensitive.containsKey(lowerQuery)) {
                result[i] = caseInsensitive.get(lowerQuery);
                continue;
            }
            
            String vowelQuery = replaceVowels(lowerQuery);
            if (vowelError.containsKey(vowelQuery)) {
                result[i] = vowelError.get(vowelQuery);
                continue;
            }
            
            result[i] = "";
        }
        
        return result;
    }
    
    private String replaceVowels(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}