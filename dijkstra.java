package logistics;

import java.util.*;

public class Dijkstra {

    public static Map<String, Integer> shortestPaths(Graph graph, String start) {
        List<String> nodes = graph.getNodes();
        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (String node : nodes) dist.put(node, Integer.MAX_VALUE);
        dist.put(start, 0);
        pq.add(new int[]{nodes.indexOf(start), 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            String currNode = nodes.get(curr[0]);
            int currDist = curr[1];

            if (currDist > dist.get(currNode)) continue;

            for (int[] neighbor : graph.getNeighbors(currNode)) {
                String neighborNode = nodes.get(neighbor[0]);
                int newDist = currDist + neighbor[1];

                if (newDist < dist.get(neighborNode)) {
                    dist.put(neighborNode, newDist);
                    pq.add(new int[]{neighbor[0], newDist});
                }
            }
        }
        return dist;
    }
}