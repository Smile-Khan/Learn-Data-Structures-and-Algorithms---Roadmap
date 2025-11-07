// QUESTION: Weighted Job Scheduling
// https://www.geeksforgeeks.org/problems/weighted-job-scheduling/1

// SOLUTION: DYNAMIC PROGRAMMING

class Solution {
     class Job {
        int start, end, profit;
        
        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public int maxProfit(int[][] jobsArr) {
        int n = jobsArr.length;
        Job[] jobs = new Job[n];

        // --- THIS IS THE KEY ADAPTATION ---
        // Step 1: Convert the input int[][] into a more readable Job[] array.
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(jobsArr[i][0], jobsArr[i][1], jobsArr[i][2]);
        }
        
        // --- FROM THIS POINT ON, THE LOGIC IS IDENTICAL ---

        // Step 2: Sort the jobs based on their end time.
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.end));
        
        // Step 3: Initialize the DP array. dp[i] will store the max profit using jobs 0..i.
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;
        
        // Step 4: Fill the DP array using our recurrence relation.
        for (int i = 1; i < n; i++) {
            // Option 1: Profit if we INCLUDE the current job.
            int inclProf = jobs[i].profit;
            
            // Find the latest job that doesn't overlap with the current job.
            int lastCompatibleJobIndex = -1;
            int low = 0, high = i - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (jobs[mid].end <= jobs[i].start) {
                    lastCompatibleJobIndex = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            
            if (lastCompatibleJobIndex != -1) {
                inclProf += dp[lastCompatibleJobIndex];
            }
            
            // Option 2: Profit if we EXCLUDE the current job.
            // This is simply the max profit we could make up to the previous job.
            int exclProf = dp[i - 1];
            
            // Store the best of the two options.
            dp[i] = Math.max(inclProf, exclProf);
        }
        
        // The final answer is the maximum profit we can get by considering all jobs.
        return dp[n - 1];
    }
}
