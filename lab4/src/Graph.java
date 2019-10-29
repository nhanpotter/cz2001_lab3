import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class Graph {
	private final int LOOP = 1;
	public ArrayList <ArrayList <String> > nodeList;
	private int size;
	// private boolean connected; // true if you want to make sure the graph is connected
	
	
	public Graph (int s) {
		this.size = s;
		this.nodeList = new ArrayList <ArrayList <String> >();
		for (int i = 0; i < size; i++) {
			ArrayList<String> al = new ArrayList <String> ();
			this.nodeList.add(al);
		}
	}
	
	public void addEdge(int source, int dest) {
		if (source >= this.size || dest >= this.size) {
			System.out.println("Please choose a smaller source or dest");
		} else if (containEdge(source, dest)) {
			System.out.println("Edge already exists");
		} else {
			String destStr = Integer.toString(dest);
			String sourceStr = Integer.toString(source);
			this.nodeList.get(source).add(destStr);
			this.nodeList.get(dest).add(sourceStr);
		}
	}
	
	public boolean containEdge(int source, int dest) {
		String destStr = Integer.toString(dest);
		return this.nodeList.get(source).contains(destStr);
	}

	public void addConnectedEdge(int step) {
		for (int i=0; i<size; i++) {
			// here step = 1
			// you can change to other step, as long as it can still make sure
			// gcd (step, size) = 1
			int d = (i+step) % size;
			// source = i, dest = d
			addEdge(i, d);
		}
	}
	
	public void generateRandomEdges() {
		// number of edges is now n*lg(n)
		// you can choose whatever appropriate
		int num_of_edges = (int) Math.floor(size * Math.log(size));
		for (int i=0; i<num_of_edges; i++) {
			int s = (int) (Math.random() * size);
			int d = (int) (Math.random() * size);
			if (s == d) {
				d = (s+1)%size;
			}
			addEdge(s,d);
		}
	}
	
	public void printAdjacencyList() {
		for (int i = 0; i < size; i++) {
			if (this.nodeList.get(i) == null)
				System.out.println("Point " + i + ": Empty");
			else
				System.out.println(this.nodeList.get(i));
		}
	}
	
	public long findShortestPath(int source, int dest) {
		// remember to cast String to dest
		// String destStr = Integer.toString(dest)
		// as we store string values in the adjacency matrix 
		long runTime=0;
		ArrayList<String> result = new ArrayList<>();
		for (int i=0; i<LOOP; i++) {
			result = BFS.search(this.nodeList, source, dest);
			runTime += BFS.runTime;
		}
		System.out.println(Arrays.toString(result.toArray()));
		return runTime;

	}
		
}
