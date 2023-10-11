package entities;

public class ShortestPath {

    private Vertex sourceVertex;
    private Vertex destinationVertex;
    private int distance;

    // Construtor para criar o objeto ShortestPath.
    public ShortestPath(Vertex sourceVertex, Vertex destinationVertex, int distance) {

        // Inicializa as variáveis com os valores definidos.
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
        this.distance = distance;
    }

    // Método para retornar o vértice de origem do menor caminho.
    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    // Método para retornar o vértice de destino do menor caminho.
    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    // Método para retornar distância do menor caminho.
    public int getDistance() {
        return distance;
    }
}
