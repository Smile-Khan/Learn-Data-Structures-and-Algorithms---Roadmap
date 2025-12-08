// QUESTION: 1925. Count Square Sum Triples
// LINK: https://leetcode.com/problems/count-square-sum-triples/description/?envType=daily-question&envId=2025-12-08

// SOLUTION: 

class Solution {
    public int countTriples(int n) {
        int count = 0;
        
        for (int a = 1; a <= n; a++) {
            int a2 = a * a;
            
            for (int b = a; b <= n; b++) {
                int sum = a2 + b * b;
                int c = (int) Math.sqrt(sum);
                
                // Early exit: if c exceeds n, break (since c increases with b)
                if (c > n) {
                    break;
                }
                
                // Check Pythagorean triple
                if (c * c == sum) {
                    count += (a == b) ? 1 : 2;
                }
            }
        }
        
        return count;
    }
}