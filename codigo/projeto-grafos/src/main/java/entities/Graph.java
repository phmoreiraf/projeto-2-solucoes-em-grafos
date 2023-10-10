package entities;

import java.util.*;

public class Graph {

    List<Vertex> vertices;
    List<Edge> edges;
    int[][] adjacencyMatrix;

    /**
     * @param vertices vertices do grafo
     * @param edges    arestas do grafo
     */
    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.adjacencyMatrix = new int[vertices.size()][vertices.size()];
        /*Cria matriz de adjacencia vazia*/
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
        /*Preenche matriz de adjacencia*/
        for (Edge edge : edges) {
            int originIndex = vertices.indexOf(edge.getOrigin());
            int destinyIndex = vertices.indexOf(edge.getDestiny());
            adjacencyMatrix[originIndex][destinyIndex] = 1;
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * @param vertices vertices do grafo
     */
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * @param edges arestas do grafo
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Imprime a matriz de adjacencia
     */
    public void printAdjacencyMatrix() {
        int n = vertices.size();

        System.out.println("Matriz de Adjacência:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Busca em largura - Questao (a)
     *
     * @return boolean indicando se grafo e conexo ou nao
     */
    public boolean isConnected() {
        List<Vertex> allVertices = new ArrayList<>(vertices);

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

    /**
     * Lista as cidades que nao sao alcancadas por uma cidade de referencia (sourceVertex) - Questao (b)
     *
     * @param sourceVertex vertice de referencia
     * @return lista de vertices que nao sao alcancados pelo vertice de referencia
     */
    public List<Vertex> getUnreachableVertices(Vertex sourceVertex) {

        int initialPosition = sourceVertex.getId() - 1;
        List<Vertex> unreachableVertices = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix[initialPosition].length; i++) {
            if (!vertices.get(i).equals(sourceVertex)) {
                if (adjacencyMatrix[initialPosition][i] == 0) {
                    unreachableVertices.add(vertices.get(i));
                }
            }
        }
        return unreachableVertices;
    }

    /**
     * Lista as cidades que sao alcancadas por uma cidade de referencia (sourceVertex)
     *
     * @param sourceVertex vertice de referencia
     * @return lista de vertices que sao alcancados pelo vertice de referencia
     */
    public List<Vertex> getReachableVertices(Vertex sourceVertex) {

        int initialPosition = sourceVertex.getId() - 1;
        List<Vertex> reachableVertices = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix[initialPosition].length; i++) {
            if (!vertices.get(i).equals(sourceVertex)) {
                if (adjacencyMatrix[initialPosition][i] == 1) {
                    reachableVertices.add(vertices.get(i));
                }
            }
        }
        return reachableVertices;
    }

    /**
     * Busca em profundidade
     *
     * @param initialPosition posicao inicial de busca
     * @param visited         marcacao em cada vertice para que a busca nao re-visite um vertice marcado
     * @param currentRoute    rota atual que está sendo explorada
     * @param allRoutes 	  lista de rotas recomendadas
     */
    private void DFS(int initialPosition, Set<Integer> visited, Route currentRoute, List<Route> allRoutes) {
        visited.add(initialPosition);
        currentRoute.addVertex(vertices.get(initialPosition));

        boolean hasUnvisitedNeighbor = false;
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacencyMatrix[initialPosition][i] == 1 && !visited.contains(i)) {
                hasUnvisitedNeighbor = true;
                DFS(i, new HashSet<>(visited), new Route(currentRoute), allRoutes);
            }
        }

        if (!hasUnvisitedNeighbor) {
            allRoutes.add(currentRoute);
        }
    }

    /**
     * Fornece recomendacoes de cidades para visitar, a partir de um vertice - Questao (c)
     *
     * @param sourceVertex vertice de referencia
     * @return lista de rotas recomendadas
     */
    public List<Route> visitAllRoadsAndCities(Vertex sourceVertex) {
    	List<Route> allRoutes = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int sourceVertexIndex = vertices.indexOf(sourceVertex);
        
        DFS(sourceVertexIndex, visited, new Route(), allRoutes);
        return allRoutes;
    }

}
