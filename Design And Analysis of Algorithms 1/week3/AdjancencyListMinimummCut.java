/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//PROGRAM TO FIND THE MINIMUMM CUT OF AN ADJACENCY LIST BY RUNNING THE PROGRAM square(N) TIMES
package algorithms1.week3;

import java.io.*;
import java.util.*;

/**
 *
 * @author Aakash
 */
public class AdjancencyListMinimummCut {

    public static int minimummCut = 0;
    public static int[][] adjacencyListOrignal = new int[200][200];
    public int[][] adjancencyList;
    public ArrayList<Integer> vertexList = new ArrayList<Integer>();
    public ArrayList<Integer> mergeVertexList = new ArrayList<Integer>();
    public static LinkedHashMap<Integer, ArrayList<Integer>> verticesMap = new LinkedHashMap<Integer, ArrayList<Integer>>(200);

    public static void initializeMap() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(
                new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\ajay garg\\Week 3\\kargerMinCut.txt"));
        BufferedReader br = new BufferedReader(fileReader);

        String line = null;
        while ((line = br.readLine()) != null) {
      //      line = removeUselessSpaces(line);
            Scanner sc = new Scanner(line);
            int vertex = sc.nextInt();
            ArrayList<Integer> list = new ArrayList<Integer>();

            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            Collections.sort(list);
            verticesMap.put(vertex, list);
        }
    }

    public int getMinimummCut(int vertex) {
        int minCut = 0;
        
        while (hasMoreThan2vertices()) {
            mergeVertexList = verticesMap.get(vertex);
            if (mergeVertexList.size() > 0) {
                int randomIndex = (int) (Math.random() * (mergeVertexList.size() - 1));
                int mergeVertex = mergeVertexList.get(randomIndex);

                for (int i = 0; i < verticesMap.size(); i++) {
                    int mergeVertexIndex = Collections.binarySearch(mergeVertexList, mergeVertex);
                    if (mergeVertexIndex >= 0) {
                        //replace all links towards v to towards u
                        mergeVertexList.set(mergeVertexIndex, vertex);
                        Collections.sort(mergeVertexList);
                    }
                }
                if (verticesMap.get(vertex) != null) {
                    //replace all from v to from u
                    ArrayList<Integer> list = verticesMap.get(mergeVertex);
                    for (Integer v : list) {
                        verticesMap.get(vertex).add(v);
                    }
                }
                verticesMap.remove(mergeVertex);
                Collections.sort(verticesMap.get(vertex));
            }
        }
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                if (adjancencyList[i][j] > 0) {
                    minCut = adjancencyList[i][j];
                    break;
                }
            }
        }
        return minCut;
    }

    private boolean hasMoreThan2vertices() {
        int count = 0;
        for (Iterator<Integer> it = vertexList.iterator(); it.hasNext();) {
            Integer vertex = it.next();
            for (int j = 0; j < adjancencyList[vertex - 1].length; j++) {
                if (adjancencyList[vertex - 1][j] > 0) {
                    count++;
                    if (count > 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        initializeMap();
        for (int i = 0; i < 200 * 200; i++) {
            System.out.println("count no : " + i);
            AdjancencyListMinimummCut obj = new AdjancencyListMinimummCut();
            int minCut = obj.getMinimummCut();
            if (minCut > minimummCut) {
                minimummCut = minCut;
            }
        }
        System.out.println(minimummCut);
    }
}
