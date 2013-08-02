/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms1.week3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aakash
 */
public class MinimummCutCounter {

    public static int N = 200;
    public static int[][] matrixOrig = new int[N][N];
    public static int minimummCut = 0;
    public static int[][] matrix;

    public static void initialize() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(
                new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\ajay garg\\Week 3\\kargerMinCut.txt"));
        BufferedReader br = new BufferedReader(fileReader);

        String line = null;
        while ((line = br.readLine()) != null) {
            Scanner sc = new Scanner(line);
            int node = sc.nextInt();

            while (sc.hasNextInt()) {
                int nextNode = sc.nextInt();
                matrixOrig[node - 1][nextNode - 1]++;
            }
        }
    }

    public static int getMinimummCut(int node) {
        int minCut = 0;

        //first add transpose of matrix[][node-1] to matrix[node-1][]
        //first replace connections towards node ----> connectons from node
        for (int i = 0; i < N; i++) {
            matrix[node - 1][i] += matrix[i][node - 1];
            matrix[i][node - 1] = 0;
        }
        ArrayList<String> VList = new ArrayList<String>();
        ArrayList<String> HList = new ArrayList<String>();
        for (int i = 0; i < N; i++) {
            VList.add(String.valueOf(i));
            HList.add(String.valueOf(i));
        }
        HList.remove(String.valueOf(node - 1));

        while ((HList.size() == 1 && VList.size() == 1) == false) {
            matrix[node - 1][node - 1] = 0;                         //remove self loops
            int mergeNode = getRandomNode(node, HList);
            for (int i = 0; i < N; i++) {
                matrix[node - 1][i] += matrix[i][mergeNode - 1];    //replace connections towards mergeNode to node
                matrix[node - 1][i] += matrix[mergeNode - 1][i];    //replace connections from mergeNode to node
                matrix[i][mergeNode - 1] = 0;
                matrix[mergeNode - 1][i] = 0;
            }
            int size = HList.size();
            HList.remove(String.valueOf(mergeNode - 1));                //remove mergeNode
            VList.remove(String.valueOf(mergeNode - 1));                //remove mergeNode
            int count = 0;
            while (size == HList.size()) {
                HList.remove(String.valueOf(mergeNode - 1));                //remove mergeNode
                if (count == 5) {
                    System.err.println("object not deleted tried 5 times " + mergeNode);
                    System.err.println("object " + mergeNode + " exists : " + HList.contains(String.valueOf(mergeNode - 1)));
                    System.err.println("size of list : " + size + " : " + HList.size());
                    throw new NullPointerException();
                }
                count++;
            }
        }
        minCut = matrix[Integer.parseInt(VList.get(0)) - 1][Integer.parseInt(HList.get(0)) - 1];
        return minCut;
    }

    public static int getRandomNode(int node, ArrayList<String> HList) {
        int randomNode = 0;
        ArrayList<String> list = new ArrayList<String>();
        for (String e : HList) {
            int n = Integer.parseInt(e);
            if(matrix[node-1][n-1]!=0){
                list.add(e);
            }
        }
        if (list.size() == 1) {
            return (Integer.parseInt(list.get(0)));
        }
        if (list.size() == 0) {
            System.err.println("list size is zero");
            for (String string : HList) {
                System.out.print(string+",");
            }
        }
        while (randomNode == 0) {
            int randomIndex = (int) (Math.random() * (list.size() - 1));
            randomNode = Integer.parseInt(list.get(randomIndex));
        }
        System.out.println("returning node " + randomNode);
        return randomNode;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        initialize();
        for (int i = 0; i < 1/*
                 * N * N * Math.log(N)
                 */; i++) {
            // System.out.print("running..." + i);
            int node = 1 + (int) (Math.random() * (N - 1));
            node = 1;
            System.out.println("  with node " + node);
            matrix = matrixOrig.clone();
            int minCut = getMinimummCut(node);
            if (minCut < minimummCut) {
                minimummCut = minCut;
            }
        }
        System.out.println("minimumm cut = " + minimummCut);
    }
}
