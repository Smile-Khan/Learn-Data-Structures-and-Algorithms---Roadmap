//QUESTION: Sum of subarray minimum
//LINK: https://www.geeksforgeeks.org/problems/sum-of-subarray-minimum/1
//
//
//SOLUTION: MONOTONIC STACK
//🧠 Time & Space Complexity
//Aspect	Value
//Time	O(n)
//Space	O(n)
//Stack	Used twice
//Final Result	Fits in 32-bit (as guaranteed)

class Solution {
    final int MOD = 1_000_000_007;
    public int sumSubMins(int[] arr) {
        // code here
        final int MOD = 1_000_000_007;

        int n = arr.length;

        int[] ple = new int[n];
        int[] nle = new int[n];

        Stack<Integer> stack = new Stack<>();

        // calculate ple
        for(int i = 0; i < n; i++)
        {
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i])
            {
                stack.pop();
            }

            ple[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // clear stack for reuse
        stack.clear();

        for(int i = n - 1; i >= 0; i--)
        {
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i])
            {
                stack.pop();
            }

            nle[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Comute total contribution
        long result = 0;
        for(int i = 0; i < n; i++)
        {
            long left = i - ple[i];
            long right = nle[i] - i;

            result = (result + arr[i] * left * right) % MOD;
        }
        return (int) result;
    }
}
