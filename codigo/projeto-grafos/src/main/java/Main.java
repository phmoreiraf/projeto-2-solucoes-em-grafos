import entities.Graph;
import utils.GenerateGraphUtil;
import utils.HandleOptionUtil;

import java.util.Scanner;

import static utils.MenuOptionUtil.*;

public class Main {

    public static void main(String[] args) throws Exception {

        /*
         * Opcoes de grafos de teste (substituir na variavel `fileName`):
         * graph01
         * graph02
         * graph03
         * graph04
         */

        Scanner scanner = new Scanner(System.in);
        String fileName = "C:\\Users\\phmcf\\OneDrive\\Documentos\\GitHub\\projeto-2-solucoes-em-grafos\\codigo\\projeto-grafos\\graphs\\graph01.txt";
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
