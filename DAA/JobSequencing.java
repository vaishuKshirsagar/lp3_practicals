import java.util.*;
class Job {
    char id;
    int deadline;
    int profit;
    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
public class JobSequencing {
    public static void printJobSequence(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job j1, Job j2) {
                return Integer.compare(j2.profit, j1.profit);
            }
        });
        int n = jobs.length;
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }
        char[] result = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];
        Arrays.fill(result, 'X');
        Arrays.fill(slot, false);
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(maxDeadline - 1, jobs[i].deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs[i].id;
                    slot[j] = true;
                    break;
                }
            }
        }
        System.out.print("Job Sequence: ");
        for (char jobId : result) {
            if (jobId != 'X') {
                System.out.print(jobId);
            }
        }
        System.out.println();
        int totalProfit = 0;
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                for (Job job : jobs) {
                    if (job.id == result[i]) {
                        totalProfit += job.profit;
                        break;
                    }
                }
            }
        }
        System.out.println("Total Profit: " + totalProfit);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of jobs:");
        int n = scanner.nextInt();
        Job[] jobs = new Job[n];
        System.out.println("Enter jobs in the format (id deadline profit):");
        for (int i = 0; i < n; i++) {
            char id = scanner.next().charAt(0);
            int deadline = scanner.nextInt();
            int profit = scanner.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }
        printJobSequence(jobs);
    }
}