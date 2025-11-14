// QUESTION: 2536. Increment Submatrices by One
// LINK: https://leetcode.com/problems/increment-submatrices-by-one/description/?envType=daily-question&envId=2025-11-14

// SOLUTION: BRUTE FORCE (NAIVE)

class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] matrix = new int[n][n];

        for(int[] query : queries)
        {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            for(int i = r1; i <= r2; i++)
            {
                for(int j = c1; j <= c2; j++)
                {
                    matrix[i][j] += 1;
                }
            }
        }
        return matrix;
    }
}


// APPROACH 2: USING ROW-WISE PREFIX SUM

class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] matrix = new int[n][n];

        for(int[] query : queries)
        {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            for(int i = r1; i <= r2; i++)
            {
                matrix[i][c1] += 1;
                if(c2 + 1 < n)
                {
                    matrix[i][c2 + 1] -= 1;
                }
            }
        }

        // Compute row-wise prefix sum
        for(int i = 0; i < n; i++)
        {
            for(int j = 1; j < n; j++)
            {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        return matrix;
    }
}

// APPROACH 3: USING 2D DIFFERENCE ARRAY

class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n + 1][n + 1];

        for(int[] query : queries)
        {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            diff[r1][c1] += 1;
            if(c2 + 1 < n)
            {
                diff[r1][c2 + 1] -= 1;
            }
            if(r2 + 1 < n)
            {
                diff[r2 + 1][c1] -= 1;
            }
            if(r2 + 1 < n && c2 + 1 < n)
            {
                diff[r2 + 1][c2 + 1] += 1;
            }
        }

        // Compute prefix sums row-wise
        for(int i = 0; i < n; i++)
        {
            for(int j = 1; j < n; j++)
            {
                diff[i][j] += diff[i][j - 1];
            }
        }

        // Compute prefix sums column-wise
        for(int j = 0; j < n; j++)
        {
            for(int i = 1; i < n; i++)
            {
                diff[i][j] += diff[i - 1][j];
            }
        }

        // Prepare the final matrix
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                result[i][j] = diff[i][j];
            }
        }

        return result;
    }
}

// APPROACH 4: OPTIMIZED 2D PREFIX SUM
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] matrix = new int[n + 1][n + 1];

        for(int[] query : queries)
        {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            matrix[r1][c1] += 1;
            if(c2 + 1 < n)
            {
                matrix[r1][c2 + 1] -= 1;
            }
            if(r2 + 1 < n)
            {
                matrix[r2 + 1][c1] -= 1;
            }
            if(r2 + 1 < n && c2 + 1 < n)
            {
                matrix[r2 + 1][c2 + 1] += 1;
            }
        }

        // Compute prefix sums row-wise
        for(int i = 0; i < n; i++)
        {
            for(int j = 1; j < n; j++)
            {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        // Compute prefix sums column-wise
        for(int j = 0; j < n; j++)
        {
            for(int i = 1; i < n; i++)
            {
                matrix[i][j] += matrix[i - 1][j];
            }
        }

        // Prepare the final matrix
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                result[i][j] = matrix[i][j];
            }
        }

        return result;
    }
}

// APPROEACH 5: IN-PLACE MODIFICATION
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] matrix = new int[n][n];

        for(int[] query : queries)
        {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            matrix[r1][c1] += 1;
            if(c2 + 1 < n)
            {
                matrix[r1][c2 + 1] -= 1;
            }
            if(r2 + 1 < n)
            {
                matrix[r2 + 1][c1] -= 1;
            }
            if(r2 + 1 < n && c2 + 1 < n)
            {
                matrix[r2 + 1][c2 + 1] += 1;
            }
        }

        // Compute prefix sums row-wise
        for(int i = 0; i < n; i++)
        {
            for(int j = 1; j < n; j++)
            {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        // Compute prefix sums column-wise
        for(int j = 0; j < n; j++)
        {
            for(int i = 1; i < n; i++)
            {
                matrix[i][j] += matrix[i - 1][j];
            }
        }

        return matrix;
    }
}