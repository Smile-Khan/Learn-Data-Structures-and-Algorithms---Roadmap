// QUESTION: 2840. Check if Strings Can be Made Equal With Operations II
// LINK: https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii/description/?envType=daily-question&envId=2026-03-30

// SOLUTION: SORTING


class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        // Collect characters at even indices
        StringBuilder even1 = new StringBuilder();
        StringBuilder even2 = new StringBuilder();

        // Collect characters at odd indices
        StringBuilder odd1 = new StringBuilder();
        StringBuilder odd2 = new StringBuilder();

        for(int i= 0; i < n; i++)
        {
            if(i % 2 == 0)
            {
                even1.append(s1.charAt(i));
                even2.append(s2.charAt(i));
            }
            else 
            {
                odd1.append(s1.charAt(i));
                odd2.append(s2.charAt(i));
            }
        }


        /// Convert to char arrays and sort
        char[] even1Arr = even1.toString().toCharArray();
        char[] even2Arr = even2.toString().toCharArray();
        char[] odd1Arr = odd1.toString().toCharArray();
        char[] odd2Arr = odd2.toString().toCharArray();

        Arrays.sort(even1Arr);
        Arrays.sort(even2Arr);
        Arrays.sort(odd1Arr);
        Arrays.sort(odd2Arr);

        // Compare
        return Arrays.equals(even1Arr, even2Arr) && Arrays.equals(odd1Arr, odd2Arr);
    }
}

//APPROACH 2: FREQUENCY ARRAY


class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        int[] evenFreq1 = new int[26];
        int[] evenFreq2 = new int[26];
        int[] oddFreq1 = new int[26];
        int[] oddFreq2 = new int[26];

        for(int i = 0; i < n; i++)
        {
            if(i % 2 == 0)
            {
                evenFreq1[s1.charAt(i) - 'a']++;
                evenFreq2[s2.charAt(i) - 'a']++;
            }
            else
            {
                oddFreq1[s1.charAt(i) - 'a']++;
                oddFreq2[s2.charAt(i) - 'a']++;
            }
        }

        // compare frequencies
        for(int i = 0; i < 26; i++)
        {
            if(evenFreq1[i] != evenFreq2[i] || oddFreq1[i] != oddFreq2[i])
            {
                return false;
            }
        }
        return true;
    }
}