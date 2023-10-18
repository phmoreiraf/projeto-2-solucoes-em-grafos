import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    // Variaveis para o menu principal
    private static final String MENU_HEADER = "\n---";
    private static final String MENU_FOOTER = "---";
    private static final String MENU_OPTIONS =
            "Selecione qual questão deseja verificar: (A, B, C, D / S para sair)\n" +
                    "Letra A - Existe estrada de qualquer cidade para qualquer cidade?\n" +
                    "Letra B - Cidades não alcançadas por uma determinada cidade:\n" +
                    "Letra C - Recomendação de visitas: (Ex.: 0)\n" +
                    "Letra D - Menores distâncias possíveis a partir de determinada cidade:";

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        // Caminho do arquivo do grafo
        // Nos nossos testes, nao conseguimos processar o graph03.txt pois ele e extremamente pesado
        String fileName = "graphs/graph02.txt";
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
        Graph graph = new Graph(vertices, edges);

        // Menu principal
        String op;
        while (true) {
            System.out.println(MENU_HEADER);
            System.out.println(MENU_OPTIONS);
            System.out.println(MENU_FOOTER);
            System.out.print("*Opcao: ");
            op = scanner.nextLine().toLowerCase();

            if (op.equals("s"))
                break;

            handleOption(op, graph, vertices);
        }
    }

    /**
     * Apresenta uma lista contendo as cidades(vertices) do grafo
     *
     * @param vertices vertices do grafo
     * @return scanner para opcao de cidade escolhida
     */
    private static int getCityIndex(List<Vertex> vertices) {

        Scanner scanner = new Scanner(System.in);

        final int[] i = {0};
        vertices.forEach(vertex -> {
            System.out.println(i[0] + ". " + vertex.getCityName());
            i[0]++;
        });
        System.out.println("---");
        System.out.print("*Opcao: ");

        return scanner.nextInt();
    }

    /**
     * Trata as opcoes disponiveis para o usuario, cada opcao representa uma questao exigida pelo trabalho pratico
     *
     * @param op       opcao escolhida
     * @param graph    grafo
     * @param vertices vertices do grafo
     * @throws Exception lanca uma excecao caso a opcao escolhida seja invalida, ou seja, op != (a || b || c || d)
     */
    private static void handleOption(String op, Graph graph, List<Vertex> vertices) throws Exception {
        switch (op) {
            case "a" -> handleOptionA(graph);
            case "b" -> handleOptionB(graph, vertices);
            case "c" -> handleOptionC(graph, vertices);
            case "d" -> handleOptionD(graph, vertices);
            default -> throw new Exception("Opcao invalida");
        }
    }

    /**
     * Executa funcao relativa a letra A do trabalho pratico
     *
     * @param graph grafo
     */
    private static void handleOptionA(Graph graph) {
        System.out.println("\n---------------");
        System.out.println("Letra A - Existe estrada de qualquer cidade para qualquer cidade?");
        System.out.println(graph.isConnected() ? "Sim" : "Não");
    }

    /**
     * Executa funcao relativa a letra B do trabalho pratico
     *
     * @param graph    grafo
     * @param vertices vertices do grafo
     */
    private static void handleOptionB(Graph graph, List<Vertex> vertices) {

        System.out.println("\n---------------");
        System.out.println("Letra B - Cidades não alcançadas por uma determinada cidade:");
        System.out.println("Selecione a cidade:");

        int index = getCityIndex(vertices);
        List<Vertex> unreachableTowns = graph.getUnreachableVertices(vertices.get(index));

        if (unreachableTowns.isEmpty())
            System.out.println("\n" + vertices.get(index).getCityName() + " alcança todas as cidades!");
        else {
            System.out.println("\nCidades não alcançadas por " + vertices.get(index).getCityName() + ": ");

            final int[] i = {0};
            unreachableTowns.forEach(vertex -> {
                System.out.printf(i[0] + ". " + vertex.getCityName() + "\n");
                i[0]++;
            });
        }
    }

    /**
     * Executa funcao relativa a letra C do trabalho pratico
     *
     * @param graph    grafo
     * @param vertices vertices do grafo
     */
    private static void handleOptionC(Graph graph, List<Vertex> vertices) {

        System.out.println("\n---------------");
        System.out.println("Letra C - Recomendação de visitas:");
        System.out.println("Selecione a cidade:");

        int index = getCityIndex(vertices);
        List<Route> allRoutes = graph.visitAllRoadsAndCities(vertices.get(index));

        if (allRoutes.size() == 1)
            System.out.println("\n" + vertices.get(index).getCityName() + " não alcança nenhuma outra cidade!");
        else {
            System.out.println("\nRecomendações de visitas a partir de " + vertices.get(index).getCityName() + ": ");
            allRoutes.forEach(System.out::println);
        }
    }

    /**
     * Executa funcao relativa a letra D do trabalho pratico
     *
     * @param graph    grafo
     * @param vertices vertices do grafo
     */
    private static void handleOptionD(Graph graph, List<Vertex> vertices) {

        System.out.println("\n---------------");
        System.out.println("Letra D - Menores distâncias possíveis a partir de determinada cidade:");
        System.out.println("Selecione a cidade:");

        int index = getCityIndex(vertices);
        List<ShortestPath> shortestPathList = graph.shortestPathsFromSource(vertices.get(index));

        if (shortestPathList.isEmpty())
            System.out.println("\n" + vertices.get(index).getCityName() + " não alcança nenhuma outra cidade!");
        else {
            System.out.println("\nMenores distâncias possíveis a partir de " + vertices.get(index).getCityName() + ": ");
            shortestPathList.forEach(vertex -> {
                System.out.println();
                System.out.println("Destino -> " + vertex.getDestinationVertex().getCityName());
                System.out.println("Distância -> " + vertex.getDistance());
            });
        }
    }
}
