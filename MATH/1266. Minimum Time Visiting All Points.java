// QUESTION: 1266. Minimum Time Visiting All Points
// LINK: https://leetcode.com/problems/minimum-time-visiting-all-points/description/?envType=daily-question&envId=2026-01-12

// SOLUTIO: BRUTE FORCE BFS APPROACH (Theoretical - Not Recommended):

class Solution {
    /*
    BRUTE FORCE BFS APPROACH (Theoretical - Not Recommended):

    HOW IT WORKS:
    1. Treat each coordinate as grid cell
    2. Use BFS to find shortest path between consecutive points
    3. Sum all shortest paths

    WHY IT'S BRUTE FORCE:
    - BFS explores all possible moves from each point
    - For points far apart, explores many unnecessary cells
    - O(max_distance^2) per pair of points

    COMPLEXITY:
    TIME: O(n * d^2) where d = max distance between points
    SPACE: O(d^2) for BFS queue and visited set

    GOOD FOR: Understanding the problem only
    */


    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            int[] start = points[i];
            int[] end = points[i + 1];

            // Use BFS to find minimum steps
            totalTime += bfsMinSteps(start, end);
        }

        return totalTime;
    }
    private int bfsMinSteps(int[] start, int[] end)
    {
        // 8 Possible moves: 4 cardinal + 4 diagonal
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new int[]{start[0], start[1]});
        visited.add(start[0] + "," + start[1]);

        int steps = 0;

        while(!queue.isEmpty())
        {
            int size = queue.size();

            for(int i = 0; i < size; i++)
            {
                int[] current = queue.poll();

                /// Check if reached destination
                if(current[0] == end[0] && current[1] == end[1])
                {
                    return steps;
                }

                // Try all 8 possible moves
                for(int[] dir : directions)
                {
                    int newX = current[0] + dir[0];
                    int newY = current[1] + dir[1];
                    String key = newX + "," + newY;

                    if(!visited.contains(key))
                    {
                        visited.add(key);
                        queue.offer(new int[] {newX, newY});
                    }
                }
            }
            steps++;
        }
        return steps;
    }
}


// APPROACH 2: SIMULATION (Step-by-Step Movement):

class Solution {
    /*
    APPROACH 2: SIMULATION (STEP - BY - STEP MOVEMENT):

    HOW IT WORKS:
    1. Start from first point
    2. While not at next point, move towards it
    3. Each move: prefer diagonal, then horizontal/vertical
    4. Count each move as 1 second

    SIMULATION STRATEGY:
    - Always move diagonally if possible (covers both x and y)
    - Then move horizontally or vertically as needed

    COMPLEXITY:
    TIME: O(n * d) where d = distance between points
    SPACE: O(1)

    BETTER THAN BFS but still not optimal
    */


    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            int[] current = points[i];
            int[] target = points[i + 1];

            int timeBetween = 0;

            // Simulate movement from current to target
            while(current[0] != target[0] || current[1] != target[1])
            {
                // Determine movement directions
                int dx = Integer.compare(target[0], current[0]);
                int dy = Integer.compare(target[1], current[1]);

                // Move diagonally if both directions needed
                if(dx != 0 && dy != 0)
                {
                    current[0] += dx;
                    current[1] += dy;
                }

                // Move Horizontally
                else if(dx != 0)
                {
                    current[0] += dx;
                }
                // Move Vertically
                else
                {
                    current[1] += dy;
                }
                timeBetween++;
            }
            totalTime += timeBetween;
        }
        return totalTime;
    }
}


// APPROACH 3: CHEBYSHEV DISTANCE (Mathematical Insight):

class Solution {
    /*
    APPROACH 3: CHEBSHEV DISTANCE (Mathematical Insight):

    HOW IT WORKS:
    1. Key insight: Minimum time = max(|dx|, |dy|)
    2. Reason: Diagonal moves cover both x and y simultaneously
    3. We can move max(|dx|, |dy|) steps, each covering 1 unit

    PROOF:
    - Let dx = x2 - x1, dy = y2 - y1
    - In each second, we can change both x and y at most 1
    - So time = max(|dx| - |dy|)

    EXAMPLE:
    From (1, 1) to (3, 4):
    dx = 2, dy = 3
    max(2, 3) = 3 seconds

    COMPLEXITY:
    TIME: O(n) - Single pass through points
    SPACE: O(1)
    */

    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            int[] point1 = points[i];
            int[] point2 = points[i + 1];

            // Calculate differences
            int dx = Math.abs(point2[0] - point1[0]);
            int dy = Math.abs(point2[1] - point1[1]);

            // Chebshev distance
            totalTime += Math.max(dx, dy);
        }
        return totalTime;
    }
}


// APPROACH 4: MANHATTAN WITH DIAGONAL OPTIMIZATION:

class Solution {
    /*
    APPROACH 4: MANHATTAN WITH DIAGONAL OPTIMIZATION:

    HOW IT WORKS:
    1. Manhattan distance = |dx| + |dy|
    2. Each diagonal move saves 1 second (covers both axes)
    3. Maximum diagonal move saves 1 second (covers both axes)
    4. Time = Manhattan distance - min(|dx|, |dy|)

    MATHEMATICAL FORMULA:
    time = |dx| + |dy| - min(|dx| , |dy|)
         = max(|dx|, |dy|) (same as Chebshev)

    This shows both approaches are equivalent

    COMPLXITY:
    TIME: O(N)
    SPACE: O(1)
    */

    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            int[] p1 = points[i];
            int[] p2 = points[i + 1];

            int dx = Math.abs(p2[0] - p1[0]);
            int dy = Math.abs(p2[1] - p1[1]);

