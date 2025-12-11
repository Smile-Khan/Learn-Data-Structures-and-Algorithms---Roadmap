// QUESTION: 3531. Count Covered Buildings
// LINK: https://leetcode.com/problems/count-covered-buildings/description/?envType=daily-question&envId=2025-12-11

// SOLUTION: Using HashMaps (O(m) time, O(m) space)


import java.util.*;

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        
        Map<Integer, List<Integer>> rows = new HashMap<>();
        Map<Integer, List<Integer>> cols = new HashMap<>();

        // Step 1: Group buildings by row and column. CORRECTLY.
        for (int[] b : buildings) {
            int x = b[0];
            int y = b[1];

            rows.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            cols.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        // Step 2: Sort all the generated lists.
        for (List<Integer> list : rows.values()) {
            Collections.sort(list);
        }
        for (List<Integer> list : cols.values()) {
            Collections.sort(list);
        }

        int covered = 0; 

        // Step 3: Check each building.
        for (int[] b : buildings) {
            int x = b[0];
            int y = b[1];

            List<Integer> rowList = rows.get(x);
            List<Integer> colList = cols.get(y); // Use the correct map.

            // A building must have neighbors to be potentially covered.
            // If the list is null or has < 2 elements, it can't have a distinct min and max.
            if (rowList == null || colList == null) {
                continue;
            }

            // The O(1) check for being "in the middle".
            boolean hasLeftAndRight = rowList.size() > 1 && (y > rowList.get(0) && y < rowList.get(rowList.size() - 1));
            boolean hasUpAndDown = colList.size() > 1 && (x > colList.get(0) && x < colList.get(colList.size() - 1));

            if (hasLeftAndRight && hasUpAndDown) {
                covered++;
            }
        }
        return covered;
    }
}