QUESTION: 3558. Number of Ways to Assign Edge Weights I
LINK: https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i/description/?envType=daily-question&envId=2026-06-11

SOLUTION: 

class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        
        // Build graph
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        // BFS from root to find farthest distance
        int maxDepth = 0;
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int depth = curr[1];
            maxDepth = Math.max(maxDepth, depth);
            
            for (int neighbor : graph[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, depth + 1});
                }
            }
        }
        
        // Number of ways = 2^(maxDepth - 1)
        return pow(2, maxDepth - 1);
    }
    
    private int pow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) result;
    }
}