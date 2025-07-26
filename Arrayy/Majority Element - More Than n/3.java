QUESTION: Majority Element - More Than n/3
LINK: https://www.geeksforgeeks.org/problems/majority-vote/1

SOLUTION

class Solution {
    public ArrayList<Integer> findMajority(int[] arr) {
        // Code here
        ArrayList<Integer> result = new ArrayList<>();

        // Edge case
        if (arr == null || arr.length == 0) {
            return result;
        }

        int n = arr.length;

        // Step 1: Find candidates
        int candidate1 = Integer.MIN_VALUE, candidate2 = Integer.MIN_VALUE;
        int count1 = 0, count2 = 0;

        for (int num : arr) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Verify candidates
        count1 = 0;
        count2 = 0;
        for (int num : arr) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++; // avoid double count if both are same
        }

        if (count1 > n / 3) result.add(candidate1);
        if (count2 > n / 3) result.add(candidate2);

        Collections.sort(result); // to return in increasing order
        return result;
    }
}