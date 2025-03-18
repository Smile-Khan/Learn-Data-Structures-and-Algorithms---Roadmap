import java.util.Scanner;

public class Find_Max_in_an_Array {

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while(t-- > 0)
        {
            int N = sc.nextInt();

            int arr[] = new int[N];
            for(int i =0; i < N; i++)
            {
                arr[i] = sc.nextInt();
            }
            // Handle case when there is no mountains
            if(N == 0)
            {
                System.out.println(0);
                continue;
            }
            int max = findMax(arr, N);
            System.out.println(max);
        }
        sc.close();
    }
    static int findMax(int[] arr, int N)
    {
        int maxx = Integer.MAX_VALUE;
        for(int i = 0; i< N; i++)
        {
            maxx = Math.max(maxx,arr[i]);
        }
        return  maxx;
    }
}
