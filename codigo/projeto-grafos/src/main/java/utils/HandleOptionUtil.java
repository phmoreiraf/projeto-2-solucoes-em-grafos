package utils;

import entities.Graph;
import records.ShortestPath;
import entities.Vertex;

import java.util.List;
import java.util.Scanner;

public class HandleOptionUtil {

    /**
     * Trata as opcoes disponiveis para o usuario, cada opcao representa uma questao exigida pelo trabalho pratico
     *
     * @param op       opcao escolhida
     * @param graph    grafo
     * @param vertices vertices do grafo
     * @throws Exception lanca uma excecao caso a opcao escolhida seja invalida, ou seja, op != (a || b || c || d)
     */
    public static void handleOption(String op, Graph graph, List<Vertex> vertices) throws Exception {
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
    public static void handleOptionA(Graph graph) {
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
    public static void handleOptionB(Graph graph, List<Vertex> vertices) {

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
    public static void handleOptionC(Graph graph, List<Vertex> vertices) {

        System.out.println("\n---------------");
        System.out.println("Letra C - Recomendação de visitas:");
        System.out.println("Selecione a cidade:");

        int index = getCityIndex(vertices);
        Vertex vertex = vertices.get(index);
        List<Vertex> recommendations = graph.getReachableVertices(vertex);

        if (recommendations.isEmpty())
            System.out.println("\n" + vertices.get(index).getCityName() + " não alcança nenhuma outra cidade!");
        else {
            System.out.println("\nRecomendações de visitas a partir de " + vertices.get(index).getCityName() + ": ");
            recommendations.forEach(v -> {
                System.out.println(vertex.getCityName() + " -> " + v.getCityName());
            });
        }
    }

    /**
     * Executa funcao relativa a letra D do trabalho pratico
     *
     * @param graph    grafo
     * @param vertices vertices do grafo
     */
    public static void handleOptionD(Graph graph, List<Vertex> vertices) {

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
                System.out.println("Destino -> " + vertex.destinationVertex().getCityName());
                System.out.println("Distância -> " + vertex.distance());
            });
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

}
