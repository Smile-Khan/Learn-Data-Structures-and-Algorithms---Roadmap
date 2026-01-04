// QUESTION: 1390. Four Divisors
// LINK: https://leetcode.com/problems/four-divisors/description/?envType=daily-question&envId=2026-01-04

// SOLUTION: BRUTE FORCE 

class Solution {
    public int sumFourDivisors(int[] nums) {
        /*
        BRUTE FORCE APPROACH:
        For each number, find all divisors by checking up to √n
        If exactly 4 divisors, sum them.

        HOW IT WORKS:
        - For each number, check divisibility from 1 to √n
        - Collect divisors (both i and n/i when n%i == 0)
        - If count == 4, sum all divisors

        TIME COMPLEXITY:  O(∑ √nums[i]) 
        For nums[i] <= 10^5, √nums[i] <= 316
        For n = 10^4 * 316 = 3.16M operations -> OK

        SPACE COMPLEXITY: O(√n) for storing divisors (or O(1) with counting)
        */

        int totalSum = 0;

        for(int num : nums)
        {
            // Store divisors (we need at most 4)
            List<Integer> divisors = new ArrayList<>();

            // Check up to √num
            for(int i = 1; i * i <= num; i++)
            {
                if(num % i == 0)
                {
                    // Add divisor i
                    divisors.add(i);

                    // Add complement divisor if different
                    if(i != num / i)
                    {
                        divisors.add(num / i);
                    }
                }
            }

            // If exactly 4 divisors, sum them
            if(divisors.size() == 4)
            {
                for(int divisor : divisors)
                {
                    totalSum += divisor;
                }
            }
        }
        return totalSum;
    }
}

// APPROACH 2: OPTIMIZED BRUTE FORCE

class Solution {
    public int sumFourDivisors(int[] nums) {
        /*
        OPTIMIZED BRUTE FORCE:
        Count divisors without storing them, exit early if >4.

        KEY OPTIMIZATIONS:
        1. Exit early if divisor count exceeds 4
        2. Track divisor sum while counting
        3. No need to store all divisors

        TIME COMPLEXITY: O(∑ √nums[i]) same as Approach 1 but faster in practice
        SPACE COMPLEXITY: O(1) per number

        BEST PRACTICE: Early exit improves performance
        */

        int totalSum = 0;

        for(int num : nums)
        {
            // Variables to track divisors
            int divisorCount = 0;
            int divisorSum = 0;

            // Check divisors up to √num
            for(int i = 1; i * i <= num; i++)
            {
                if(num % i == 0)
                {
                    // First divisor: i
                    divisorCount++;
                    divisorSum += i;

                    // Second divisor: num / i (if different)
                    if(i != num / i)
                    {
                        divisorCount++;
                        divisorSum += num / i;
                    }

                    // Early exit: If we already have more than 4 divisors
                    if(divisorCount > 4)
                    {
                        break;
                    }
                }
            }

            // If exactly 4 divisors, add to total sum
            if(divisorCount == 4)
            {
                totalSum += divisorSum;
            }
        } 
        return totalSum;
    }
}

// APPROACH 3: MATHEMATICAL INSIGHT (PRIME SQUARES AND CUBES)

class Solution {
    public int sumFourDivisors(int[] nums) {
        /*
        MATHEMATICAL APPROACH:
        Number with exactly 4 divisors are of two tuypes:

        1. p^3 where p is prime (divisors: 1, p, p^2, p^3)
        2. p * q where p and q are distinct primes (divisors: 1, p, 1, pq)

        WHY?
        - For p^3: divisors are powers of p from 0 to 3 -> 4 divisors
        - For p*q: divisors are combinations -> 4 divisors
        - Other numbers have different divisor counts

        STRATEGY:
        For each number, check if it's of form p3 or p*q
        If yes, calculate divisor sum directly

        BENEFITS:
        - No need to find all divisors
        - Can use precomputed primes
        - More mathematically elegant
        */

        int maxNum = 0;
        for(int num : nums)
        {
            maxNum = Math.max(maxNum, num);
        }

        // Sieve of Eratosthenes to find primes up to maxNum
        boolean[] isPrime = sieve(maxNum);

        int totalSum = 0;

        for(int num : nums)
        {
            // Check if num is of form p^3
            int cubeRoot = (int) Math.cbrt(num);
            if(cubeRoot * cubeRoot * cubeRoot == num && isPrime[cubeRoot])
            {
                // num = p^3, divisors: 1, p, p^2, p^3
                totalSum += 1 + cubeRoot + cubeRoot * cubeRoot + num;
                continue;
            }

            // Check if num is of form p * q (p and q distinct primes)
            // Find first prime divisor

            for(int p = 2; p * p <= num; p++)
            {
                if(isPrime[p] && num % p == 0)
                {
                    int q = num / p;

                    // Check if q is prime and q != p
                    if(isPrime[q] && q != p)
                    {
                        // num = p * q, divisors: 1, p, 1, pq
                        totalSum += 1 + p + q + num;
                    }
                    break;  // Found the only possible prime factorization
                }
            }
        }
        return totalSum;
    }
    
    // Sieve of Eratosthenes to find primes up to n
    private boolean[] sieve(int n)
    {
        boolean[] isPrime = new boolean[n + 1];
        if(n >= 2) isPrime[2] = true;

        for(int i = 3; i <= n; i += 2)
        {
            isPrime[i] = true;
        }

        for(int i = 3; i * i <= n; i += 2)
        {
            if(isPrime[i])
            {
                for(int j = i * i; j <= n; j += i)
                {
                    isPrime[j] = false;
                }
            }
        }

        // Mark 2 as prime (special case)
        if(n >= 2) isPrime[2] = true;
        return isPrime;
    }
}


