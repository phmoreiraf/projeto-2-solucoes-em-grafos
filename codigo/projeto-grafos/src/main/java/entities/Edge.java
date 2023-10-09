package entities;

public class Edge {

    private Vertex origin;
    private Vertex destiny;
    private int distance;

    public Edge(Vertex origin, Vertex destiny, int distance) {
        this.origin = origin;
        this.destiny = destiny;
        this.distance = distance;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    public Vertex getDestiny() {
        return destiny;
    }

    public void setDestiny(Vertex destiny) {
        this.destiny = destiny;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
