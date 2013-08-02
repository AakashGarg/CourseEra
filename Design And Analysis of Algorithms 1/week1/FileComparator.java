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
public class FileComparator {

    static int count = 0;
    static File file1 = new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\ajay garg\\IntegerArray.txt");
    static File file2 = new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\ajay garg\\output.txt");

    public static boolean compareFiles() throws FileNotFoundException, IOException {
        FileReader fileReader1 = new FileReader(file1);
        BufferedReader br1 = new BufferedReader(fileReader1);
        FileReader fileReader2 = new FileReader(file2);
        BufferedReader br2 = new BufferedReader(fileReader2);

        String line = null;
        while ((line = br1.readLine()) != null) {
            count++;
            int a = Integer.parseInt(line);
            int b = Integer.parseInt(br2.readLine());
            if (a != b) {
                System.out.println(count);
                System.out.println(a);
                System.out.println(b);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println(compareFiles());
    }
}
