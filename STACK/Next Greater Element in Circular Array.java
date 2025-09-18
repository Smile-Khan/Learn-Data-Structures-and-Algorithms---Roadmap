// QUESTION: Next Greater Element in Circular Array
// LINK: https://www.geeksforgeeks.org/problems/next-greater-element/1

// SOLUTION: 

class Solution {
    public ArrayList<Integer> nextGreater(int[] arr) {
        // code here
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        ArrayList<Integer> finalResult = new ArrayList<>();
        
        
        for(int i = 2 * n - 1; i >= 0; i--)
        {
            // Keep popping while the stack has elements AND 
            // the value represented by the index at the top of the stack is
            // less than or equal to the current value.
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i % n])
            {
                stack.pop();
            }
            // Set the result. Only set it for the first pass (i < n)
            // we can actually set it on every pass, the second pass will just overwrite with the same values
            
            result[i%n] = stack.isEmpty() ? -1 : arr[stack.peek()];
            
            // Push the current index onto the stack
            stack.push(i%n);
        }
        ArrayList<Integer> finalList = new ArrayList<>();
        
        for(int val : result)
        {
            finalList.add(val);
        }
        return finalList;
    }
}