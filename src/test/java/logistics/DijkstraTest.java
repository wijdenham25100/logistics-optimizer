package logistics;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    /** Hilfsmethode: kleiner Beispielgraph wie im Logistik-Szenario. */
    private Graph beispielGraph() {
        Graph g = new Graph();
        g.addNode("Lager");
        g.addNode("DepotA");
        g.addNode("DepotB");
        g.addEdge("Lager", "DepotA", 5);
        g.addEdge("Lager", "DepotB", 2);
        g.addEdge("DepotB", "DepotA", 1);
        return g;
    }

    @Test
    void distanzZumStartknotenIstNull() {
        Map<String, Integer> d = Dijkstra.shortestPaths(beispielGraph(), "Lager");
        assertEquals(0, d.get("Lager"));
    }

    @Test
    void findetKuerzestenWegUeberZwischenstation() {
        Map<String, Integer> d = Dijkstra.shortestPaths(beispielGraph(), "Lager");
        // Direkt Lager->DepotA = 5, über DepotB = 2 + 1 = 3
        assertEquals(3, d.get("DepotA"));
        assertEquals(2, d.get("DepotB"));
    }

    @Test
    void waehltGuenstigsteVonMehrerenRouten() {
        Graph g = new Graph();
        for (String n : new String[]{"A", "B", "C", "D"}) g.addNode(n);
        g.addEdge("A", "B", 10);
        g.addEdge("A", "C", 3);
        g.addEdge("C", "D", 3);
        g.addEdge("D", "B", 3);   // A->C->D->B = 9 statt direkt 10
        assertEquals(9, Dijkstra.shortestPaths(g, "A").get("B"));
    }

    @Test
    void laengereKetteWirdKorrektAufsummiert() {
        Graph g = new Graph();
        for (String n : new String[]{"A", "B", "C", "D", "E"}) g.addNode(n);
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "E", 1);
        assertEquals(4, Dijkstra.shortestPaths(g, "A").get("E"));
    }

    @Test
    void kanteMitGewichtNullWirdKorrektBehandelt() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");
        g.addEdge("A", "B", 0);
        assertEquals(0, Dijkstra.shortestPaths(g, "A").get("B"));
    }

    @Test
    void nichtErreichbarerKnotenHatKeineEndlicheDistanz() {
        Graph g = new Graph();
        g.addNode("Lager");
        g.addNode("Insel");   // keine Kante dorthin
        Integer dist = Dijkstra.shortestPaths(g, "Lager").get("Insel");
        // Je nach Implementierung: gar nicht in der Map ODER "unendlich"
        assertTrue(dist == null || dist == Integer.MAX_VALUE,
            "Nicht erreichbarer Knoten darf keine endliche Distanz haben, war: " + dist);
    }
}
