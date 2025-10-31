// QUESTION: 3289. The Two Sneaky Numbers of Digitville
// LINK: https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/description/?envType=daily-question&envId=2025-10-31


// SOLUTION: HASHSET

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        int[] result = new int[2];
        int index = 0;

        for(int num : nums)
        {
            if(seen.contains(num))
            {
                // duplicate found
                result[index++] = num;
            }
            else 
            {
                // First time seeing 
                seen.add(num);
            }
        }
        return result;
    }
}

// SOLUTION: HashMap

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        // Count frequencies
        for(int num : nums)
        {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Find numbers with frequency 2
        int[] result = new int[2];
        int index = 0;

        for(int key : freq.keySet())
        {
            if(freq.get(key) == 2)
            {
                result[index++] = key;
            }
        }
        return result;
    }
}


// SOLUTION: IN PLACE MODIFICATION

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        

        Arrays.sort(nums);
        int[] result = new int[2];
        int index = 0;
        
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) {
                result[index++] = nums[i];
                if(index == 2) break; // Found both
            }
        }
        
        return result;
    }
}

// SOLUTION: BOOLEAN ARRAY

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
      boolean[] seen = new boolean[nums.length];
      int[] result = new int[2];
      int index = 0;

      for(int num : nums)
      {
        if(seen[num])
        {
            result[index++] = num;
        }
        else 
        {
            seen[num] = true;
        }
      }
      return result;
    }
}