// APPROACH: 

class Solution {
    public int sumFourDivisors(int[] nums) {
        /*
        MEMOIZATION APPROACH:
        Cache results for numbers we've already computed.

        WHY CACHE:
        - Numbers may repeat in array (like Example 2: [21, 21])
        - Avoid recomputing divisor sums for same number

        IMPLEMENTATION:
        - Use HashMap to store number -> divisor sum (or 0 if not 4 divisors)
        - Check cache before computing

        BENEFITS:
        - Faster when numbers repeat
        - Good for real-world scenarios with duplicates
        */

        Map<Integer, Integer> cache = new HashMap<>();
        int totalSum = 0;

        for(int num : nums)
        {
            // Check cache first
            if(cache.containsKey(num))
            {
                totalSum += cache.get(num);
                continue;
            }

            // Compute divisor count and sum
            int divisorCount = 0;
            int divisorSum = 0;

            for(int i = 1; i * i <= num; i++)
            {
                if(num % i == 0)
                {
                    divisorCount++;
                    divisorSum += i;

                    if(i != num / i)
                    {
                        divisorCount++;
                        divisorSum += num / i;
                    }

                    if(divisorCount > 4) break;
                }
            }

            // Cache the result
            int result = (divisorCount == 4) ? divisorSum : 0;
            cache.put(num, result);

            totalSum += result;
        }
        return totalSum;
    }
}


// APPROACH: 

class Solution {
    private static final int MAX = 100000;
    private static final int[] divisorSumCache;

    // Static initialization block - runs once when class loads
    static {
        /*
        PREPROCESSING APPROACH:
        Precompute divisor sums for ALL numbers up to MAX.

        HOW IT WORKS:
        - Initialize array of size MAX + 1
        - For each number i, add i to all its multiples (sieve-like)
        - Also count divisors
        - Store sum if exactly 4 divisors

        TIME FOR PRECOMPUTATION: O(MAX log MAX) ≈  O(10^5 x log 10^5)
        Once precomputed, each query is O(1)

        GOOD FOR: Multiple calls to sumFourDivisors
        OVERKILL FOR: Single call (but shows deep optimization thinking)
        */

        divisorSumCache = new int[MAX + 1];
        int[] divisorCount = new int[MAX + 1];

        // Sieve - like approach to count divisors and sum them
        for(int i = 1; i <= MAX; i++)
        {
            for(int j = i; j <= MAX; j += i)
            {
                divisorCount[j]++;
                divisorSumCache[j] += i;
            }
        }

        // Zero out sums for numbers without exactly 4 divisors
        for(int i = 1; i <= MAX; i++)
        {
            if(divisorCount[i] != 4)
            {
                divisorSumCache[i] = 0;
            }
        }
    }
    public int sumFourDivisors(int[] nums) {
        /*
        QUERY PHASE:
        Simply sum cached values for each number.

        TIME COMPLEXITY: O(n) where n = nums.length;
        SPACE COMPLEXITY: O(MAX) for precomputed array

        EXTREMELY FAST for queries after preprocessing
        */

        int totalSum = 0;
        for(int num : nums)
        {
            totalSum += divisorSumCache[num];
        }
        return totalSum;
    }
}


// APPROACH: HYBRID (PREPROCESS + ON-DEMAND)

class Solution {
    public int sumFourDivisors(int[] nums) {
        
        /*
        HYBRID APPROACH:
        Combine mathematical insight with caching.

        STRATEGY:
        1. Use mathematical fact: numbers with 4 divisors are p^3 or p * q
        2. Cache results for efficiency
        3. Early exit when possible

        THIS IS THE BEST PRACTICAL SOLUTION
        */

        Map<Integer, Integer> cache = new HashMap<>();

        int totalSum = 0;

        // Find maximum number to limit prime sieve
        int maxNum = 0;
        for(int num : nums)
        {
            maxNum = Math.max(maxNum, num);
        }

        // Sieve primes up to maxNum
        boolean[] isPrime = sieve(maxNum);

        for(int num: nums)
        {
            // Check cache first
            if(cache.containsKey(num))
            {
                totalSum += cache.get(num);
                continue;
            }
            int result = 0;


            // Case 1: Check if num is p^3
            int cubeRoot = (int) Math.cbrt(num);
            if(cubeRoot * cubeRoot * cubeRoot == num && cubeRoot >= 2 && isPrime[cubeRoot])
            {
                result = 1 + cubeRoot + cubeRoot * cubeRoot + num;
            }

            // Case 2: Check if num is p * q
            else 

            {
                // find first prime divisor
                for(int p = 2; p * p <= num; p++)
                {
                    if(p >= 2 && isPrime[p] && num % p == 0)
                    {
                        int q = num / p;
                        // Check if q is prime and different from p
                        if(q >= 2 && isPrime[q] && q != p)
                        {
                            result = 1 + p + q + num;
                        }
                        break;  // Only one possible factorization
                    }
                }
            }
            cache.put(num, result);
            totalSum += result;
        }
        return totalSum;
    }
    // Optimized sieve
    private boolean[] sieve(int n)
    {
        if(n < 2) return new boolean[n + 1];

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for(int i = 2; i * i <= n; i++)
        {
            if(isPrime[i])
            {
                for(int j = i * i; j <= n; j += i)
                {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}