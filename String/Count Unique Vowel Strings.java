QUESTION:- Count Unique Vowel Strings

QUESTION LINK:- https://www.geeksforgeeks.org/problems/count-unique-vowel-strings/1

SOLUTION:

class Solution {
    public int vowelCount(String s) {
        // code here
        Map<Character, Integer> freq = new HashMap<>();
        Set<Character> vowelsI = new HashSet<>();
        String vowels = "aeiou";

        // Count freq of each vowel in the string
        for(char ch : s.toCharArray())
        {
            if(vowels.indexOf(ch) != -1)
            {
                freq.put(ch, freq.getOrDefault(ch, 0)+1);
                vowelsI.add(ch);
            }
        }

        // if there are no vowels return 0
        if(vowelsI.isEmpty())
        {
            return 0;
        }

        // Count of distinct vowels
        int distinctVowelCount = vowelsI.size();

        // Number of ways to choose 1 occurrence per vowel
        long waysToChoose = 1;

        for(char vowel : vowelsI)
        {
            waysToChoose *= freq.get(vowel);
        }

        // Number of permutation of selected vowels
        long permutations = factorial(distinctVowelCount);

        return (int)(waysToChoose * permutations);
    }

    private long factorial(int n)
    {
        long fact = 1;
        for(int i = 2; i <= n; i++)
        {
            fact *= i;
        }
        return fact;
    }
}