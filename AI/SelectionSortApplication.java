package AI;

import java.util.Scanner;

public class SelectionSortApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Application: Sorting Product Prices");

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        int prices[] = new int[n];

        System.out.println("Enter product prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (prices[j] < prices[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = prices[i];
            prices[i] = prices[minIndex];
            prices[minIndex] = temp;
        }

        System.out.println("Sorted prices:");
        for (int i = 0; i < n; i++) {
            System.out.print(prices[i] + " ");
        }

        sc.close();
    }
}
