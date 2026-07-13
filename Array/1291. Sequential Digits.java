// 1291. Sequential Digits
// LINK: https://leetcode.com/problems/sequential-digits/description/?envType=daily-question&envId=2026-07-13

// SOLUTION: 
import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        for (int length = 1; length <= 9; length++) {
            for (int start = 0; start + length <= 9; start++) {
                String sub = digits.substring(start, start + length);
                int num = Integer.parseInt(sub);
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        Collections.sort(result);
        return result;
    }
}