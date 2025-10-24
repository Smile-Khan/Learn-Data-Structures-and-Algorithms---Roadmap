// QUESTION: Split Array Subsequences
// LINK: https://www.geeksforgeeks.org/problems/split-array-subsequences/1

// SOLUTION: GREEDY APPROACH

class Solution {

    public boolean isPossible(int[] arr, int k) {
        // Code here
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> tailsMap = new HashMap<>();
        
        for(int num : arr)
        {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        for(int x : arr)
        {
            // If we have no more of this number left, skip
            if(freqMap.get(x) == 0)
            {
                continue;
            }
            
            // Greedy choice 1: Append to an existing sequence if possible
            if(tailsMap.getOrDefault(x, 0) > 0)
            {
                // We are using one 'x', so decrement its frequency.
                freqMap.put(x, freqMap.get(x) - 1);
                
                // A sequence waiting for 'x' is now statisfied.
                tailsMap.put(x, tailsMap.get(x) - 1);
                
                // That sequence is now waiting for 'x + 1'
                tailsMap.put(x + 1, tailsMap.getOrDefault(x + 1, 0) + 1);
            }
            // Greedy Choice 2: Otherwise, try to start a new sequence.
            else 
            {
                // Check AND decrement in the same loop for efficiency.
                for(int i = 0; i < k; i++)
                {
                    int needed = x + i;
                    
                    // Check if we have the number we need for the new sequence
                    if(freqMap.getOrDefault(needed, 0) <= 0)
                    {
                        return false; // Impossible to form a new sequence.
                    }
                    
                    // If we have it, use it by decrementing its frequency.
                    freqMap.put(needed, freqMap.get(needed) - 1);
                }
                // The new sequence we just formed is now waiting for 'x + k'.
                tailsMap.put(x + k, tailsMap.getOrDefault(x + k, 0) + 1);
            }
        }
        // If we suffessfully process every number, it's possible
        return true;
    }
}