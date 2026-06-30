// QUESTION: 1358. Number of Substrings Containing All Three Characters
// LINK: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/?envType=daily-question&envId=2026-06-30
// SOLUTION: SLIDING WINDOW + OTHER APPROACHES

class Solution {
    public int numberOfSubstrings(String s) {
        // i'll use hashMap for this
        // like i'll store "abc" and i'll check if 
        // hashmap.containsKey(s) count++;
        // HashMap<Integer,Character> map = new HashMap<>();
        // map.put("abc");

        // for(char ch : s.toCharArray())
        // {
        //     if(map.containsKey(ch))
        //     {
        //         count++;
        //     }
        // }
        // return count;
        // int count = 0;
        // for(int i = 0; i < s.length(); i++)
        // {
        //     for(int j = i; j >= 0; j--)
        //     {
        //         if(s.charAt(i) && s.charAt(j) == "abc")
        //         {
        //             count++;
        //         }
        //     }
        // }
        // return count;
        // Time & space: O(n)^2

        // expample: ababc
        // valid substring: ababc = 1, babc=2, abc=3, totaol = 3;

        // int left = 0;
        // int total = 0;

        // for(int right = 0; right <= s.length(); right++)
        // {
        //     if(s.charAt(right) && s.charAt(left) == 'abc')
        //     {
        //         total++;
        //         right++;
        //         left--;
        //     }
        // } 
        // return total;

        // int left = 0;
        // int[] count = new int[3];
        // int answer = 0;

        // for(int right = 0; right < s.charAt(i); right++)
        // {
        //     // how can i add character at right?
        //     // what will be the method?
        //     if(s.charAt(right) == 'abc')
        //     {
        //         count++;
        //     }
        //     while(right == abc)
        //     {
        //         left++;
        //         // what where is the use of count array?
        //         // i dont know this type of sliding window exist
        //     }
        // }

        // int left = 0;
        // int[] count = new int[3];
        // int answer = 0;

        // for(int right = 0; right < s.length(); right++)
        // {
        //     char ch = s.charAt(right);
        //     int index = ch - 'a';

        //     count[index]++;

        //     while(count[0] > 0 && count[1] > 0 && count[2] > 0)
        //     {
                

        //         char leftChar = s.charAt(left);
        //         int leftInd = leftChar - 'a';
        //         count[leftInd]--;
                
        //         left++;
        //     } 
        //     answer += left;
        // }
        // return answer;

        int[] lastPos = new int[3];
        Arrays.fill(lastPos, -1);
        int answer = 0;

        for(int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            lastPos[ch - 'a'] = i;

            // Find the minimum of the last positions
            int minPos = Math.min(lastPos[0], Math.min(lastPos[1], lastPos[2]));

            // If all three characters have been seen
            if(minPos != -1)
            {
                answer += minPos + 1;
            }
        }
        return answer;
    }
}