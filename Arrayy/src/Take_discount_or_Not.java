import java.util.Scanner;

public class Take_discount_or_Not {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0)
        {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int a[] = new int[n];

            int originalTotal = 0;
            int newTotal = 0;

            for(int i = 0; i < n; i++)
            {
                a[i] = scanner.nextInt();
                originalTotal += a[i];

                if(a[i] <= y)
                {
                    newTotal += 0;
                }
                else
                {
                    newTotal += (a[i] - y);
                }
            }
            int newCost = x + newTotal;

            if(newCost < originalTotal)
            {
                System.out.println("COUPON");
            }
            else
            {
                System.out.println("NO COUPON");
            }
        }
        scanner.close();
    }
}
