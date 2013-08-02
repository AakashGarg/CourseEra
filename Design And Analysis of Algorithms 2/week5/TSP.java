/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static java.lang.Math.*;

/**
 *
 * @author Aakash
 */
public class TSP {

    private static float[][] dp;

    private static double solve(double[] x, double[] y) {
        double length = Double.POSITIVE_INFINITY;
        int N = x.length - 1;
        int twoToN = (int) Math.pow(2, N);
        dp = new float[twoToN + 1][N + 1];
        dp[1][1] = (float) 0.0;
        for (int i = 1; i <= twoToN; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Float.POSITIVE_INFINITY;
            }
        }
        dp[1][1] = (float) 0.0;
        int[] len = new int[N + 1];
        int[][] list = initializeIntArray(N);
        list[0] = null;
        for (int i = 1; i <= twoToN; i += 2) {
            String binary = toBinaryString(i, N);
            int m = getBinaryCount(binary, N);
            try {
                //list[m][len[m]++] = binary;
                list[m][len[m]++] = i;
            } catch (NullPointerException e) {
                System.err.println(m + "," + i + "," + binary);
                throw e;
            }
        }
        System.out.println("done initialization");
        for (int m = 2; m <= N; m++) {
            for (int S : list[m]) {
                String binaryCode = toBinaryString(S, N);
                ArrayList<Integer> set = toBinaryList(binaryCode, N);
//                System.out.println("---------");
//                System.out.print("binary code: " + binaryCode);
//                System.out.println(", Using set: " + set);
                for (Integer j : set) {
                    if (j == 1 || (j == 1 && S == 1)) {
                        continue;
                    }
                    for (Integer k : set) {
//                        System.out.print("j=" + j + ", k =" + k);
                        if (k != j) {
                            char[] temp = binaryCode.toCharArray();
//                            System.out.print(", " + binaryCode);
//                            System.out.print(", " + (temp.length - j) + " bit to zero");
                            temp[temp.length - j] = '0';
                            int S_j = Integer.parseInt(String.valueOf(temp), 2);
//                            System.out.print("," + print(temp) + ", S_j:" + S_j + ", k:" + k);
//                            System.out.print(", --" + dp[S_j][k] + " + " + length(x[j], y[j], x[k], y[k]) + "--");
                            dp[S][j] = (float) min(dp[S_j][k] + length(x[j], y[j], x[k], y[k]), dp[S][j]);
//                            System.out.print(", S:"+S+", j:"+j);
                        }
//                        System.out.println("");
                    }
                }
            }
        }
        for (int j = 1; j <= N; j++) {
            length = min(length, dp[twoToN - 1][j] + length(x[j], y[j], x[1], y[1]));
        }
        return length;
    }

    private static double length(double x1, double y1, double x2, double y2) {
        return abs(sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2)));
    }

    public static void main(String... args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double[] x = new double[N + 1];
        double[] y = new double[N + 1];

        for (int i = 1; i <= N; i++) {
            x[i] = sc.nextDouble();
            y[i] = sc.nextDouble();
        }
        double lengthOfPath = solve(x, y);
        //printDP();
        System.out.println("Sol: " + lengthOfPath);
    }

    private static ArrayList<Integer> toBinaryList(int no, int N) {
        //String binary = Integer.toBinaryString((int) (Math.pow(2, 10) - 1));
        String binary = toBinaryString(no, N);
        ArrayList<Integer> set = new ArrayList<Integer>();
        char[] ch = binary.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '1') {
                set.add(N - i + 1);
            }
        }
        return set;
    }

    private static ArrayList<Integer> toBinaryList(String binary, int N) {
        //String binary = Integer.toBinaryString((int) (Math.pow(2, 10) - 1));
        //String binary = toBinaryString(no, N);
        ArrayList<Integer> set = new ArrayList<Integer>();
        char[] ch = binary.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '1') {
                set.add(N - i + 1);
            }
        }
        return set;
    }

    private static int getBinaryCount(String binary, int N) {
        //String binary = Integer.toBinaryString((int) (Math.pow(2, 10) - 1));
        //String binary = toBinaryString(no, N);
        int count = 0;
        char[] ch = binary.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '1') {
                count++;
            }
        }
        return count;
    }

    private static String toBinaryString(int no, int N) {
        String binary = Integer.toBinaryString(no);
        for (int i = binary.length(); i <= N; i++) {
            binary = 0 + binary;
        }
        return binary;
    }

    public static void main(String args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] k = new int[N];
        int twoToN = (int) Math.pow(2, N);
        for (int i = 1; i < twoToN; i++) {
            char[] ch = toBinaryString(i, N).toCharArray();
            int count = 0;
            for (int j = 0; j < ch.length; j++) {
                if (ch[j] == '1') {
                    count++;
                }
            }
            k[count - 1]++;
        }
        for (int i = 0; i < N; i++) {
            System.out.println(i + 1 + " : " + k[i]);
        }
    }

    private static String[][] initializeArray(int N) {
        int[] k = new int[N];
        int twoToN = (int) Math.pow(2, N);
        for (int i = 1; i <= twoToN; i += 2) {
            char[] ch = toBinaryString(i, N).toCharArray();
            int count = 0;
            for (int j = 0; j < ch.length; j++) {
                if (ch[j] == '1') {
                    count++;
                }
            }
            k[count - 1]++;
        }
        String[][] arr = new String[N + 1][];
        for (int i = 1; i <= N; i++) {
            arr[i] = new String[k[i - 1]];
        }
        return arr;
    }

    private static int[][] initializeIntArray(int N) {
        int[] k = new int[N];
        int twoToN = (int) Math.pow(2, N);
        for (int i = 1; i <= twoToN; i += 2) {
            char[] ch = toBinaryString(i, N).toCharArray();
            int count = 0;
            for (int j = 0; j < ch.length; j++) {
                if (ch[j] == '1') {
                    count++;
                }
            }
            k[count - 1]++;
        }
        int[][] arr = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            arr[i] = new int[k[i - 1]];
        }
        return arr;
    }

    private static String print(char[] temp) {
        String ch = "";
        for (char c : temp) {
            ch += c;
        }
        return ch;
    }

    private static void printDP() {
        for (float[] floats : dp) {
            for (float e : floats) {
                System.out.print(e + " ");
            }
            System.out.println("");
        }
    }
}
