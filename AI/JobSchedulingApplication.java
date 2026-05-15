package AI;

import java.util.*;

class ProfitJob {
    String id;
    int deadline;
    int profit;

    ProfitJob(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSchedulingApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Application: Job Scheduling for Maximum Profit");

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        ProfitJob jobs[] = new ProfitJob[n];

        System.out.println("Enter job id, deadline and profit:");
        for (int i = 0; i < n; i++) {
            String id = sc.next();
            int deadline = sc.nextInt();
            int profit = sc.nextInt();

            jobs[i] = new ProfitJob(id, deadline, profit);
        }

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;

        for (int i = 0; i < n; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }

        String result[] = new String[maxDeadline];
        boolean slot[] = new boolean[maxDeadline];

        int totalProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = jobs[i].deadline - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = jobs[i].id;
                    totalProfit = totalProfit + jobs[i].profit;
                    break;
                }
            }
        }

        System.out.println("Selected jobs:");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }

        System.out.println("\nTotal profit = " + totalProfit);

        sc.close();
    }
}
