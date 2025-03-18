import java.util.Scanner;

public class minAndMax {



   // public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            int[] arr = new int[n];

            // Read array input
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            // Get the minimum number of removals
            int result = minRemovalsToFindMinMax(arr, n);
            System.out.println(result);

            sc.close();
        }

        static int minRemovalsToFindMinMax(int[] arr, int n) {
            // Finding the index of the min and max elements
            int minI = 0, maxI = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] < arr[minI]) minI = i;
                if (arr[i] > arr[maxI]) maxI = i;
            }

            int front = Math.max(minI, maxI) + 1;

            int back = Math.max(n - minI, n - maxI);

            int bothRem = Math.min(minI, maxI) + 1 + (n - Math.max(minI, maxI));


            return Math.min(front, Math.min(back, bothRem));
        }
    }


