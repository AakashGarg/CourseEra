/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms1.week6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Aakash
 */
public class MedianMaintainance {

    public ArrayList<Integer> list = new ArrayList<Integer>();
    public ArrayList<Integer> inputList = new ArrayList<Integer>();
    long medainSum = 0;

    public void initialize() throws IOException {
        FileReader fr = new FileReader(new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\problem sets\\aakash garg\\week 6\\Median.txt"));
        BufferedReader br = new BufferedReader(fr);

        int k = 1;
        String line = null;
        while ((line = br.readLine()) != null) {
            int no = Integer.parseInt(line);
            inputList.add(no);
        }
    }

    public void solve() {
        for (int k = 1; k <= inputList.size(); k++) {
            int e = inputList.get(k - 1);
            list.add(e);
            Collections.sort(list);
            int meadianIndex = (k % 2 != 0 ? ((k + 1) / 2) : k / 2) - 1;
            medainSum += list.get(meadianIndex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        MedianMaintainance sol = new MedianMaintainance();
        sol.initialize();
        sol.solve();
        System.out.print(sol.medainSum+"----->");
        System.out.println((sol.medainSum % 10000));
    }
}
