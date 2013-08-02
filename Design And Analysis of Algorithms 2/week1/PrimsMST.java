/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Aakash
 */
public class PrimsMST {

    ArrayList<ArrayList<Edge>> graph;
    int N, E;       //nodes and edges
    String mst = "";

    private Edge getGreedyEdge(int node) {
        Edge greedyEdge = null;
        ArrayList<Edge> list = new ArrayList<Edge>();
        for (int i = 0; i < graph.size(); i++) {
            ArrayList<Edge> nodesList = graph.get(i);
            for (Edge edge : nodesList) {
                if (edge.endingNode == node) {
                    Edge e = new Edge(i + 1, edge.weight);
                    list.add(e);
                }
            }
        }

        if (graph.get(node - 1).size() > 0) {
            Collections.sort(graph.get(node - 1));
            Edge leastDestNode = graph.get(node - 1).get(0);
            greedyEdge = leastDestNode;
        }
        if (list.size() > 0) {
            Collections.sort(list);
            Edge leastSourceNode = list.get(0);
            if (greedyEdge == null) {
                greedyEdge = leastSourceNode;
            } else if (leastSourceNode.weight < greedyEdge.weight) {
                greedyEdge = leastSourceNode;
            }
        }

        return greedyEdge;
    }

    private void mergeNode(int destNode, int sourceNode) {
        ArrayList<Edge> nodeList = null;

        for (int node = 1; node <= N; node++) {
            Integer minWeight = null;

            for (int i = 0; i < graph.get(node - 1).size(); i++) {
                Edge edge = graph.get(node - 1).get(i);

                if (edge.endingNode == destNode || edge.endingNode == sourceNode) {
                    if ((minWeight == null) || (edge.weight < minWeight)) {
                        minWeight = edge.weight;
                    }
                    graph.get(node - 1).remove(edge);
                }
                if (minWeight != null && node != sourceNode) {
                    graph.get(node - 1).add(new Edge(sourceNode, minWeight));
                }
            }
        }

        graph.get(sourceNode - 1).addAll(graph.get(destNode - 1));
        graph.set(destNode - 1, new ArrayList<Edge>());

        //remove self loops

        for (int i = 0; i < graph.get(sourceNode - 1).size(); i++) {
            Edge edge = graph.get(sourceNode - 1).get(i);
            if (edge.endingNode == sourceNode) {
                graph.get(sourceNode - 1).remove(edge);
     //           System.out.println("deleting edge "+edge);
            }
        }
    }

    public long getMSTCost() throws IOException {
        getData();
        long mstCost = 0;

        PrintStream out = new PrintStream(new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 1\\output.txt"));
        ArrayList<Edge> mstEdges = new ArrayList<Edge>(N - 1);
        printData(out);
        for (int node = 1, nodeCount = 1; nodeCount < N; nodeCount++) {
            Edge mergeEdge = getGreedyEdge(node);
            out.println("");
            out.println("edge to merge ----> " + mergeEdge);
            if (mergeEdge == null) {
                System.err.println("error at : " + nodeCount);;
            }
            mstEdges.add(mergeEdge);
            mergeNode(mergeEdge.endingNode, node);
            printData(out);
        }

        out.println("Calculatong cost...");
        for (Edge edge : mstEdges) {
            mstCost += edge.weight;
            out.println("adding cost:" + edge.weight);
        }
        printData(out);
        return mstCost;
    }

    public void getData() throws FileNotFoundException, IOException {
        // TODO code application logic here
        BufferedReader br = getReader(true);
        String[] line = br.readLine().trim().split(" ");
        N = Integer.parseInt(line[0]);
        E = Integer.parseInt(line[1]);
        graph = new ArrayList<ArrayList<Edge>>(N);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < E; i++) {
            line = br.readLine().trim().split(" ");
            int sourceNode = Integer.parseInt(line[0]);
            int destNode = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            graph.get(sourceNode - 1).add(new Edge(destNode, weight));
        }
    }

    private static BufferedReader getReader(boolean flag) throws FileNotFoundException {
        BufferedReader br = null;
        if (flag == true) {
            File inputFile = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 1\\edges.txt");
            br = new BufferedReader(new FileReader(inputFile));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    public static void main(String[] args) throws IOException {
        PrimsMST mst = new PrimsMST();
        long mstCost = mst.getMSTCost();
        System.out.println("MST Cost = " + mstCost);
    }

    public void printData(PrintStream out) {
        out.println("Graph Data : ");
        for (int i = 0; i < graph.size(); i++) {
            out.println();
            out.print(i + 1 + " : ");
            for (Edge edge : graph.get(i)) {
                out.print(edge.toString2());
            }
        }
    }

    class Edge implements Comparable<Edge> {

        int endingNode;
        int weight;

        public Edge(int endingNode, int weight) {
            this.endingNode = endingNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }

        @Override
        public String toString() {
            return "endind node:" + this.endingNode + " ,weight:" + this.weight;
        }

        public String toString2() {
            return "(" + this.endingNode + "," + this.weight + ")";
        }
    }
}
