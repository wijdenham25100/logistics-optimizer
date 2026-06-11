package logistics;

import java.util.*;

public class Graph {
    private Map<String, List<int[]>> adjList = new HashMap<>();

    public void addNode(String name) {
        adjList.putIfAbsent(name, new ArrayList<>());
    }

    public void addEdge(String from, String to, int cost) {
        adjList.get(from).add(new int[]{indexOf(to), cost});
        adjList.get(to).add(new int[]{indexOf(from), cost});
    }

    public List<String> getNodes() {
        return new ArrayList<>(adjList.keySet());
    }

    public List<int[]> getNeighbors(String node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    private int indexOf(String name) {
        return getNodes().indexOf(name);
    }
}