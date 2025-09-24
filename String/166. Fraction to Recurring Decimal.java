// QUESTION: 166. Fraction to Recurring Decimal
// LINK: https://leetcode.com/problems/fraction-to-recurring-decimal/description/?envType=daily-question&envId=2025-09-24

// SOLUTION: 

import java.util.HashMap;

class Solution {
    public String fractionToDecimal(int numerator, int denominator)
    {
        // Edge case
        if(numerator == 0)
        {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        if((numerator > 0) ^ (denominator > 0))
        {
            result.append("-");
        }

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        result.append(num / den);

        long rem = num % den;

        if(rem == 0)
        {
            return result.toString();
        }
        else 
        {
            result.append(".");
        }

        HashMap<Long, Integer> map = new HashMap<>();

        while(rem != 0)
        {
            map.put(rem, result.length());

            rem *= 10;
            result.append(rem/den);
            rem %= den;

            if(map.containsKey(rem))
            {
                int startIndex = map.get(rem);
                result.insert(startIndex, "(");
                result.append(")");
                break;
            }
        }
        return result.toString();
    }
}