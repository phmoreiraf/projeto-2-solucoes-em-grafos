package entities;

public class Edge {

    private Vertex origin;
    private Vertex destiny;
    private int distance;

    /**
     * @param origin   vertice de origem
     * @param destiny  vertice de destino
     * @param distance distancia entre vertices (peso)
     */
    public Edge(Vertex origin, Vertex destiny, int distance) {
        this.origin = origin;
        this.destiny = destiny;
        this.distance = distance;
    }

    public Edge() {
        this.origin = this.destiny = null;
        this.distance = 0;
    }

    public Vertex getOrigin() {
        return origin;
    }

    /**
     * @param origin vertice de origem
     */
    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    public Vertex getDestiny() {
        return destiny;
    }

    /**
     * @param destiny vertice de destino
     */
    public void setDestiny(Vertex destiny) {
        this.destiny = destiny;
    }

    public int getDistance() {
        return distance;
    }

    /**
     * @param distance distancia entre vertices
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
}
