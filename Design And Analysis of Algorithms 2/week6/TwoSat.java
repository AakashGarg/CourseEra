/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week6;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Aakash
 */
public class TwoSat {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();

    private static HashMap<Integer, HashSet<Integer>> getGraph(String dirName, String fileName) throws FileNotFoundException, IOException {
        File dir = new File(dirName);
        File inputFile = new File(dir, fileName);
        FileReader reader = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, HashSet<Integer>> implGraph = new HashMap<Integer, HashSet<Integer>>(2 * N);
        for (int i = 1; i <= N; i++) {
            implGraph.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < N; i++) {
            String[] tok = br.readLine().trim().split(" ");
            int a = nodeValue(Integer.parseInt(tok[0]));
            int b = nodeValue(Integer.parseInt(tok[1]));
            implGraph.get(a).add(b);
        }
        return implGraph;
    }

    private static int nodeValue(int n) {
        return (n > 0 ? 2 * n : 2 * n - 1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String dir  = "D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 6";
        String file = "2sat1";
        graph = getGraph(dir, file);
        
    }
}
