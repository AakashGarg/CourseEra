/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week2;

import java.io.*;
import mst.QuickFindUFModified;

/**
 *
 * @author Aakash
 */
public class HammingCodeMaxSpacing {

    private int N;
    private int codeLength;
    private String[][] hammingCode;

    /**
     * @param args the command line arguments
     */
    public void getData(boolean fromFile) throws FileNotFoundException, IOException {
        BufferedReader br = getReader(fromFile);
        String inputLine = br.readLine().trim();
        String[] line = inputLine.split(" ");
        N = Integer.parseInt(line[0]);
        codeLength = Integer.parseInt(line[1]);
        int k = 0;
        hammingCode = new String[N][codeLength];
        while ((inputLine = br.readLine()) != null) {
            hammingCode[k++] = inputLine.trim().split(" ");
        }
    }

    private static BufferedReader getReader(boolean flag) throws FileNotFoundException {
        BufferedReader br = null;
        if (flag == true) {
            File inputFile = new File("D:\\CourseEra\\Design and Analysis of Algorithms 2\\problem sets\\week 2\\clustering2.txt");
            br = new BufferedReader(new FileReader(inputFile));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    private boolean hammingCodeDiff(String[] a, String[] b, int maxLength) {
        int count = 0;
        for (int i = 0; count < maxLength && i < codeLength; i++) {
            if (!a[i].equals(b[i])) {
                count++;
            }
        }
        return count < maxLength;
    }

    private int getClusters(int maxLength) {
        QuickFindUFModified uf = new QuickFindUFModified(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean isValid = hammingCodeDiff(hammingCode[i], hammingCode[j], maxLength);
                if (isValid && (!uf.connected(i, j))) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }

    public static void main(String[] args) throws IOException {
        HammingCodeMaxSpacing mst = new HammingCodeMaxSpacing();
        mst.getData(true);
        int maxLength = 3;
        int clustersCount = mst.getClusters(maxLength);
        System.out.println("no of clusters: " + clustersCount);
    }
}
