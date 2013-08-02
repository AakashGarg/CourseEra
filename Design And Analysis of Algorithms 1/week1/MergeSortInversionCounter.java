/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms1.week1;

import java.io.*;

/**
 *
 * @author Aakash
 */
public class MergeSortInversionCounter {

    static int[] array = new int[100000];
    static long noOfInversions = 0;

    public static void initializeArray() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\Week 1\\IntegerArray.txt"));
        // FileReader fileReader = new FileReader(new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\ajay garg\\IntegerArray.txt"));
        BufferedReader br = new BufferedReader(fileReader);
        int k = 0;
        String line = null;
        while ((line = br.readLine()) != null) {
            array[k++] = Integer.parseInt(line);
        }
    }

    public static int[] count(int[] A, int length) {
        if (length == 1) {
            return A;
        } else {
            int[] B = subArray(A, 0, length / 2);
            int[] C = subArray(A, length / 2, length);
            B = count(B, B.length);
            C = count(C, C.length);
            return countSplitInv(B, C);
        }
    }

    public static int[] countSplitInv(int[] B, int[] C) {
        int[] D = new int[B.length + C.length];
        int i = 0, j = 0;

        for (int k = 0; k < D.length; k++) {

            if ((i == (B.length)) || (j == (C.length))) {
                if (i < B.length) {
                    for (; i < B.length; k++, i++) {
                        D[k] = B[i];
                    }
                } else {
                    for (; j < C.length; k++, j++) {
                        D[k] = C[j];
                    }
                }
                break;
            } else if (B[i] < C[j]) {
                D[k] = B[i];
                i++;
            } else {
                D[k] = C[j];
                j++;
                noOfInversions += (B.length - i);

            }
        }
        return D;
    }

    private static int[] subArray(int[] A, int start, int length) {
        int[] arr = new int[length - start];
        for (int i = start; i < length; i++) {
            arr[i - start] = A[i];
        }
        return arr;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        initializeArray();
        array = count(array, array.length);
        System.out.println(noOfInversions);

        noOfInversions = 0;
        int[] E = {6, 5, 4, 3, 2, 1};
        E = count(E, E.length);
        for (int i = 0; i < E.length; i++) {
            System.out.print(E[i] + " ");
        }
        System.out.println(",noOfInversions : " + noOfInversions);

        System.out.println("verification of sorting");
        for (int i = 1; i <= array.length; i++) {
            if (array[i - 1] != i) {
                System.err.println(array[i - 1]);
                break;

            }
        }


    }
}
