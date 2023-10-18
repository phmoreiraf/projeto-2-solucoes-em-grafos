import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String fileName = "graph.txt";
        List<Vertex> vertices;
        List<Edge> edges = new ArrayList<>();
        Map<String, Vertex> vertexMap = new HashMap<>();

        /* Captura informacoes do arquivo graph.txt, e transfere para vertices e edges*/
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

        Graph graph = new Graph(vertices, edges);

        System.out.println("Selecione qual questão deseja verificar: (A, B, C, D / S para sair)");
        System.out.println("Letra A - Existe estrada de qualquer cidade para qualquer cidade?");
        System.out.println("Letra B - Cidades não alcançadas por uma determinada cidade: (Ex.: 0)");
        System.out.println("Letra C - Recomendação de visitas: (Ex.: 0)");
        System.out.println("Letra D - Menores distâncias possíveis a partir de determinada cidade: (Ex.: 0)");
        Character op = scanner.nextLine().toLowerCase().charAt(0);

        while (!op.equals('s')) {
            switch (op) {
                case 'a' -> {
                    System.out.println(graph.isConnected() ? "Sim" : "Não");
                }
                case 'b' -> {
                    int index = getCityIndex(vertices);
                    List<Vertex> unreachableTowns = graph.getUnreachableVertices(vertices.get(index));
                    unreachableTowns.forEach(vertex -> System.out.printf(vertex.getCityName() + "\n"));
                }
                case 'c' -> {
                    int index = getCityIndex(vertices);
                    List<Route> allRoutes = graph.visitAllRoadsAndCities(vertices.get(index));
                    allRoutes.forEach(System.out::println);
                }
                case 'd' -> {
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
            System.out.println("Selecione qual questão deseja verificar: (A, B, C, D / S para sair)");
            System.out.println("Letra A - Existe estrada de qualquer cidade para qualquer cidade?");
            System.out.println("Letra B - Cidades não alcançadas por uma determinada cidade: (Ex.: 0)");
            System.out.println("Letra C - Recomendação de visitas: (Ex.: 0)");
            System.out.println("Letra D - Menores distâncias possíveis a partir de determinada cidade: (Ex.: 0)");
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
