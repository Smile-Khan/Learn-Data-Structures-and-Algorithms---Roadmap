// QUESTION: 2197. Replace Non-Coprime Numbers in Array
// LINK : https://leetcode.com/problems/replace-non-coprime-numbers-in-array/description/?envType=daily-question&envId=2025-09-16

// SOLUTION: 

class Solution {
    public List<Integer> replaceNewCoprimes(int[] nums)
    {
        List<Long> result = new ArrayList<>();

        for(int num : nums)
        {
            long currentNum = num;

            while(!result.isEmpty() && gcd(result.get(result.size() - 1), currentNum) > 1)
            {
                long lastElemnt = result.remove(result.size() - 1);

                currentNum = lcm(lastElemnt, currentNum);
            }

            result.add(currentNum);
        }

        // Convert result back to int 
        List<Integer> finalResult = new ArrayList<>();
        
        for(long val : result)
        {
            finalResult.add((int) val);
        }
        return finalResult;
    }

    private long gcd(long a, long b)
    {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b)
    {
        if(a == 0 || b == 0 ) return 0;

        // Use long to prevent overFlow during multiplication
        rerturn (a * b) / gcd(a, b);
    }
}