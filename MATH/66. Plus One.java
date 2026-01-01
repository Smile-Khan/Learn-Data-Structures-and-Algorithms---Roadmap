// QUESTION: 66. Plus One
// LINK: https://leetcode.com/problems/plus-one/description/?envType=daily-question&envId=2026-01-01

// SOLUTION: OPTIMIZED SIMULATION
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Iterate from the rightMost digit (least significant)
        for(int i = n - 1; i >= 0; i--)
        {
            // Add 1 to the current digit
            digits[i]++;

            // Check if there's a carry
            if(digits[i] < 10)
            {
                // No carry, the addition is complete. Return the modified array
                return digits;
            }
            else 
            {
                // There's a carry. Set this digit to 0 and continue the loop
                // To handle the carry for the next digit to the left.
                digits[i] = 0;
            }
        }

        // If the loop finished, it means there was a carry out of the leftMost digit
        // (e.g: the input was [9, 9, 9, 9]).
        // We need to create a new array with extra digit at the beginning.
        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;   // The leading 1 from the carry
        // The rest of the newDigits array is already filled with zeros by default.
        // If the original was [9], n = 1. New array size is 2.
        // newDigits[0] = 1. newDigits[1] = 0 (from the carry). Result is [1, 0]. Correct

        return newDigits;
    }
}

// BRUTE FORCE
class Solution {
    public int[] plusOne(int[] digits) {
        /*
        BRUTE FORCE APPROACH:
        CONVERt array to actual number, add one, then convert back to array.

        WHY IT'S BRUTE FORCE:
        -Converts array to number (potentially huge, can cause overflow)
        -Not efficient for large arrays (up to 100 digits = 10^100 number)
        -Extra conversions add unnecessary complexity

        TIME COMPLEXITY: O(N) FOR CONVERSIONS
        SPACE COMPLEXITY: O(N) for string operations

        PROBLEM: Integer overlfow for large numbers (> 2^31-1)
        */

        // Step 1: Convert array to sting representation
        StringBuilder numStr = new StringBuilder();
        for(int digit : digits)
        {
            numStr.append(digit);
        }

        // Step 2: Convert string to BigInteger to handle large numbers
        java.math.BigInteger bigNum = new java.math.BigInteger(numStr.toString());

        // Step 3: Add one
        bigNum = bigNum.add(java.math.BigInteger.ONE);

        // Step 4: Convert back to string
        String resultStr = bigNum.toString();

        // Step 5: Convert string to array
        int[] result = new int[resultStr.length()];
        for(int i = 0; i < resultStr.length(); i++)
        {
            result[i] = resultStr.charAt(i) - '0';
        }
        return result;
    }
}


// APPROACH:
class Solution {
    public int[] plusOne(int[] digits) {
        /*
        BRUTE FORCE WITH STRING MANIPULATION:
        Simulate addition by treating digits as characters.

        STEPS:
        1. Convert array to string
        2. Add one manually from right to left (like grade school addition)
        3. Convert result back to array

        TIME COMPLEXITY: O(n)
        SPACE COMPLEXITY: O(n) for string operations
        BETTER THAN APPROACH 1: no BigInteger overhead
        */

        // Convert array to string for easier manipulation
        StringBuilder sb = new StringBuilder();
        for(int digit : digits)
        {
            sb.append(digit);
        }

        String numStr = sb.toString();

        // Manual addition from right to left
        char[] chars = numStr.toCharArray();
        int carry = 1;  // We're adding 1

        for(int i = chars.length - 1; i >= 0; i--)
        {
            int digit = (chars[i] - '0') + carry;

            if(digit == 10)
            {
                chars[i] = '0';
                carry = 1;
            }
            else 
            {
                chars[i] = (char)(digit + '0');
                carry = 0;
                break;  // No more carry propagation needed
            }
        }
        // Handle case where carry propagates through all digits
        if(carry == 1)
        {
            // All digits were 9, need new array starting with 1
            int[] result = new int[chars.length + 1];
            result[0] = 1;
            return result;
        }

        // Convert char array back to int array
        int[] result = new int[chars.length];

        for(int i = 0; i < chars.length; i++)
        {
            result[i] = chars[i] - '0';
        }

        return result;
    }
}
 // APPROACH 3:

 class Solution {
    public int[] plusOne(int[] digits) {
        /*
        OPTIMIZED APPROACH:
        Work directly with the array without any conversions.

        KEY INSIGHT:
        - Most cases: just increment the last digit
        - Only need to worry about carry when digit is 9
        - Only need new array when ALL digits are 9


        TIME COMPLEXITY: O(n) in worst case, O(1) in best case
        SPACE COMPLEXITY: O(1) for most cases, O(n + 1) when all digits are 9
        */

        int n = digits.length;

        // Traverse from last digit to first (right to left)
        for(int i = n - 1; i >= 0; i--)
        {
            // If digit is less than 9, simply increment and return
            if(digits[i] < 9)
            {
                digits[i]++;    // Increment the digit
                return digits;  // We're done - return immediately
            }

            // Digit is 9, so it becomes 0 and we carry 1
            digits[i] = 0;  // 9 + 1 = 10, so digit becomes 0
        }

        /* 
        If we reach here, ALL digits were 9 (e.g: 999 -> 1000)
        We need to create a new array with size n + 1
        First digit will be 1, all others automatically 0
        */

        int[] result = new int[n + 1];
        result[0] = 1;  // Most significant digit is 1

        return result;
    }
} 


