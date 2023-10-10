import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Edge;
import entities.Graph;
import entities.Route;
import entities.Vertex;

public class Main {
    public static void main(String[] args) {

        String fileName = "graph.txt";
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        Map<String, Vertex> vertexMap = new HashMap<>();

        /*I - Preenche informacoes do grafo a partir do arquivo graph.txt*/
        /*II - Cria e preenche vertices*/
        /*III - Cria  e preenche arestas*/
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
        List<Route> allRoutes = graph.visitAllRoadsAndCities(vertices.get(0));

        for (Route route : allRoutes) {
            System.out.println(route);
        }
    }
}
