// QUESTION: 3551. Minimum Swaps to Sort by Digit Sum
// LINK: https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/description/?envType=daily-question&envId=2026-10-17

// SOLUTION: JAVA

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        int[][] arr = new int[n][3];

        for(int i = 0; i < n; i++)
        {
            arr[i][0] = digitSum(nums[i]);  // Digit Sum
            arr[i][1] = nums[i];    // original
            arr[i][2] = i;  // Original index
        }

        Arrays.sort(arr, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });


        // count the cycles
        boolean[] visited = new boolean[n];
        int cycles = 0;

        for(int i = 0; i < n; i++)
        {
            if(visited[i]) continue;

            // We found the start of a new cycle
            int j = i;
            while(!visited[j])
            {
                visited[j] = true;
                j = arr[j][2];
            }
            cycles++;
        }
        return n - cycles;
    }
    
    


    private int digitSum(int num)
    {
        int sum = 0;
        while(num > 0)
        {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}


// C++

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();

        // arr[i] = {digitSum, originalValue, originalIndex}
        vector<array<int, 3>> arr(n);

        for (int i = 0; i < n; i++) {
            arr[i][0] = digitSum(nums[i]);  // Digit Sum
            arr[i][1] = nums[i];            // original value
            arr[i][2] = i;                  // Original index
        }

        sort(arr.begin(), arr.end(), [](const array<int, 3>& a, const array<int, 3>& b) {
            if (a[0] != b[0]) return a[0] < b[0];
            return a[1] < b[1];
        });

        // Count the cycles
        vector<bool> visited(n, false);
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            // We found the start of a new cycle
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j][2];
            }
            cycles++;
        }

        return n - cycles;
    }

private:
    int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
};