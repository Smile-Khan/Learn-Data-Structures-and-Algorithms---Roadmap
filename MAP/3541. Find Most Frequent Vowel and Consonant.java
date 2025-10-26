QUESTION: 3541. Find Most Frequent Vowel and Consonant
LINK: https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/description/?envType=daily-question&envId=2025-09-13


SOLUTION: 

class Solution {
    public int maxFreqSum(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        
        for(char ch : s.toCharArray())
        {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }

        List<Integer> vowelFreq = new ArrayList<>();
        List<Integer> consonantFreq = new ArrayList<>();

        for(char ch : freqMap.keySet())
        {
            if(isVowel(ch))
            {
                vowelFreq.add(freqMap.get(ch));
            }
            else 
            {
                consonantFreq.add(freqMap.get(ch));
            }
        }

        int maxVowel = vowelFreq.isEmpty() ? 0 : Collections.max(vowelFreq);
        int maxConsonant = consonantFreq.isEmpty() ? 0 : Collections.max(consonantFreq);

        return maxVowel + maxConsonant;
    }
    private boolean isVowel(char ch)
    {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}