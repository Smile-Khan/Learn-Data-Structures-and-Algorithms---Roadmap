// QUESTION: 3600. Maximize Spanning Tree Stability with Upgrades
// LINK: https://leetcode.com/problems/maximize-spanning-tree-stability-with-upgrades/description/?envType=daily-question&envId=2026-03-12

// SOLUTION: BINARY SEARCH ON ANSWER


class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        UnionFind check = new UnionFind(n);
        int mandatoryCount = 0;

        for(int[] edge : edges)
        {
            if(edge[3] == 1)
            {
                if(check.find(edge[0]) == check.find(edge[1]))
                {
                    return -1;
                }
                check.union(edge[0], edge[1]);
                mandatoryCount++;
            }
        }
        if(mandatoryCount > n - 1) return -1;
        
        int maxEdgeStrength = 0;
        for(int[] edge : edges)
        {
            maxEdgeStrength = Math.max(maxEdgeStrength, edge[2]);
        }
        int low = 0;
        int high = maxEdgeStrength * 2;
        int ans = -1;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;

            if(feasible(mid, n, edges, k))
            {
                ans = mid;
                low = mid + 1;
            }
            else 
            {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean feasible(int t, int n, int[][] edges, int k) {
    UnionFind uf = new UnionFind(n);
    
    int upgradesUsed = 0;
    int half = (t + 1) / 2;
    int mandatoryEdgesUsed = 0;
    
    // PHASE 1: Handle mandatory edges
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        int s = edge[2];
        int must = edge[3];
        
        if (must == 1) {
            if (s < t) return false;
            
            // Check if this edge creates a cycle
            if (uf.find(u) == uf.find(v)) {
                return false; // Cycle detected in mandatory edges
            }
            
            uf.union(u, v);
            mandatoryEdgesUsed++;
        }
    }
    
    // Check if mandatory edges already form a valid tree
    if (mandatoryEdgesUsed > n - 1) return false; // Too many mandatory edges
    if (uf.components() == 1 && mandatoryEdgesUsed == n - 1) return true;
    
    // PHASE 2: Add optional edges that already meet threshold
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        int s = edge[2];
        int must = edge[3];
        
        if (must == 0 && s >= t) {
            uf.union(u, v);
            if (uf.components() == 1) return true;
        }
    }
    
    // PHASE 3: Try upgrading edges
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        int s = edge[2];
        int must = edge[3];
        
        if (must == 0 && s >= half && s < t) {
            if (uf.union(u, v)) {
                upgradesUsed++;
                if (upgradesUsed > k) return false;
                if (uf.components() == 1) return true;
            }
        }
    }
    
    return uf.components() == 1;
}
    class UnionFind{
        int[] parent;
        int[] rank;
        int count;  

        UnionFind(int n)
        {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for(int i = 0; i < n; i++)
            {
                parent[i] = i;
            }
        }
        int find(int x)
        {
            if(parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b)
        {
            int ra = find(a);
            int rb = find(b);

            if(ra == rb) return false;

            if(rank[ra] < rank[rb])
            {
                parent[ra] = rb;
            }
            else if(rank[ra] > rank[rb])
            {
                parent[rb] = ra;
            }
            else
            {
                parent[rb] = ra;
                rank[ra]++;
            }
            count--;
            return true;
        }
        int components() {return count;}
    }
}