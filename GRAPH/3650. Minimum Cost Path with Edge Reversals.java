// QUESTION: 3650. Minimum Cost Path with Edge Reversals
// LINK: https://leetcode.com/problems/minimum-cost-path-with-edge-reversals/description/?envType=daily-question&envId=2026-01-27

// SOLUTION: Dijkstra 

class Solution {
    class Edge{
        int to;
        int weight;
        Edge(int to, int weight)
        {
            this.to = to;
            this.weight = weight;
        }
    }

    class Pair{
        int node;
        long dist;
        Pair(int node, long dist)
        {
            this.node = node;
            this.dist = dist;
        }
    }
    public int minCost(int n, int[][] edges) {
        // Step 1: Initialize the Adjacency lists with weights
        List<Edge>[] adj = new ArrayList[n];
        List<Edge>[] revAdj = new ArrayList[n];

        for(int i = 0; i < n; i++)
        {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }

        for(int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new Edge(v, w));
            revAdj[v].add(new Edge(u, w));
        }


        // Step 2: Dijkstra Setup
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        pq.offer(new Pair(0, 0));

        while(!pq.isEmpty())
        {
            Pair curr = pq.poll();
            int u = curr.node;
            long d = curr.dist;

            if(d > dist[u]) continue;

            // Step 3: Explore Standard Edges (u -> v)
            for(Edge e : adj[u])
            {
                if(dist[u] + e.weight < dist[e.to])
                {
                    dist[e.to] = dist[u] + e.weight;
                    pq.offer(new Pair(e.to, dist[e.to]));
                }
            }

            // Step 4: Explore reverse Edges (u -> v using u's swithc)
            // The switch reverses an incoming edge v -> u into u -> v
            for(Edge e : revAdj[u])
            {
                long reverseCost = (long) e.weight * 2;
                if(dist[u] + reverseCost < dist[e.to])
                {
                    dist[e.to] = dist[u] + reverseCost;
                    pq.offer(new Pair(e.to, dist[e.to]));
                }
            }
        }
        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int)dist[n - 1];
    }
}