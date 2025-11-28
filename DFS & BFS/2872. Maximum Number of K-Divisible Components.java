// QUESTION: 2872. Maximum Number of K-Divisible Components
// LINK: https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/?envType=daily-question&envId=2025-11-28

// SOLUTION: DFS 

class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        // Build adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        int[] count = new int[1];
        dfs(0, -1, graph, values, k, count);
        return count[0];
    }
    
    private long dfs(int node, int parent, List<Integer>[] graph, int[] values, int k, int[] count) {
        long currentSum = values[node];
        
        // Process all children
        for (int neighbor : graph[node]) {
            if (neighbor == parent) continue;
            long childSum = dfs(neighbor, node, graph, values, k, count);
            currentSum += childSum;
        }
        
        // Check if we can form a divisible component
        if (currentSum % k == 0) {
            count[0]++;
            return 0; // This component is complete
        }
        
        return currentSum;
    }
}