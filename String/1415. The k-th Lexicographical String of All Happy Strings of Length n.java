// QUESTION: 1415. The k-th Lexicographical String of All Happy Strings of Length n
// LINK: https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/?envType=daily-question&envId=2026-03-14

// SOLUTION: Combinatorics (Counting)

class Solution {
    public String getHappyString(int n, int k) {
        int total = 3 * (1 << (n - 1));

        if(k > total) return "";

        String result = "";
        k--;    // make k zero indexed

        int blockSize = 1 << ( n - 1);

        // First character
        result += (char) ('a' + k / blockSize);
        k %= blockSize;

        char prev = result.charAt(0);

        // Remaining charcters
        for(int i = 1; i < n; i++)
        {
            blockSize >>= 1;

            char[] options = new char[2];
            int idx = 0;

            for(char ch = 'a'; ch <= 'c'; ch++)
            {
                if(ch != prev)
                {
                    options[idx++] = ch;
                }
            }


            result += options[k / blockSize];
            prev = options[k / blockSize];
            k %= blockSize;
        }
        return result;
    }
}