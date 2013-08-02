/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week1;

import java.io.*;

/**
 *
 * @author Aakash
 */
public class CreateOctaveDataSet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        File inputFile = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 1\\edges.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        
        PrintWriter fw = new PrintWriter(new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 1\\edgesdata.txt"));
        String[] output  = br.readLine().trim().split(" ");
        int N = Integer.parseInt(output[0]);
        int E = Integer.parseInt(output[1]);
        for (int i = 0; i < E; i++) {
            output = br.readLine().trim().split(" ");
            String line = "";
            line += Integer.parseInt(output[0])+"-";
            line += Integer.parseInt(output[1])+" ";
            line += Float.parseFloat(output[2]);
            fw.println(line);
        }
    }
}
