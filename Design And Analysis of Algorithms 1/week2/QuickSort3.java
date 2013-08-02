/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms1.week2;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Aakash
 */
public class QuickSort3 {   //chosing median element as pivot

    public static int[] A;
    public static long comparisons = 0;

    public static void quickSort(int l, int r) {
        if ((r - l + 1) == 1) {
            return;
        } else {
            int pivotIndex = partition(l, r);
            if ((pivotIndex - 1) >= l) {
                quickSort(l, pivotIndex - 1);
            }
            if ((pivotIndex + 1) <= r) {
                quickSort(pivotIndex + 1, r);
            }
        }
    }

    private static int partition(int l, int r) {
        int pivot = choosePivot(l, r);
        comparisons += r - l;
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (A[j] < pivot) {
                //swap A[j] and A[i]
                int t = A[j];
                A[j] = A[i];
                A[i] = t;
                i++;
            }
        }
        //swap A[l] and A[i-1]
        int t = A[l];
        A[l] = A[i - 1];
        A[i - 1] = t;

        return i - 1;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        A = new int[10000];
        initializeArray();
        quickSort(0, A.length - 1);
        System.out.println("no of comparisons = " + comparisons);
        System.out.println(validate(A));

    }

    private static int choosePivot(int l, int r) {
        int index = 0;
        int[] arr = {A[l], A[l + ((r - l) / 2)], A[r]};
        Arrays.sort(arr);

        if (arr[1] == A[l]) {
            index = l;
        } else if (arr[1] == A[(l + ((r - l) / 2))]) {
            index = (l + ((r - l) / 2));
        } else {
            index = r;
        }
        int t = A[l];
        A[l] = A[index];
        A[index] = t;
        return A[l];
    }

    private static void initializeArray() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(
                new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\aakash garg\\week 2\\QuickSort.txt"));
        BufferedReader br = new BufferedReader(fileReader);
        int k = 0;
        String line = null;
        while ((line = br.readLine()) != null) {
            A[k++] = Integer.parseInt(line);
        }
    }

    private static boolean validate(int[] array) {
        System.out.print("verification of sorting : ");
        for (int i = 1; i <= array.length; i++) {
            if (array[i - 1] != i) {
                System.err.println("error at " + array[i - 1]);
                return false;
            }
        }
        return true;
    }
}
