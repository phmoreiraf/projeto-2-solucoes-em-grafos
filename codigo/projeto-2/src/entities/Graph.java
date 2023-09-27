package entities;

import java.util.*;

public class Graph {

    List<Vertex> vertices;
    List<Edge> edges;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isConnected() {
        List<Vertex> allVertices = new ArrayList<>(vertices);

        // Verificar a conectividade a partir de cada vértice
        for (int i = 0; i < allVertices.size(); i++) {

            Set<Vertex> visited = new HashSet<>();
            Queue<Vertex> queue = new LinkedList<>();
            Vertex startVertex = allVertices.get(i);

            queue.offer(startVertex);
            visited.add(startVertex);

            while (!queue.isEmpty()) {
                Vertex currentVertex = queue.poll();

                for (Edge edge : edges) {
                    if (edge.getOrigin().equals(currentVertex)) {
                        Vertex neighbor = edge.getDestiny();

                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }
            }

            if (visited.size() != allVertices.size()) {
                return false;
            }
        }

        return true;
    }

    public List<Vertex> getUnreachableVertices(Vertex sourceVertex) {
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();

        // Inicie a busca em largura a partir do vértice de origem
        queue.offer(sourceVertex);
        visited.add(sourceVertex);

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();

            for (Edge edge : edges) {
                if (edge.getOrigin().equals(currentVertex) && !visited.contains(edge.getDestiny())) {
                    visited.add(edge.getDestiny());
                    queue.offer(edge.getDestiny());
                }
            }
        }

        // Todas as cidades visitadas agora estão em visited
        // As cidades não alcançadas estão na diferença entre todas as cidades e as visitadas
        List<Vertex> unreachableVertices = new ArrayList<>();
        for (Vertex vertex : vertices) {
            if (!visited.contains(vertex) && !vertex.equals(sourceVertex)) {
                unreachableVertices.add(vertex);
            }
        }
        return unreachableVertices;
    }

}
