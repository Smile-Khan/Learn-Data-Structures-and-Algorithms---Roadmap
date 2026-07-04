// QUESTION: 2492. Minimum Score of a Path Between Two Cities
// LINK: https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/?envType=daily-question&envId=2026-07-04

// SOLUTION: DFS & BFS

class Solution {
    List<int[]>[] adj;
    boolean[] visited;
    int minEdge;

    public int minScore(int n, int[][] roads) {
        // Build adjacency list
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : roads) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            
            // Bidirectional roads
            adj[u].add(new int[]{v, cost});
            adj[v].add(new int[]{u, cost});
        }

        visited = new boolean[n + 1];
        minEdge = Integer.MAX_VALUE;

        bfs(1);  // Start from city 1
        return minEdge;
    }

    private void bfs(int city) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(city);  // ✅ Use the parameter
        visited[city] = true;

        while (!queue.isEmpty()) {
            int currentCity = queue.poll();

            for (int[] neighbor : adj[currentCity]) {
                int nextCity = neighbor[0];
                int cost = neighbor[1];

                // Update minimum edge
                minEdge = Math.min(minEdge, cost);

                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }
    }
}

// DFS

class Solution {
    List<int[]>[]adj;
    boolean[] visited;
    int minEdge;

    public int minScore(int n, int[][] roads) {
        adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++)
        {
            adj[i] = new ArrayList<>();
        }    

        for(int[] edge : roads)
        {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            adj[u].add(new int[]{v, cost});
            adj[v].add(new int[]{u, cost});
        }

        visited = new boolean[n + 1];
        // minEdge 
        minEdge = Integer.MAX_VALUE;

        dfs(1);
        return minEdge;
    }

    private void dfs(int city)
    {
        visited[city] = true;

        for(int[] neighbor : adj[city])
        {
            int nextCity = neighbor[0];
            int cost = neighbor[1];

            minEdge = Math.min(minEdge, cost);

            if(!visited[nextCity])
            {
                dfs(nextCity);
            }
        }
        
    }
}