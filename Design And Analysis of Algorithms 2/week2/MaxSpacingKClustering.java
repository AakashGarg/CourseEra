/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import mst.QuickFindUFModified;

/**
 *
 * @author Aakash
 */
public class MaxSpacingKClustering {

    private int N;
    private int E;
    private Edge[] edgeList;

    /**
     * @param args the command line arguments
     */
    public void getData(boolean fromFile) throws FileNotFoundException, IOException {
        // TODO code application logic here
        BufferedReader br = getReader(fromFile);
        String[] line = null;
        N = Integer.parseInt(br.readLine());
        E = 0;
        ArrayList<Edge> list = new ArrayList<Edge>();
        String inputLine = null;
        while ((inputLine = br.readLine()) != null) {
            line = inputLine.trim().split(" ");
            int sNode = Integer.parseInt(line[0]);
            int dNode = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            list.add(new Edge(sNode, dNode, weight));
        }
        edgeList = list.toArray(new Edge[1]);
        E = edgeList.length;
    }

    private static BufferedReader getReader(boolean flag) throws FileNotFoundException {
        BufferedReader br = null;
        if (flag == true) {
            File inputFile = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 2\\clustering1.txt");
            br = new BufferedReader(new FileReader(inputFile));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    private long getMaxSpacing(int K) {
        int k = N;
        long S = 0;
        QuickFindUFModified uf = new QuickFindUFModified(N);
        Arrays.sort(edgeList);
        int i = 0;
        for (i = 0; (k > K) && (i < edgeList.length); i++) {
            Edge edge = edgeList[i];
            if (!uf.connected(edge.sNode - 1, edge.dNode - 1)) {
                uf.union(edge.sNode - 1, edge.dNode - 1);
                k--;
            }
        }

        while (uf.connected(edgeList[i].sNode - 1, edgeList[i].dNode - 1)) {
            i++;
        }
        S = edgeList[i].weight;
        return S;
    }

    public static void main(String[] args) throws IOException {
        MaxSpacingKClustering mst = new MaxSpacingKClustering();
        mst.getData(true);
        int k = 4;
        long S = mst.getMaxSpacing(k);
        System.out.println("k = " + k + " Spacing = " + S);

    }

    private static class Edge implements Comparable<Edge> {

        int sNode;
        int dNode;
        int weight;

        public Edge(int sNode, int dNode, int weight) {
            this.sNode = sNode;
            this.dNode = dNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }

        @Override
        public String toString() {
            return sNode + "->" + dNode + "(" + weight + ")";
        }
    }
}
