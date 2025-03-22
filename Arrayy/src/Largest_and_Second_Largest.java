import java.util.Scanner;

public class Largest_and_Second_Largest {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while(t-- > 0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i = 0; i < n; i++)
            {
                arr[i] = sc.nextInt();
            }
            System.out.println(findMaxSum(arr));
        }
        sc.close();
    }
    static int findMaxSum(int[] arr)
    {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for(int num : arr)
        {
            if(num > max1)
            {
                max2 = max1;
                max1 = num;
            }
            else if(num > max2 && num != max1)
            {
                max2 = num;
            }
        }

        return max1 + max2;
    }
}
