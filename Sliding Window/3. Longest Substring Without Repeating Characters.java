// QUESTION:- 3. Longest Substring Without Repeating Characters
// LINK:- https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

//SOLUTION CODE :- Using Set
//        TIME:-  0(N)
//        SPACE:- O(1)

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxLength = 0;

        while(right < s.length())
        {
            char ch = s.charAt(right);
            if(!set.contains(ch))
            {
                set.add(ch);
                maxLength = Math.max(maxLength, right - left+1);
                right++;
            }
            else
            {
                set.remove(s.charAt(left));
                left++;

            }
        }
        return maxLength;
    }
}

//USING HASHMAP
//        TIME: O(N)
//        SPACE: O(N)

public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Map<Character, Integer> map = new HashMap<>();
    int left = 0, maxLength = 0;

    for (int right = 0; right < n; right++) {
        char ch = s.charAt(right);
        if (map.containsKey(ch) && map.get(ch) >= left) {
            left = map.get(ch) + 1;
        }
        map.put(ch, right);
        maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
}

//Sliding Window with ASCII Array
//
//TIME:  O(N);
//SPACE: O(1)

public int lengthOfLongestSubstring(String s) {
    int[] index = new int[128]; // ASCII size
    int maxLength = 0;
    int left = 0;

    for (int right = 0; right < s.length(); right++) {
        char ch = s.charAt(right);
        left = Math.max(index[ch], left);  // update left to skip duplicates
        maxLength = Math.max(maxLength, right - left + 1);
        index[ch] = right + 1; // store next position after current character
    }

    return maxLength;
}
