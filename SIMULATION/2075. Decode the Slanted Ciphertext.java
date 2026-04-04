// QUESTION: 2075. Decode the Slanted Ciphertext
// LINK: https://leetcode.com/problems/decode-the-slanted-ciphertext/description/?envType=daily-question&envId=2026-04-04

// SOLUTION: SIMULATION BRUTE FORCE

class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int l = encodedText.length();

        if(l == 0 || rows == 1) return encodedText;

        // Calculate number of columns
        int cols = l / rows;

        // Build the matrix
        char[][] matrix = new char[rows][cols];
        int index = 0;
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                matrix[i][j] = encodedText.charAt(index++);
            }
        }

        // Traverse anti-diagonally
        StringBuilder result = new StringBuilder();

        // Start from each column of the first row
        for(int startCol = 0; startCol < cols; startCol++)
        {
            int i = 0;
            int j = startCol;
            while(i < rows && j < cols)
            {
                result.append(matrix[i][j]);
                i++;
                j++;
            }
        }

        // Remove trailing spaces
        String original = result.toString();

        int lastNonSpace = original.length() - 1;
        while(lastNonSpace >= 0 && original.charAt(lastNonSpace) == ' ')
        {
            lastNonSpace--;
        }
        return original.substring(0, lastNonSpace + 1);
    }
}

// APPROACH 2: WITHOUT EXTRA SPACE

class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int len = encodedText.length();

        if(len == 0 || rows == 1) return encodedText;

        int cols = len / rows;
        StringBuilder result = new StringBuilder();

        // Direct anti-diagonal traversal without matrix

        for(int startCol = 0; startCol < cols; startCol++)
        {
            for(int i = 0, j = startCol; i < rows && j < cols; i++, j++)
            {
                int index = i * cols + j;
                result.append(encodedText.charAt(index));
            }
        }

        // Remove trailing spaces
        String original = result.toString();
        int lastNonSpace = original.length() - 1;
        while(lastNonSpace >= 0 && original.charAt(lastNonSpace) == ' ')
        {
            lastNonSpace--;
        }

        return original.substring(0, lastNonSpace + 1);
    }
}