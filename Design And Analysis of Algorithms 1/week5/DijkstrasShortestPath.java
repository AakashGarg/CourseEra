/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms1.week5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Aakash
 */
public class DijkstrasShortestPath {

    private int N = 200;
    private ArrayList<Integer> X = new ArrayList<Integer>(N);
    private ArrayList<Integer> V = new ArrayList<Integer>(N);
    private long[] A = new long[N];
    private int[][] matrix = new int[N][N];
    private String[] B = new String[N];       //stores the path

    public void initialize() throws IOException {
        String fileNameString = "D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\aakash garg\\week 5\\dijkstraData.txt";
        File dijkstraData = new File(fileNameString);
        FileReader fileReader = new FileReader(dijkstraData);
        BufferedReader br = new BufferedReader(fileReader);

        String line = null;
        while ((line = br.readLine()) != null) {
            line = line.replaceAll(",", " ");
            Scanner sc = new Scanner(line);
            int x = sc.nextInt();
            while (sc.hasNextInt()) {
                int y = sc.nextInt();
                int value = sc.nextInt();
                matrix[x - 1][y - 1] = value;
            }
        }
        for (int i = 0; i < N; i++) {
            V.add(i + 1);
        }
        Collections.sort(V);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public void computeStortestPath(int node) {
        X.add(node);
        V.remove(Integer.valueOf(node));
        A[node - 1] = 0;
        B[node - 1] = "" + node;

        while (X.size() < N) {

            long minDist = Long.MAX_VALUE;
            Integer reqV = null;
            Integer reqW = null;

            for (int v : X) {
                long length = Long.MAX_VALUE;
                Integer reqLocalW = null;
                for (int w : V) {
                    int lvw = Math.min(matrix[v - 1][w - 1], matrix[w - 1][v - 1]);     // a check for a double path
                    if (length > (A[v - 1] + lvw)) {
                        length = (A[v - 1] + lvw);
                        reqLocalW = w;
                    }
                }
                if (length < minDist) {
                    minDist = length;
                    reqV = v;
                    reqW = reqLocalW;
                }
            }
            X.add(reqW);
            V.remove(reqW);
            A[reqW - 1] = minDist;
            int w = reqW;
            B[reqW - 1] = B[reqV - 1] + "-->" + (w);
        }
    }

    public void main() throws IOException {
        // TODO code application logic here
        initialize();
        computeStortestPath(1);
        int[] outNodes = {7, 37, 59, 82, 99, 115, 133, 165, 188, 197};
        for (int i = 0; i < outNodes.length; i++) {
            long value = A[outNodes[i] - 1];
            System.out.print(value + ",");
        }
        System.out.println("");
    }

    public static void main(String[] args) throws IOException {
        DijkstrasShortestPath shortestPath = new DijkstrasShortestPath();
        shortestPath.main();
    }
}
