/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms1.week3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Aakash
 */
public class MinimummCut {

    public static HashMap<Integer, ArrayList<Integer>> referenceVerticesMap = new HashMap<Integer, ArrayList<Integer>>(200);

    public static void initializeMap() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(
                new File("D:\\CourseEra\\Design and Analysis of Algorithms 1\\Week 1\\IntegerArray.txt"));
        BufferedReader br = new BufferedReader(fileReader);
        String line = null;

        while ((line = br.readLine()) != null) {
            String[] tok = (removeUselessSpaces(line).trim()).split(" ");
            int vertex = Integer.parseInt(tok[0]);
            ArrayList list = new ArrayList(tok.length - 2);
            for (int i = 1; i < tok.length; i++) {
                list.add(Integer.parseInt(tok[i]));
            }
            referenceVerticesMap.put(vertex, list);
        }
    }

    public int countMinimummCut() {
        int minCut = 0;
        HashMap<Integer, ArrayList<Integer>> verticesMap = new HashMap<Integer, ArrayList<Integer>>();
        verticesMap.putAll(referenceVerticesMap);
        while (verticesMap.size()>1) {            
            
        }
        
        return minCut;
    }

    public static String removeUselessSpaces(String line) {
        char[] tok = line.toCharArray();
        char[] newTok = new char[(line.replaceAll(" ", "").length()) + 3];
        for (int i = 0, k = 0; i < line.length(); i++) {
            if (!((tok[i] == ' ') && (tok[i + 1] == ' '))) {
                newTok[k++] = tok[i];
            }
        }
        return (String.valueOf(newTok));
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        initializeMap();
    }
}
