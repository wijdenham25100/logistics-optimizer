package logistics;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Knoten hinzufügen (Lager / Depots)
        String[] cities = {"Berlin", "Hamburg", "Dortmund", "Frankfurt", "München", "Leipzig"};
        for (String city : cities) graph.addNode(city);

        // Kanten hinzufügen (Verbindungen in km)
        graph.addEdge("Berlin",    "Hamburg",   289);
        graph.addEdge("Berlin",    "Leipzig",   191);
        graph.addEdge("Hamburg",   "Dortmund",  235);
        graph.addEdge("Dortmund",  "Frankfurt", 236);
        graph.addEdge("Leipzig",   "Frankfurt", 395);
        graph.addEdge("Leipzig",   "München",   510);
        graph.addEdge("Frankfurt", "München",   393);

        // Startknoten aus Args oder Standard
        String start = args.length > 0 ? args[0] : "Dortmund";

        if (!Arrays.asList(cities).contains(start)) {
            System.out.println("Unbekannter Startknoten: " + start);
            System.out.println("Verfügbar: " + Arrays.toString(cities));
            return;
        }

        System.out.println("=== Logistics Optimizer ===");
        System.out.println("Startdepot: " + start);
        System.out.println("---------------------------");

        Map<String, Integer> result = Dijkstra.shortestPaths(graph, start);

        List<String> sorted = new ArrayList<>(result.keySet());
        sorted.sort(Comparator.comparingInt(result::get));

        for (String city : sorted) {
            int dist = result.get(city);
            String bar = "█".repeat(dist / 30);
            System.out.printf("%-12s %4d km  %s%n", city, dist, bar);
        }
    }
}