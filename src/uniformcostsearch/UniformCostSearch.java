/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniformcostsearch;

import java.util.Random;

/**
 *
 * @author sengkhunlim
 */
public class UniformCostSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int size = 5;
        int[][] graph = initialGraph(size);
        printGraph(graph);
        
        char start = 'E';
        char goal = 'C';

    }

    public static int[][] initialGraph(int size) {

        int[][] graph = new int[size][size];

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                if (i == j) {
                    graph[i][j] = -1;
                } else {
                    graph[i][j] = new Random().nextInt(5) - 1;    // Random value from -1 to 4
                }
            }

        }

        return graph;

    }

    public static void printGraph(int[][] g) {

        System.out.print("   ");

        for (int i = 0; i < g[0].length; i++) {
            System.out.printf("%3c", i+65);
        }

        System.out.println();

        for (int i = 0; i < g[0].length + 1; i++) {

            if (i == 0) {
                System.out.print("   ");
            } else {
                System.out.print("---");
            }
        }

        System.out.println();

        for (int i = 0; i < g.length; i++) {

            System.out.printf("%c |", i+65 );

            for (int j = 0; j < g[0].length; j++) {
                System.out.printf("%3d", g[i][j]);
            }

            System.out.println();

        }

    }

}
