import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class App
{
    public static void main(String[] args) {
		int[] sizeInputList = {30};
		//int[][] searchInputList = {{1,5}, {3,6}};
		long[][] store = new long[sizeInputList.length][2];
		
		//An example of 10 cities
		HashMap<Integer, String> cities = new HashMap<Integer, String>();
		//String[] cities_arr = {"Venice","Seville", "New York", "Lhasa", "Rio de Janeiro", "London", "Marrakech", "Jakarta", "Rome", "Varanasi"};
		String[] cities_arr = {
				"Venice",
				"Seville", 
				"New York", 
				"Lhasa", 
				"Rio de Janeiro", 
				"London", 
				"Marrakech", 
				"Jakarta", 
				"Rome", 
				"Varanasi",
				"Kuala Lumpur",
				"Beijing",
				"Singapore",
				"Surabaya",
				"Xi-an",
				"Siem Reap",
				"Seoul",
				"Vientiane",
				"Kong",
				"Bangkok",
				"Reykjavik",
				"Fuzhou",
				"Paris",
				"Kyoto",
				"Los Angeles",
				"Dubai",
				"Pyongyang",
				"Lisbon",
				"Madrid",
				"Taipei"};
		for (int j=0; j<cities_arr.length; j++) {
			cities.put(j, cities_arr[j]);
		}

		for (int i=0; i< sizeInputList.length; i++) {
			Graph g = new Graph(sizeInputList[i]);
			g.generateRandomEdges();
			//g.printAdjacencyList();
			g.printAdjacencyList(cities);
			store[i][0] = (long) sizeInputList[i];
			//store[i][1] = g.findShortestPath(0, 1)
			store[i][1] = g.findShortestPath(9, 1, cities);
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