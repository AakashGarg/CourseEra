/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// greedy algorithms implementation from lecture for minimizing the weighted sum of completion times.
package algorithms2.week1;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Aakash
 */
public class MinCompletionTimes {

    Job[] jobs;
    static boolean ratio = false;

    public void extractInput(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        jobs = new Job[N];

        for (int n = 0; n < N; n++) {
            String[] lineTok = br.readLine().trim().split(" ");
            int weight = Integer.parseInt(lineTok[0]);
            int length = Integer.parseInt(lineTok[1]);
            jobs[n] = new Job(n, weight, length);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean fileInput = true;

        MinCompletionTimes obj = new MinCompletionTimes();
        long ans1 = obj.solve(getReader(fileInput));
        System.out.println("difference : " + ans1);
        ratio = true;

        long ans2 = obj.solve(getReader(fileInput));
        System.out.println("ratio : " + ans2);
    }

    private static BufferedReader getReader(boolean flag) throws FileNotFoundException {
        BufferedReader br = null;
        if (flag == true) {
            File inputFile = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 1\\jobs.txt");
            br = new BufferedReader(new FileReader(inputFile));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    public long solve(BufferedReader br) throws FileNotFoundException, IOException {
        // TODO code application logic here
        extractInput(br);
        Arrays.sort(jobs);
        int count = 0;
        if (!ratio) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (int n = 0; n < jobs.length - 1; n++) {
                if ((jobs[n].diff == jobs[n + 1].diff) && (jobs[n].weight < jobs[n + 1].weight)) {
                    Job tempJob = jobs[n];
                    jobs[n] = jobs[n + 1];
                    jobs[n + 1] = tempJob;
                    tempJob = null;
                    count++;
                    set.add(jobs[n].diff);
                }
            }
            System.out.println(count + " swaps, " + set.size() + " unique differences");
        }
        long ans = 0;
        long length = 0;
        for (Job job : jobs) {
            length += job.length;
            if ((ans + (job.weight * length)) < ans) {
                System.err.println("error");
            }
            ans += job.weight * length;
        }

        count = 0;
        for (int i = 0; i < jobs.length - 1; i++) {
            if (MinCompletionTimes.ratio ? (jobs[i].ratio < jobs[i + 1].ratio) : (jobs[i].diff < jobs[i + 1].diff)) {
                count++;
            }
        }
        assert (count == 0) : "error detected";
        return ans;
    }

    class Job implements Comparable<Job> {

        int id;
        int weight;
        int length;
        int diff;
        float ratio;

        public Job(int id, int weight, int length) {
            this.id = id;
            this.weight = weight;
            this.length = length;
            diff = weight - length;
            ratio = (float) ((weight * 1.0 / length) * 100000);
        }

        @Override
        public int compareTo(Job o) {

            if (!MinCompletionTimes.ratio) {
                return (o.diff - this.diff) * 100000 + (o.weight - this.weight);
            } else {
                return (int) (o.ratio - this.ratio);
            }
        }
    }
}
