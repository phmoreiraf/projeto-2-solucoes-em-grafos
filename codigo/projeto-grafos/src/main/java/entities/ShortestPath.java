package entities;

public record ShortestPath(Vertex sourceVertex, Vertex destinationVertex, int distance) {

    /**
     * @param sourceVertex      vertice de referencia
     * @param destinationVertex vertice de destino
     * @param distance          distancia da aresta
     */
    public ShortestPath {
    }
}
