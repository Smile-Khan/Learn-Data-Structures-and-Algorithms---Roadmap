// QUESTION: 67. Add Binary
// LINK: https://leetcode.com/problems/add-binary/description/?envType=daily-question&envId=2026-02-15

// SOLUTION: TWO POINTER

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;

        int carry = 0;

        while(i >= 0 || j >= 0 || carry > 0)
        {
            int sum = carry;

            if(i >= 0)
            {
                sum += a.charAt(i) - '0';
                i--;
            }

            if(j >= 0)
            {
                sum += b.charAt(j) - '0';
                j--;
            }
            result.append(sum % 2);
            carry = sum / 2;
        }
        return result.reverse().toString();
    }
}

// APPROACH: STACK
class Solution {
    public String addBinary(String a, String b) {
        Stack<Integer> stack = new Stack<>();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while(i >= 0 || j >= 0 || carry > 0)
        {
            int sum = carry;
            if(i >= 0) sum += a.charAt(i--) - '0';
            if(j >= 0) sum += b.charAt(j--) - '0';

            stack.push(sum % 2);
            carry = sum / 2;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
        {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}