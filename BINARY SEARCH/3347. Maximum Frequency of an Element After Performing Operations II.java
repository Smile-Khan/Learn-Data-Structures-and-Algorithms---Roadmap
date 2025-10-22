// QUESTION: 3347. Maximum Frequency of an Element After Performing Operations II
// LINK: https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii/editorial/?envType=daily-question&envId=2025-10-22

// SOLUTION: Sort + Enumerate + Binary Search

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);

        // Step 2: Generate our candidate target values (we'll call 'modes')
        // We also need the original counts of each number

        Map<Integer, Integer> numCount = new HashMap<>();
        Set<Integer> modes = new TreeSet<>(); // A TreeSet keeps candidates sorted and unique.

        // This helper method will contain the logic for generating candidates
        generateCandidates(nums, k, numCount, modes);

        // Step 3: Iterate through each candidate and find the best possible frequency
        int maxFreq = 0;

        // We already have the counts of existing numbers, so let's get the max
        // Frequency possible *without* any operations first.
        
        for(int count : numCount.values())
        {
            maxFreq = Math.max(maxFreq, count);
        }

        for(int mode : modes)
        {
            // This helper method will calculate the max frequency for a given target 'mode'.
            int currentFreq = calculateFrequencyForMode(mode, k, numOperations, nums, numCount);

            maxFreq = Math.max(maxFreq, currentFreq);
        }

        // Step 4: Return the best result we found;
        return maxFreq;
    }
    private void generateCandidates(int[] nums, int k, Map<Integer, Integer> numCount, Set<Integer> modes)
    {
        if(nums.length == 0)
        {
            return;
        }

        // this lambda will add a value and its k-neighbors to our candidate set.
        Consumer<Integer> addModeCandidates = value -> {
            modes.add(value); // The number itslef is a candidate.
            // A number 'k' smaller is also a candidate.

            if(value - k >= nums[0])
            {
                // Small optimization : check if it's in the array's range
                modes.add(value - k);
            }

            // A number 'k' larger is also a candidate.
            if(value + k <= nums[nums.length - 1])
            {
                modes.add(value + k);
            }
        };
        // this loop populates the frequeny map (numCount) and calls the lambda 
        // for each unique number to generate mode candidates
        int lastNumIndex = 0;
        for(int i =0; i < nums.length; i++)
        {
            if(nums[i] != nums[lastNumIndex])
            {
                // We've found the end of a group of identical numbers.
                int groupNum = nums[lastNumIndex];
                int groupCount = i - lastNumIndex;

                numCount.put(groupNum, groupCount);
                addModeCandidates.accept(groupNum);

                lastNumIndex = i;
            }
        }
        // Don't forget the very last group of numbers in the array!
    int lastGroupNum = nums[lastNumIndex];
    int lastGroupCount = nums.length - lastNumIndex;
    numCount.put(lastGroupNum, lastGroupCount);
    addModeCandidates.accept(lastGroupNum);
    }
    private int leftBound(int[] nums, int value)
    {
        int left = 0;
        int right = nums.length - 1;

        while(left < right)
        {
            int mid = (left + right) / 2;

            if(nums[mid] < value)
            {
                left = mid + 1;
            }
            else 
            {
                right = mid;
            }
        }
        return left;
    }

    // Finds the index of the last element <= value
    private int rightBound(int[] nums, int value)
    {
        int left = 0;
        int right = nums.length - 1;

        while(left < right)
        {
            int mid = (left + right + 1) / 2; // Biased mid to prevent infinite looop
            if(nums[mid] > value)
            {
                right = mid - 1;
            }
            else 
            {
                left = mid;
            }
        }
        return left;
    }

    // Now we can write the main calculation method
private int calculateFrequencyForMode(int mode, int k, int numOperations, int[] nums, Map<Integer, Integer> numCount) {
    // 1. Find the count of ELIGIBLE numbers using binary search.
    int l_idx = leftBound(nums, mode - k);
    int r_idx = rightBound(nums, mode + k);
    
    // This check is important: if the range is invalid (e.g., nums[l_idx] > mode+k),
    // the count is 0.
    if (nums[l_idx] > mode + k || nums[r_idx] < mode - k) {
        return 0;
    }
    int eligibleCount = r_idx - l_idx + 1;

    // 2. Find the count of numbers we can AFFORD.
    int existingCount = numCount.getOrDefault(mode, 0);
    int affordableCount = existingCount + numOperations;

    // 3. The result is the smaller of the two. We can't make more than
    // are eligible, and we can't make more than we can afford.
    return Math.min(eligibleCount, affordableCount);
}
}