// QUESTION: Interleave the First Half of the Queue with Second Half
// LINK: https://www.geeksforgeeks.org/problems/interleave-the-first-half-of-the-queue-with-second-half/1

// SOLUTION: STACK + QUEUE 

class Solution {
    public void rearrangeQueue(Queue<Integer> q) {
        // code here
        
        int n = q.size();
        int half = n / 2;
        
        // Step 1: Push first half to stack (reverse Order)
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < half; i++)
        {
            stack.push(q.poll());
        }
        
        // Step 2: Push stack elements back to queue
        // Now first half is at end of queue (but reversed)
        while(!stack.isEmpty())
        {
            q.add(stack.pop());
        }
        
        // STep 3: Move first half to end again
        // This puts first half in correct order at end
        for(int i = 0; i < half; i++)
        {
            q.add(q.poll());
        }
        
        // Step 4: Push first half to stack again
        for(int i = 0; i < half; i++)
        {
            stack.push(q.poll());
        }
        
        // Step 5: Interleave elements from stack and queue
        while(!stack.isEmpty())
        {
            q.add(stack.pop());
            q.add(q.poll());
        }
    }
}