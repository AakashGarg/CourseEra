/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms2.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 *
 * @author Aakash
 */
public class Test {

    public static void main(String[] args) throws IOException {
        HashSet<Integer> set = new HashSet<Integer>();
        String input = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < (int) Math.pow(2, 6); i++) {
            int no = Integer.parseInt(br.readLine(), 2);
            if (set.contains(no)) {
                System.err.println("no already exists");
            } else {
                System.out.println("added " + no);
            }
        }
    }
}
