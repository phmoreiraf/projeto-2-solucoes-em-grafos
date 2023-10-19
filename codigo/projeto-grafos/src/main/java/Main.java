import entities.Graph;
import utils.GenerateGraphUtil;
import utils.HandleOptionUtil;

import java.util.Scanner;

import static utils.MenuOptionUtil.*;

public class Main {

    public static void main(String[] args) throws Exception {

        /*
         * graph01 -> cidades10.txt
         * graph02 -> cidades10-hamiltoniano.txt
         * graph03 -> cidades100.txt
         * graph04 -> cidades100-hamiltoniano.txt
         */

        Scanner scanner = new Scanner(System.in);
        String fileName = "graphs/graph03.txt";
        Graph graph = GenerateGraphUtil.buildGraphFromFile(fileName);

        String op;
        while (true) {
            System.out.println(MENU_HEADER);
            System.out.println(MENU_OPTIONS);
            System.out.println(MENU_FOOTER);
            System.out.print("*Opcao: ");
            op = scanner.nextLine().toLowerCase();

            if (op.equals("s"))
                break;

            HandleOptionUtil.handleOption(op, graph, graph.getVertices());
        }
    }

}
