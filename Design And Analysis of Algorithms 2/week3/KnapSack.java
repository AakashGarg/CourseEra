/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week3;

import java.io.*;

/**
 *
 * @author Aakash
 */
public class KnapSack {

    public static long calculateCost(int[] v, int[] w, int W) {
        int N = v.length;
        long[] a1 = new long[W + 1];
        long[] a2 = new long[W + 1];

        for (int i = 1; i <= N; i++) {
            a2 = new long[W + 1];
            for (int x = 0; x <= W; x++) {
                long a = Long.MIN_VALUE;
                if (x - w[i - 1] >= 0) {
                    a = a1[x - w[i - 1]] + v[i - 1];
                }
                a2[x] = Math.max(a1[x], a);
            }
            a1 = a2;
        }
        return a2[W];
    }

    private static BufferedReader getReader(boolean flag) throws FileNotFoundException {
        BufferedReader br = null;
        if (flag == true) {
            File inputFile = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 3\\knapsack2.txt");
            br = new BufferedReader(new FileReader(inputFile));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = getReader(true);
        String[] line = br.readLine().trim().split(" ");
        int N = Integer.parseInt(line[1]);
        int W = Integer.parseInt(line[0]);
        int[] v = new int[N];
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            line = br.readLine().trim().split(" ");
            v[i] = Integer.parseInt(line[0]);
            w[i] = Integer.parseInt(line[1]);
        }
        System.out.println("Max value : " + calculateCost(v, w, W));
    }
}
