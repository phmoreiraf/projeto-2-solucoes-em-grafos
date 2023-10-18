package utils;

import entities.Edge;
import entities.Graph;
import entities.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateGraphUtil {
    /**
     * Cria um novo grafo a partir do caminho fornecido
     *
     * @param fileName nome do arquivo
     * @return novo grafo com os vertices e arestas do arquivo fornecido
     */
    public static Graph buildGraphFromFile(String fileName) {
        List<Vertex> vertices;
        List<Edge> edges = new ArrayList<>();
        Map<String, Vertex> vertexMap = new HashMap<>();

        // Captura informacoes do arquivo graph01.txt, e transfere para vertices e edges
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(": ");

                if (parts.length >= 2) {

                    String cityName = parts[0];
                    Vertex origin = vertexMap.computeIfAbsent(cityName, Vertex::new);
                    String[] connections = parts[1].split(", ");

                    for (String connection : connections) {

                        String[] connectionParts = connection.split(" \\(");

                        if (connectionParts.length == 2) {

                            String destinationCity = connectionParts[0];
                            Vertex destiny = vertexMap.computeIfAbsent(destinationCity, Vertex::new);
                            int distance = Integer.parseInt(connectionParts[1].replaceAll("[^\\d]", ""));

                            edges.add(new Edge(origin, destiny, distance));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        vertices = new ArrayList<>(vertexMap.values());
        return new Graph(vertices, edges);
    }

}
