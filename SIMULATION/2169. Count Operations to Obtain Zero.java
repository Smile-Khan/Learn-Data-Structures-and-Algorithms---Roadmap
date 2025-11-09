// QUESTION: 2169. Count Operations to Obtain Zero
// LINK: https://leetcode.com/problems/count-operations-to-obtain-zero/description/?envType=daily-question&envId=2025-11-09

// SOLUTION: Approach 1: Simulation (Direct Implementation)

class Solution {
    public int countOperations(int num1, int num2) {
        int operations = 0;
        
        while (num1 > 0 && num2 > 0) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
            operations++;
        }
        
        return operations;
    }
}


// Approach 2: RECURSIVE Approach
class Solution {
    public int countOperations(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }
        
        if (num1 >= num2) {
            return 1 + countOperations(num1 - num2, num2);
        } else {
            return 1 + countOperations(num1, num2 - num1);
        }
    }
}

// Approach 3: Optimized with Division (Mathematical)

class Solution {
    public int countOperations(int num1, int num2) {
        int operations = 0;
        
        while (num1 > 0 && num2 > 0) {
            if (num1 >= num2) {
                operations += num1 / num2;
                num1 %= num2;
            } else {
                operations += num2 / num1;
                num2 %= num1;
            }
        }
        
        return operations;
    }
}

// Approach 4: Most Efficient - Euclidean Algorithm Style

class Solution {
    public int countOperations(int num1, int num2) {
        int count = 0;
        
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) {
                count += num1 / num2;
                num1 %= num2;
            } else {
                count += num2 / num1;
                num2 %= num1;
            }
        }
        
        return count;
    }
}


// Approach 5: Using Math.min and Math.max

class Solution {
    public int countOperations(int num1, int num2) {
        int operations = 0;
        
        while (num1 > 0 && num2 > 0) {
            int minVal = Math.min(num1, num2);
            int maxVal = Math.max(num1, num2);
            
            operations += maxVal / minVal;
            
            if (num1 >= num2) {
                num1 %= num2;
            } else {
                num2 %= num1;
            }
        }
        
        return operations;
    }
}


// Approach 6: Clean While Loop

class Solution {
    public int countOperations(int num1, int num2) {
        int steps = 0;
        
        while (num1 != 0 && num2 != 0) {
            if (num1 > num2) {
                num1 = num1 - num2;
            } else {
                num2 = num2 - num1;
            }
            steps++;
        }
        
        return steps;
    }
}