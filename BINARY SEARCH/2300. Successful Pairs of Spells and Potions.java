// QUESTION: 2300. Successful Pairs of Spells and Potions
// LINK: https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/?envType=daily-question&envId=2025-10-08

// SOLUTION: BINARY SEARCH 

import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        // Step 1: Sort the potions array. This is the key.
        Arrays.sort(potions);
        
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n]; // Declare the result array

        // Step 2: Iterate through each spell
        for (int i = 0; i < spells.length; i++) {
            // Calculate the minimum potion strength needed for this spell
            // Using long for the spell value to prevent overflow in the calculation
            long minPotionStrength = (success + spells[i] - 1) / spells[i];

            // Step 3: Binary search for the first valid potion
            int low = 0;
            int high = m - 1;
            int firstValidIndex = m; // Assume none are found initially

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (potions[mid] >= minPotionStrength) {
                    // This potion is strong enough. It's a candidate for our answer.
                    // Let's see if there's an even better one to the left.
                    firstValidIndex = mid;
                    high = mid - 1;
                } else {
                    // This potion is too weak. We need to look at stronger potions
                    // in the right half of the search space.
                    low = mid + 1;
                }
            }
            
            // The number of successful potions is the total count minus the index
            // of the first successful one.
            result[i] = m - firstValidIndex;
        }
        
        return result;
    }
}