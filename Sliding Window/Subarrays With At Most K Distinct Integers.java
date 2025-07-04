//QUESTION: Subarrays With At Most K Distinct Integers
//LINK : https://www.geeksforgeeks.org/problems/subarrays-with-at-most-k-distinct-integers/1


class Solution {
    public int countAtMostK(int arr[], int k) {
        // code here
        int n = arr.length;
        int left = 0, right = 0;
        int count = 0;

        Map<Integer, Integer> freq = new HashMap<>();

        for (right = 0; right < n; right++) {
            freq.put(arr[right], freq.getOrDefault(arr[right], 0) + 1);

            while (freq.size() > k) {
                freq.put(arr[left], freq.get(arr[left]) - 1);
                if (freq.get(arr[left]) == 0) {
                    freq.remove(arr[left]);
                }
                left++;
            }

            // For current right, all subarrays starting from left to right are valid
            count += right - left + 1;
        }

        return count;
    }
}
