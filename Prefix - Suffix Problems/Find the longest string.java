// PROBLEM: Find the longest string
// LINK: https://www.geeksforgeeks.org/problems/find-the-longest-string--170645/1


//SOLUTION: JAVA
//Time Complexity
//O(n log n): Due to sorting (Arrays.sort(words)).
//
//O(n * L): For prefix checks (where L is the max word length).

class Solution {
    public String longestString(String[] words) {
        // code here
        //int n = words.length();
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        set.add("");
        String result = "";

        for (String word : words) {
            String prefix = word.substring(0, word.length() - 1);
            if (set.contains(prefix)) {
                set.add(word);
                if (word.length() > result.length() ||
                        (word.length() == result.length() && word.compareTo(result) < 0)) {
                    result = word;
                }
            }
        }
        return result;
    }
}