// APPROACH 4: 

class Solution {
    public int[] plusOne(int[] digits) {
        /*
        ALTERNATIVE OPTIMIZED APPROACH:

        SAME logic as Approach 3, but using while loop for clarity

        ADVANTAGES:
        -Clearer flow for some readers
        -Explicitly shows carry propagation

        DISADVANTAGES:
        -Slightly more code
        -Same time/space complexity
        */

        int carry = 1;  // We're adding 1, so initially carry is 1
        int index = digits.length - 1;  // Start from the last digit

        // Propagate carry from right to left
        while(index >= 0 && carry > 0)
        {
            int sum = digits[index] + carry;
            digits[index] = sum % 10;   // Keep only last digit
            carry = sum / 10;
            index--;
        }

        // If carry still exists after processing all digits
        if(carry > 0)
        {
            // Create new array starting with 1
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            // Note: Java initializes all other elements to 0
            return result;
        }

        return digits;
    }
}

// APPROACH 5:

class Solution {
    public int[] plusOne(int[] digits) {
        /*
        Use recursion to handle carry propagation

        HOW IT WORKS:
        -Recursively process digits from right to left
        - Base case: empty array (all digits were 9)
        - Recursive case: increment current digit or propagate carry

        ADVANTAGES:
        - Elegant recursive thinking
        - Shows understanding of recursion

        DISADVANTAGES:
        - Risk of stack overflow for large arrays (100 digits is okay)
        - Overkill for this problem
        */

        // Start recursion from the last digit
        return plusOneHelper(digits, digits.length - 1);
    }

    private int[] plusOneHelper(int[] digits, int index)
{
    // Base case 1: if index is -1, all digits were 9
    if(index < 0)
    {
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    // Base case 2: if digit is less than 9, increment and return
    if(digits[index] < 9)
    {
        digits[index]++;
        return digits;
    }

    // Recursive case: digit is 9, set to 0 and propagate carry left
    digits[index] = 0;
    return plusOneHelper(digits, index - 1);
}
}


// APPROACH 6: LINKEDLIST

class Solution {
    public int[] plusOne(int[] digits) {
        /*
        OVER-ENGINEERING APPROACH:
        Use linkedList to easily add elements at the beginning.

        WHY IT'S OVER-ENGINEERING
        - LinkedList has overhead
        - Unecessary for this problem
        - Shows knowledge of data structures but poor judgment

        USEFUL FOR LEARNING
        Demonstrates when NOT to use certain data structures
        */

        LinkedList<Integer> list = new LinkedList<>();
        int carry = 1;

        // Process digits from right to left
        for(int i = digits.length - 1; i >= 0; i--)
        {
            int sum = digits[i] + carry;
            list.addFirst(sum % 10);    // Add to front of list
            carry = sum / 10;
        }

        // If carry remains, add it as new most significant digit
        if(carry > 0)
        {
            list.addFirst(carry);
        }

        // Convert LinkedList to array
        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            result[i] = list.get(i);
        }
        return result;
    }
}