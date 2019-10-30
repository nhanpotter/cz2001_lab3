import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Graph {
    private final int LOOP = 100;
    public ArrayList<ArrayList<String>> nodeList;
    private int size;
    // private boolean connected; // true if you want to make sure the graph is connected

    public Graph(int s) {
        this.size = s;
        this.nodeList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < size; i++) {
            ArrayList<String> al = new ArrayList<String>();
            this.nodeList.add(al);
        }
    }

    public boolean addEdge(int source, int dest) {
        if (source >= this.size || dest >= this.size) {
            System.out.println("Please choose a smaller source or dest");
            return false;
        } else if (containsEdge(source, dest)) {
            System.out.println("Edge already exists");
            return false;
        } else {
            String destStr = Integer.toString(dest);
            String sourceStr = Integer.toString(source);
            this.nodeList.get(source).add(destStr);
            this.nodeList.get(dest).add(sourceStr);
            return true;
        }
    }

    public boolean containsEdge(int source, int dest) {
        String destStr = Integer.toString(dest);
        return this.nodeList.get(source).contains(destStr);
    }

    public void addConnectedEdge(int step) {
        for (int i = 0; i < size; i++) {
            // here step = 1
            // you can change to other step, as long as it can still make sure
            // gcd (step, size) = 1
            int d = (i + step) % size;
            // source = i, dest = d
            addEdge(i, d);
        }
    }

    public void generateRandomEdges(int num_of_edges) {
		if (num_of_edges > size * (size-1)/2) return;

        for (int i = 0; i < num_of_edges; i++) {
            int s = 0;
            int d = 0;
            boolean test = false;
            while (!test) {
                s = (int) (Math.random() * size);
                d = (int) (Math.random() * size);
                if (s != d) test = addEdge(s,d);
            }
        }
    }

    public void printAdjacencyList() {
        for (int i = 0; i < size; i++) {
			ArrayList<String> arr = this.nodeList.get(i);
            if (arr == null)
                System.out.println("Point " + i + ": Empty");
            else
				Collections.sort(arr);
                System.out.println(Integer.toString(i) + ": " + arr);
        }
    }

    public long findShortestPath(int source, int dest) {
        // remember to cast String to dest
        // String destStr = Integer.toString(dest)
        // as we store string values in the adjacency matrix
        long runTime = 0;
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < LOOP; i++) {
            result = BFS.search(this.nodeList, source, dest);
            runTime += BFS.runTime;
        }
        System.out.println(Arrays.toString(result.toArray()));
        return runTime / LOOP;
    }

}
