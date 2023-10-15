import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
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

        System.out.println("Selecione qual questão deseja verificar: (A, B, C, D / S para sair) ");
        Character op = scanner.nextLine().toLowerCase().charAt(0);

        while (!op.equals('s')) {
            switch (op) {
                case 'a' -> {
                    System.out.println("\nLetra A - Existe estrada de qualquer cidade para qualquer cidade?");
                    System.out.println(graph.isConnected() ? "Sim" : "Não");
                }
                case 'b' -> {
                    System.out.println("\nLetra B - Cidades não alcançadas por uma determinada cidade: (Ex.: 0)");
                    int index = getCityIndex(vertices);
                    List<Vertex> unreachableTowns = graph.getUnreachableVertices(vertices.get(index));
                    unreachableTowns.forEach(vertex -> System.out.printf(vertex.getCityName() + "\n"));
                }
                case 'c' -> {
                    System.out.println("\nLetra C - Recomendação de visitas: (Ex.: 0)");
                    int index = getCityIndex(vertices);
                    List<Route> allRoutes = graph.visitAllRoadsAndCities(vertices.get(index));
                    allRoutes.forEach(System.out::println);
                }
                case 'd' -> {
                    System.out.println("\nLetra D - Menores distâncias possíveis a partir de determinada cidade: (Ex.: 0)");
                    int index = getCityIndex(vertices);
                    List<ShortestPath> shortestPathList = graph.shortestPathsFromSource(vertices.get(index));
                    shortestPathList.forEach(vertex -> {
                        System.out.println();
                        System.out.println("Origem -> " + vertex.getSourceVertex().getCityName());
                        System.out.println("Destino -> " + vertex.getDestinationVertex().getCityName());
                        System.out.println("Distância -> " + vertex.getDistance());
                    });
                }
                default -> throw new Exception("Opcao invalida");
            }
            System.out.println("\nSelecione qual questão deseja verificar: ('A', 'B', 'C', 'D' / 'S' para sair) ");
            op = scanner.nextLine().toLowerCase().charAt(0);
        }
    }

    private static int getCityIndex(List<Vertex> vertices) {
        Scanner scanner = new Scanner(System.in);
        final int[] i = {0};
        vertices.forEach(vertex -> {
            System.out.println(i[0] + ". " + vertex.getCityName());
            i[0]++;
        });
        return scanner.nextInt();
    }
}
