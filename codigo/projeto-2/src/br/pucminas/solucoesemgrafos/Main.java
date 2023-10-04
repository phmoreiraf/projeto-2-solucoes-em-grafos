package br.pucminas.solucoesemgrafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.pucminas.solucoesemgrafos.entities.Edge;
import br.pucminas.solucoesemgrafos.entities.Graph;
import br.pucminas.solucoesemgrafos.entities.Vertex;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "graph.txt";
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        Map<String, Vertex> vertexMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length >= 2) {
                    String cityName = parts[0];
                    Vertex origin = vertexMap.computeIfAbsent(cityName, Vertex::new);
                    vertices.add(origin);

                    String[] connections = parts[1].split(", ");
                    for (String connection : connections) {
                        String[] connectionParts = connection.split(" \\(");
                        if (connectionParts.length == 2) {
                            String destinationCity = connectionParts[0];
                            int distance = Integer.parseInt(connectionParts[1].replaceAll("[^\\d]", ""));
                            Vertex destiny = vertexMap.computeIfAbsent(destinationCity, Vertex::new);
                            edges.add(new Edge(origin, destiny, distance));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graph graph = new Graph(vertices, edges);
        List<Vertex> vertexList = graph.getUnreachableVertices(vertices.get(10));

        for (Vertex vertex : vertexList) {
            System.out.println(vertex.getCityName());
        }
        
        vertexList = graph.getReachableVertices(vertices.get(0));

        System.out.println("\nRecomendacao:");
        for (Vertex vertex : vertexList) {
            System.out.println(vertex.getCityName());
        }
        
        List<String> recommendations = graph.visitAllRoadsAndCities(vertices.get(0));

		for (String recommendation : recommendations) {
		    System.out.println(recommendation);
		}
        
    }
}
