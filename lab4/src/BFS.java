import java.util.*;

public class BFS
{
    public static long runTime=0;

    public static void start() {
        runTime = System.nanoTime();
    }

    public static void end() {
        long endTime = System.nanoTime();
        runTime = endTime - runTime;
    }

    public static ArrayList<String> search(ArrayList <ArrayList <String> > nodeList, int source, int dest) {
        start();
        ArrayList<String> result = BFSearch(nodeList, source, dest);
        end();
        return result;
    }

    public static ArrayList<String> BFSearch(ArrayList <ArrayList <String> > nodeList, int source, int dest) {
        boolean found = false;
        ArrayList<String> mark = new ArrayList<>();
        HashMap<Integer, String> predecessor = new HashMap<Integer, String>();
        Queue<Integer> queue = new LinkedList<>();
        predecessor.put(source, Integer.toString(source));
        mark.add(Integer.toString(source));
        queue.add(source);

        while (!queue.isEmpty()) {
            int visit = queue.poll();
            for (String neighborStr: nodeList.get(visit)) {
                if (!mark.contains(neighborStr)) {
                    mark.add(neighborStr);
                    predecessor.put(Integer.valueOf(neighborStr), Integer.toString(visit));
                    queue.add(Integer.valueOf(neighborStr));
                }
                if (neighborStr == Integer.toString(dest)) {
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }

        ArrayList<String> result = new ArrayList<>();
        String visitStr = Integer.toString(dest);
        result.add(0, visitStr);

        while (!visitStr.equals(Integer.toString(source))) {
            visitStr = predecessor.get(Integer.valueOf(visitStr));
            result.add(0, visitStr);
        }
        return result;
    }
}