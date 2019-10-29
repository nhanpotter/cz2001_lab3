import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class App
{
    public static void main(String[] args) {
		int[] sizeInputList = {4};
		//int[][] searchInputList = {{1,5}, {3,6}};
		long[][] store = new long[sizeInputList.length][2];
		
		//An example of 10 cities
		HashMap<Integer, String> cities = new HashMap<Integer, String>();
		String[] cities_arr = {"Venice","Seville", "New York", "Lhasa", "Rio de Janeiro", "London", "Marrakech", "Jakarta", "Rome", "Varanasi"};
		for (int j=0; j<cities_arr.length; j++) {
			cities.put(j, cities_arr[j]);
		}

		for (int i=0; i< sizeInputList.length; i++) {
			Graph g = new Graph(sizeInputList[i]);
			g.addEdge(1, 2);
			g.addEdge(3, 2);
			g.addEdge(0, 2);
			g.printAdjacencyList();
			store[i][0] = (long) sizeInputList[i];
			store[i][1] = g.findShortestPath(3, 2);
		}
		System.out.println(store[0][1]);
		//storeCSV("data/file.csv", store);
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