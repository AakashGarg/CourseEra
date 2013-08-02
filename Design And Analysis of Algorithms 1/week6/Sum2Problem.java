/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms1.week6;

import java.io.*;
import java.util.HashSet;

/**
 *
 * @author Aakash
 */
public class Sum2Problem {

    public int count = 0;
    public HashSet<Integer> table = new HashSet<Integer>();

    public void initialize() throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\aakash garg\\week 6\\HashInt.txt"));
        BufferedReader br = new BufferedReader(reader);

        String line = null;
        while ((line = br.readLine()) != null) {
            int no = Integer.parseInt(line);
            table.add(no);
        }
    }

    public void solve(int t) {
        for (int x : table.toArray(new Integer[0])) {
            if (((t - x) != x) && table.contains(t - x)) {
                count++;
                break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Sum2Problem sol = new Sum2Problem();
        sol.initialize();
        for (int t = 2500; t <= 4000; t++) {
            sol.solve(t);
        }
        System.out.println(sol.count);
    }
}
