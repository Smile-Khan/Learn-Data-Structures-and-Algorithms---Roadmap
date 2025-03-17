import java.util.Scanner;

public class Search_an_element_in_an_array {

    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        Scanner sc = new Scanner(System.in);
        {
            int n = sc.nextInt();
            int X = sc.nextInt();

            int A[] = new int[n];
            for(int i = 0; i < n; i++)
            {
                A[i] = sc.nextInt();
            }

            String found = target(A, n, X);
            System.out.println(found);
        }
    }
    static String target(int A[], int n, int X)
    {
        for(int i = 0; i < n; i++)
        {
            if(A[i] == X)
                return "YES";
        }
        return "NO";
    }
}
