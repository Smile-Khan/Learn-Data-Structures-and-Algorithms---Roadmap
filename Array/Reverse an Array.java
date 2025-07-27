import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n]; // Initialize array of size 'n'

        // Read array elements
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt(); // Fixed: use nextInt() with parentheses
        }

        reverse(arr); // Call the reverse method

        // Print the reversed array
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    // Made static to be called from main()
    public static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}