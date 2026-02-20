// QUESTION: 761. Special Binary String
// LINK: https://leetcode.com/problems/special-binary-string/description/?envType=daily-question&envId=2026-02-20

// SOLUTION: 

class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() == 0) return "";
        
        List<String> specials = new ArrayList<>();
        int count = 0;
        int start = 0;
        
        // Find all top-level special substrings
        for (int i = 0; i < s.length(); i++) {
            count += (s.charAt(i) == '1') ? 1 : -1;
            
            if (count == 0) {
                // Found a special substring from start to i
                // Recursively process the inner part (excluding outer 1 and 0)
                String inner = s.substring(start + 1, i);
                specials.add("1" + makeLargestSpecial(inner) + "0");
                start = i + 1;
            }
        }
        
        // Sort in descending order (lexicographically largest first)
        Collections.sort(specials, Collections.reverseOrder());
        
        // Combine all special substrings
        StringBuilder result = new StringBuilder();
        for (String special : specials) {
            result.append(special);
        }
        
        return result.toString();
    }
}