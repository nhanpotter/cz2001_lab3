import java.util.Scanner;
import java.io.*;

public class App {
    public static void main(String[] args) {
        int[] sizes = new int[100];
        sizes[0] = 100;
        for (int i = 1; i < sizes.length; i++) {
            sizes[i] = 100 * (i+1);
        }
        int[] randEdges = {1000};
        int[][] nodes = {{1, 5}};
        long[][][] store = new long[sizes.length][randEdges.length][nodes.length];

        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < randEdges.length; j++) {
                for (int k = 0; k < nodes.length; k++) {
                    Graph g = new Graph(sizes[i]);
                    g.addConnectedEdge(1);
                    g.generateRandomEdges(randEdges[j]);
                    g.printAdjacencyList();
                    store[i][j][k] = g.findShortestPath(nodes[k][0], nodes[k][1]);
                }
            }
        }

        storeCSV("./lab4/data/sizes.csv", store, sizes, randEdges, nodes);
    }

    private static void storeCSV(String filepath, long[][][] store, int[] sizes, int[] numedges, int[][] nodes) {
        try {
            FileWriter writer = new FileWriter(filepath);
            writer.append("Sizes, Edges, Path: (A, B), CPU time\n");
            for (int i = 0; i < store.length; i++) {
                for (int j = 0; j < store[i].length; j++) {
                    for (int k = 0; k < store[i][j].length; k++) {
                        writer.append(Integer.toString(sizes[i]) + "," +
                                Integer.toString(  (numedges[j] + sizes[i])) + "," +
                                Integer.toString(nodes[k][0]) + "," + Integer.toString(nodes[k][1]) + "," +
								Long.toString(store[i][j][k]));
                        writer.append("\n");
                    }
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}