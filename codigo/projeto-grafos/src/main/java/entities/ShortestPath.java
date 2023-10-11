package entities;

public class ShortestPath {
    private Vertex sourceVertex;
    private Vertex destinationVertex;
    private int distance;

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
