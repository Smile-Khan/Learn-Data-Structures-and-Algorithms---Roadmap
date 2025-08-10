// QUESTION: 869. Reordered Power of 2
// LINK: https://leetcode.com/problems/reordered-power-of-2/description/?envType=daily-question&envId=2025-08-10


// SOLUTION:

class Solution {
    public boolean reorderedPowerOf2(int n)
    {
        int targetMask = digitMask(n);

        for(int i = 0; i < 31; i++)
        {
            if(targetMask == digitMask(1 << i))
            {
                return true;
            }
        }
        return false;
    }

    // Encoded digit counts into a single int
    private int digitMask(int num)
    {
        int[] count = new int[10];
        while(num > 0)
        {
            count[num % 10]++;
            num /= 10;
        }

        // compress into a base-11 encoded integer
        int mask = 0;

        for(int c : count)
        {
            mask = mask * 11 + c;
        }
        return mask;
    }
}