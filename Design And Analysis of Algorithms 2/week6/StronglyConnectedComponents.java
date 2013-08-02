/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week6;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Aakash
 */
public class StronglyConnectedComponents {

    public static boolean debug = true;
    public static boolean print = true;
    public static int N = 2 * 100000;
    public static ArrayList<Integer> mainGraph[] = new ArrayList[N];
    public static ArrayList<Integer> revGraph[] = new ArrayList[N];
    public static boolean[] explored = new boolean[N];
    public static int[] finishingTime = new int[N];
    public static int[] leader = new int[N];
    public static int t = 0;
    public static Integer s = null;
    public static ArrayList<Integer> sscNodesList = new ArrayList<Integer>();

    public static void DFS_Loop(ArrayList<Integer> graph[]) {
        t = 0;
        s = null;
        int sscCount = 0;
        sscNodesList = new ArrayList<Integer>();
        for (int i = N - 1; i >= 0; i--) {
            if (explored[i] == false) {
                sscCount++;
                int sscNodes = -t;
                s = i;
                DFS(graph, i);
                sscNodes += t;
                sscNodesList.add(sscNodes);
            }
        }
        if (print == true) {
            System.out.println("no of ssc = " + sscCount);
        }
    }

    public static void DFS(ArrayList<Integer> graph[], int i) {
        explored[i] = true;
        leader[i] = s;
        for (int e : graph[i]) {
            int j = e - 1;
            if (explored[j] == false) {
                DFS(graph, j);
            }
        }
        t++;
        finishingTime[i] = t;
        if (debug == true) {
            System.out.println(i + 1 + "->" + t);
        }
    }

    private static int nodeValue(int n) {
        return (n > 0 ? 2 * n : 2 * Math.abs(n) - 1);
    }

    private static void initializeGraphs() throws FileNotFoundException, IOException {
        File SCC_File = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 6\\2sat1.txt");
        FileReader fileReader = new FileReader(SCC_File);
        BufferedReader br = new BufferedReader(fileReader);

        for (int i = 0; i < N; i++) {
            mainGraph[i] = new ArrayList<Integer>();
            revGraph[i] = new ArrayList<Integer>();
        }
        System.out.println(N == 2 * Integer.parseInt(br.readLine()));
        System.out.print("creating graphs...");
        String line = null;
        while ((line = br.readLine()) != null) {
            int a = Integer.parseInt(line.split(" ")[0]);
            int b = Integer.parseInt(line.split(" ")[1]);
            // System.out.println(a + "," + b);
            mainGraph[nodeValue(-a) - 1].add(nodeValue(b));
            mainGraph[nodeValue(-b) - 1].add(nodeValue(a));
            revGraph[nodeValue(b) - 1].add(nodeValue(-a));
            revGraph[nodeValue(a) - 1].add(nodeValue(-b));
            if (debug) {
                System.out.println("mainGraph: connecting " + nodeValue(-a) + " to " + nodeValue(b));
                System.out.println("mainGraph: connecting " + nodeValue(-b) + " to " + nodeValue(a));
                System.out.println("revGraph: connecting " + nodeValue(b) + " to " + nodeValue(-a));
                System.out.println("revGraph: connecting " + nodeValue(a) + " to " + nodeValue(-b));
            }
        }
    }

    private static void initializeGraphs2() throws FileNotFoundException, IOException {
        File SCC_File = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 6\\2sat1.txt");
        FileReader fileReader = new FileReader(SCC_File);
        BufferedReader br = new BufferedReader(fileReader);

        for (int i = 0; i < N; i++) {
            mainGraph[i] = new ArrayList<Integer>();
            revGraph[i] = new ArrayList<Integer>();
        }
        System.out.println(N == 2 * Integer.parseInt(br.readLine()));
//        N = Integer.parseInt(br.readLine());
        System.out.print("creating graphs...");
        String line = br.readLine();
        while (line != null) {
            int tailInitial = nodeValue(Integer.parseInt(line.split(" ")[0]));
            int head = nodeValue(Integer.parseInt(line.split(" ")[1]));
//            int head = Integer.parseInt(line.split(" ")[1]);
            mainGraph[tailInitial - 1].add(head);
            revGraph[head - 1].add(tailInitial);

            while ((line = br.readLine()) != null) {
                int tail = Integer.parseInt(line.split(" ")[0]);
                head = Integer.parseInt(line.split(" ")[1]);

                if (tail == tailInitial) {
                    mainGraph[tailInitial - 1].add(head);
                    revGraph[head - 1].add(tailInitial);
                } else {
                    break;
                }
            }
        }
    }

    public static int node(int n) {
        return n - 1;
    }

    private static ArrayList<Integer>[] replaceNodes(ArrayList<Integer>[] graph, int[] t) {
        ArrayList<Integer> newGraph[] = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            newGraph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                graph[i].set(j, t[graph[i].get(j) - 1]);
                //     System.out.println("replacing " + graph[i].get(j) + " with " + t[graph[i].get(j) - 1]);
            }
            newGraph[t[i] - 1] = graph[i];
        }
        return newGraph;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        print = false;
        debug = false;
        initializeGraphs();
        System.out.println("Done");
        System.out.print("Calling DFS on rev. graph...");
        DFS_Loop(revGraph);
        System.out.println("Done");
        explored = new boolean[N];
        leader = new int[N];
        ArrayList<Integer> graph[] = replaceNodes(mainGraph, finishingTime);
        System.out.println("Calling DFS on new magical graph...");
        print = true;
        DFS_Loop(graph);
        Collections.sort(sscNodesList);
        Collections.reverse(sscNodesList);
        boolean isSatisfiable = true;
        for (int i = 0; i < leader.length; i += 2) {
            if (leader[i] == leader[i + 1]) {
                isSatisfiable = false;
                break;
            }
        }
        System.out.println(isSatisfiable);
    }

    private static void test() {

        mainGraph[node(7)].add(1);
        mainGraph[node(1)].add(4);
        mainGraph[node(4)].add(7);
        mainGraph[node(9)].add(7);
        mainGraph[node(9)].add(3);
        mainGraph[node(3)].add(6);
        mainGraph[node(6)].add(9);
        mainGraph[node(8)].add(6);
        mainGraph[node(2)].add(8);
        mainGraph[node(5)].add(2);
        mainGraph[node(8)].add(5);

        revGraph[node(1)].add(7);
        revGraph[node(7)].add(4);
        revGraph[node(4)].add(1);
        revGraph[node(7)].add(9);
        revGraph[node(9)].add(6);
        revGraph[node(6)].add(3);
        revGraph[node(3)].add(9);
        revGraph[node(6)].add(8);
        revGraph[node(8)].add(2);
        revGraph[node(2)].add(5);
        revGraph[node(5)].add(8);
    }

    private static void test1() {

        int[] arr = {11, 5, 5, 6, 6, 11,
            8, 9, 9, 3, 9, 1, 3, 1, 1, 8,
            10, 2, 2, 7, 7, 10,
            5, 4,
            4, 10, 4, 2,
            9, 10, 3, 7,
            6, 8, 6, 9
        };
        for (int i = 0; i < arr.length; i += 2) {
            mainGraph[node(arr[i])].add(arr[i + 1]);
            revGraph[node(arr[i + 1])].add(arr[i]);
        }

    }
}
