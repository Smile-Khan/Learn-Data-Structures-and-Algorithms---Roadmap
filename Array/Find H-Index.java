// QUESTION: Find H-Index
// LINK: https://www.geeksforgeeks.org/problems/find-h-index--165609/1

// SOLUTION:

class Solution {
    public int hIndex(int[] citations) {
        // code here
        // Convert the array to Integer[] for descending sort
        Integer[] cit = new Integer[citations.length];
        for(int i = 0; i < citations.length; i++)
        {
            cit[i] = citations[i];
        }
        
        Arrays.sort(cit, Collections.reverseOrder());
        
        int h = 0;
        for(int i = 0; i < cit.length; i++)
        {
            if(cit[i] >= i + 1)
            {
                h = i + 1;
            }
            else 
            {
                break;
            }
        }
        return h;
    }
}