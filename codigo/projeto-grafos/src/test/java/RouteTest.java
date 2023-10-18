import entities.Route;
import entities.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.StringJoiner;


public class RouteTest {


    private Route currentRout;

    private Vertex vertex01;

    private Vertex vertex02;

    private Vertex vertex03;



    @BeforeEach
    public void setUp() {
        vertex01 = new Vertex("Londres");
        vertex02 = new Vertex("Cidade do Cabo");
        vertex03 = new Vertex("Mumbai");
        currentRout = new Route();
    }


    @Test
    @DisplayName("Adicionar vértices a lista")
    void addVertexTest() {
        currentRout.addVertex(vertex01);
        Assertions.assertSame(vertex01, currentRout.getVertices().get(0));
    }

    @Test
    @DisplayName("Impressao com método toString")
    void toStringTest() {
        currentRout.addVertex(vertex02);
        currentRout.addVertex(vertex03);
        Assertions.assertEquals("Cidade do Cabo -> Mumbai", currentRout.toString());
    }




}
