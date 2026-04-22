// QUESTION: 2452. Words Within Two Edits of Dictionary
// LINK: https://leetcode.com/problems/words-within-two-edits-of-dictionary/description/?envType=daily-question&envId=2026-04-22


class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();

        for(String query : queries)
        {
            boolean found = false;

            for(String dict : dictionary)
            {
                int diff = 0;

                for(int i = 0; i < query.length(); i++)
                {
                    if(query.charAt(i) != dict.charAt(i))
                    {
                        diff++;
                        if(diff > 2) break; // Early exit 
                    }
                }
                if(diff <= 2)
                {
                    found = true;
                    break;
                }
            }
            if(found)
            {
                result.add(query);
            }
        }
        return result;
    }
}