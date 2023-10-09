import entities.Edge;
import entities.Graph;
import entities.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {

    private Graph graph;

    @BeforeEach
    public void setUp() {
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        Vertex v1 = new Vertex("Belo Horizonte");
        Vertex v2 = new Vertex("Nova Era");
        Vertex v3 = new Vertex("Betim");

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        /*Grafo conexo, 3 vertices e 2 aresta*/
        edges.add(new Edge(v1, v2, 120));
        edges.add(new Edge(v2, v3, 200));
        edges.add(new Edge(v3, v1, 200));

        graph = new Graph(vertices, edges);
    }

    @Test
    public void testGetVertices() {
        assertEquals(3, graph.getVertices().size());
    }

    @Test
    public void testGetEdges() {
        assertEquals(3, graph.getEdges().size());
    }

    @Test
    public void testPrintAdjacencyMatrix() {
        graph.printAdjacencyMatrix();
    }

    @Test
    @DisplayName("Verify if graph is connected")
    public void testIsConnected() {
        assertEquals(true, graph.isConnected());
    }
}
