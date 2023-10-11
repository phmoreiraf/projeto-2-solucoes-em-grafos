package entities;

public class ShortestPath {

    private final Vertex sourceVertex;
    private final Vertex destinationVertex;
    private final int distance;

    /**
     * @param sourceVertex      vertice de referencia
     * @param destinationVertex vertice de destino
     * @param distance          distancia da aresta
     */
    public ShortestPath(Vertex sourceVertex, Vertex destinationVertex, int distance) {
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
        this.distance = distance;
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    public int getDistance() {
        return distance;
    }
}
