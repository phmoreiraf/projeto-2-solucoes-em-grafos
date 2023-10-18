import entities.*;
import utils.GenerateGraphUtil;
import utils.HandleOptionUtil;
import java.util.*;
import static utils.MenuOptionUtil.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String fileName = "graphs/graph02.txt";
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
