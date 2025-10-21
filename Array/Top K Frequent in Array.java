    // QUESTION: Top K Frequent in Array

    // LINK: https://www.geeksforgeeks.org/problems/top-k-frequent-elements-in-array/1

    // SOLUTION: 

    class Solution {
    public ArrayList<Integer> topKFreq(int[] arr, int k) {
        // Code here
         Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());
        frequencyList.sort((a, b) -> {
            if (a.getValue().equals(b.getValue())) {
                return b.getKey() - a.getKey(); // Larger element has higher preference.
            }
            return b.getValue() - a.getValue();
        });
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(frequencyList.get(i).getKey());
        }
        
        return result;
    }
}
