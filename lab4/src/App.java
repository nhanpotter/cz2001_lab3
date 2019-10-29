import java.util.Scanner;
import java.io.*;

public class App
{
    public static void main(String[] args) {
		int[] sizeInputList = {10};
		int[][] searchInputList = {{1,5}};
		long[][] store = new long[sizeInputList.length][2];
		for (int i=0; i< sizeInputList.length; i++) {
			Graph g = new Graph(sizeInputList[i]);
//			g.addConnectedEdge(3);
			g.generateRandomEdges(45);
			g.printAdjacencyList();
			store[i][0] = (long) sizeInputList[i];
			store[i][1] = g.findShortestPath(searchInputList[i][0], searchInputList[i][1]);
		}

		storeCSV("./lab4/data/file.csv", store);
	}
	
	private static void storeCSV(String filepath, long[][] store) {
        try {
            FileWriter writer = new FileWriter(filepath);

            for (long[] store_elem : store) {
                writer.append(String.join(", ", String.valueOf(store_elem[0]),
                                            String.valueOf(store_elem[1])));
                writer.append("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}