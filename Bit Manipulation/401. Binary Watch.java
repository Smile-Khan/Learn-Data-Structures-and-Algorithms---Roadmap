// QUESTION: 401. Binary Watch
// LINK: https://leetcode.com/problems/binary-watch/description/?envType=daily-question&envId=2026-02-17

// SOLUTION: Enumerating Hours and Minutes

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    result.add(String.format("%d:%02d", h, m));
                }
            }
        }
        
        return result;
    }
}