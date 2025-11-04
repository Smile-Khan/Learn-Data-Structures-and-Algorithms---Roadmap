// QUESTION: 3318. Find X-Sum of All K-Long Subarrays I
// LINK: https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/description/?envType=daily-question&envId=2025-11-04

// SOLUTION: SLIDING WINDOW

class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        // Frequency map for current window
        int[] freq = new int[51];   // nums[i] <= 50

        // Initialize first window
        for(int i = 0; i < k; i++)
        {
            freq[nums[i]]++;
        }
        result[0] = calculateXSum(freq, x);

        // Slide the window
        for(int i = 1; i <= n - k; i++)
        {
            // Remove left element
            freq[nums[i - 1]]--;
            
            // Add right element
            freq[nums[i + k - 1]]++;

            result[i] = calculateXSum(freq, x);
        }
        return result;
    }

    private int calculateXSum(int[] freq, int x)
    {
        // Create list of entries (value, frequency)
        List<int[]> entries = new ArrayList<>();
        for(int i = 1; i <= 50; i++)
        {
            if(freq[i] > 0)
            {
                entries.add(new int[] {i, freq[i]});
            }
        }

        // Sort by frequency (descending), then by value (descending)
        Collections.sort(entries, (a, b) -> {
            if(a[1] != b[1])
            {
                return b[1] - a[1];  // Higher frequency first
            }
            return b[0] - a[0]; // Higher value first for same frequency
        });

        int sum = 0;
        int count = Math.min(x, entries.size());

        // Sum top x elements
        for(int i = 0; i < count; i++)
        {
            int[] entry = entries.get(i);
            sum += entry[0] * entry[1]; // Value * frequency
        }
        return sum;
    }
}