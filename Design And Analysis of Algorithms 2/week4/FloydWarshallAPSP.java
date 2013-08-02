/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week4;

import java.io.*;
import java.util.*;

/**
 *
 * @author Aakash
 */
public class FloydWarshallAPSP {

    private static Integer sum(Integer a, Integer b) {
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            if (a + b > Integer.MAX_VALUE) {
                System.err.println("out of range");
            }
            return a + b;
        }
    }

    public static Integer[][] getAPSP(ArrayList<Map<Integer, Integer>> dirGraph) {
        int N = dirGraph.size() - 1;
        Integer[][] A = new Integer[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    A[i][j] = 0;
                } else if (dirGraph.get(i).containsKey(j)) {
                    A[i][j] = dirGraph.get(i).get(j);
                } else {
                    A[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    A[i][j] = Math.min(A[i][j], sum(A[i][k], A[k][j]));
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (A[i][i] < 0) {
                return null;
            }
        }
        return A;
    }

    public static ArrayList<Map<Integer, Integer>> getGraph(File file) throws FileNotFoundException {
        ArrayList<Map<Integer, Integer>> dirGraph = new ArrayList<Map<Integer, Integer>>();
        Scanner sc = new Scanner(file);
        int N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            dirGraph.add(new HashMap<Integer, Integer>());
        }
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int e = sc.nextInt();
            dirGraph.get(u).put(v, e);
        }
        return dirGraph;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File dir = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 4");
        ArrayList<Map<Integer, Integer>> dirGraph = getGraph(new File(dir, "g3.txt"));

        Integer[][] d = getAPSP(dirGraph);
        if (d == null) {
            System.out.println("Negative cost cycle");
        } else {
            int min = d[1][1];
            for (int i = 1; i < d.length; i++) {
                for (int j = 1; j < d[0].length; j++) {
                    // System.out.print((d[i][j] == Integer.MAX_VALUE ? "x" : d[i][j]) + " ");
                    if (d[i][j] < min) {
                        min = d[i][j];
                    }
                }
                //      System.out.println("");
            }
            System.out.println(min);
        }
    }

    private static void print(Integer[][] d) {
        System.out.println("********************");
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                System.out.print((d[i][j] == Integer.MAX_VALUE ? "x" : d[i][j]) + " ");
            }
            System.out.println("");
        }
        System.out.println("********************");
    }
}