            // Method 1: Manhattan - diagonal savings
            int manhattan = dx + dy;
            int diagonalSavings = Math.min(dx, dy);
            totalTime += manhattan - diagonalSavings;

            //Equivalent to: totalTime += Math.max(dx, dy);
        }
        return totalTime;
    }
}

// APPROACH 5: VECTOR DECOMPOSITION:

class Solution {
    /*
    APPROACH 5: VECTOR DECOMPOSITION:

    HOW IT WORKS:
    1. Decompose movement into diagonal + straight components
    2. Diagonal moves: min(|dx|, |dy|)
    3. Straight moves: |dx - dy|
    4. Total = diagonal + straight = max(|dx|, |dy|)

    VISUALIZATION:
    Movement = diagonal + remaining
    Example: (0, 0) to (2, 5)
    - Diagonal moves: min(2, 5) = 2
    - Remaining vertical: 5 - 2 = 3
    - Total: 2 + 3 = 5

    COMPLEXITY:
    TIME: O(n)
    SPACE: O(1)
    */

    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            int x1 = points[i][0];
            int y1 = points[i][1];
            int x2 = points[i + 1][0];
            int y2 = points[i + 1][1];

            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);

            // Diagonal moves (cover both axes)
            int diagonalMoves = Math.min(dx, dy);

            // Remaining straight moves
            int straightMoves = Math.abs(dx - dy);

            totalTime += diagonalMoves + straightMoves;
        }
        return totalTime;
    }
}

// APPROACH 6: RECURSIVE (Divide and Conquer - Overkill):

class Solution {
    /*
    APPROACH 6: RECURSIVE (Divide and Conquer - OverKill):

    HOW IT WORKS:
    1. Recursively break down movement
    2. Base case: reached destination (0 time)
    3. Recursive case: make optimal move, recurse

    WHY OVERKILL
    - Simple formula exists
    - Recursion adds unnecessary overhead
    - Demonstrates different thinking

    COMPLEXITY:
    TIME: O(n * d) where d = distance
    SPACE: O(d) recursion depth
    */

    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            totalTime += recursiveTime(points[i], points[i + 1]);
        }
        return totalTime;
    }
    private int recursiveTime(int[] start, int[] end)
    {
        // Base case: already at destination
        if(start[0] == end[0] && start[1] == end[1])
        {
            return 0;
        }

        // Determine move direction
        int dx = Integer.compare(end[0], start[0]);
        int dy = Integer.compare(end[1], start[1]);

        // Make the move
        int[] newStart = {start[0] + dx, start[1] + dy};

        // Recursively compute remaining time
        return 1 + recursiveTime(newStart, end);
    }
}


// APPROACH 7: ITERATIVE WITH WHILE LOOP (Clean Simulation):


class Solution {
    /*
    APPROACH 7: ITERATIVE WITH WHILE LOOP (Clean Simulation):

    HOW IT WORKS:
    1. Track current position
    2. Move step by step towards target
    3. Use Math.signum to determine direction

    CLEANER THAN APPROACH 2:
    - Uses Math.signum instead of compare
    - More readable

    COMPLEXITY:
    TIME: O(n * d)
    SPACE: O(1)
    */

    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            int[] current = {points[i][0], points[i][1]};
            int[] target = points[i + 1];

            while(current[0] != target[0] || current[1] != target[1])
            {
                // Calculate direction (returns -1, 0, or 1)
                int dirX = (int) Math.signum(target[0] - current[0]);
                int dirY = (int) Math.signum(target[1] - current[1]);

                // Move (diagonal if both non-zero)
                current[0] += dirX;
                current[1] += dirY;

                totalTime++;
            }
        }
        return totalTime;
    }
}


//APPROACH 8: MOST OPTIMAL (Chebyshev Distance - Final):


class Solution {
    /*
    APPROACH 8: MOST OPTIMAL (Chebyshev Distance - Final):

     FINAL OPTIMAL SOLUTION:
    1. Use Chebyshev distance: max(|dx|, |dy|)
    2. Simple, elegant, O(n) time
    
    WHY THIS IS BEST:
    - O(n) time, O(1) space
    - One-liner calculation
    - Easy to understand and explain
    
    MATHEMATICAL PROOF:
    Let t be time needed
    In each second: |Δx| ≤ 1 and |Δy| ≤ 1
    So after t seconds: |dx| ≤ t and |dy| ≤ t
    Therefore: t ≥ max(|dx|, |dy|)
    And we can achieve t = max(|dx|, |dy|) by moving diagonally
    
    FINAL VERSION FOR INTERVIEWS:
    */

    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for(int i = 0; i < points.length - 1; i++)
        {
            int[] p1 = points[i];
            int[] p2 = points[i + 1];

            // Chebyshev distance formula
            int dx = Math.abs(p2[0] - p1[0]);
            int dy = Math.abs(p2[1] - p1[1]);

            totalTime += Math.max(dx, dy);
        }
        return totalTime;
    }
}

//BONUS: ONE-LINER USING STREAMS (Java 8+):

class Solution {
    /*
    BONUS: ONE-LINER USING STREAMS (Java 8+):
    
    HOW IT WORKS:
    1. Use IntStream to iterate through points
    2. Calculate Chebyshev distance for each consecutive pair
    3. Sum all distances
    
    CLEAN AND CONCISE:
    - Functional programming style
    - Good for code golf
    
    COMPLEXITY: Same as optimal
    */
    
    public int minTimeToVisitAllPoints(int[][] points) {
        return java.util.stream.IntStream.range(0, points.length - 1)
            .map(i -> Math.max(
                Math.abs(points[i+1][0] - points[i][0]),
                Math.abs(points[i+1][1] - points[i][1])
            ))
            .sum();
    }
}

