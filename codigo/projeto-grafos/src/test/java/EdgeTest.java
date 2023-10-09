import entities.Edge;
import entities.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeTest {

    Edge edge = new Edge();
    Vertex origin = new Vertex("Belo Horizonte");
    Vertex destiny = new Vertex("Ouro Preto");

    @BeforeEach
    public void setUp() {
        edge = new Edge(origin, destiny, 120);
    }

    @Test
    public void getOrigin() {
        Vertex origin = new Vertex("Belo Horizonte");
        assertEquals(origin.getCityName(), edge.getOrigin().getCityName());
    }

    @Test
    public void setOrigin() {
        Vertex newOrigin = new Vertex("Betim");
        edge.setOrigin(newOrigin);
        assertEquals(newOrigin.getCityName(), edge.getOrigin().getCityName());
    }

    @Test
    public void getDestiny() {
        Vertex destiny = new Vertex("Ouro Preto");
        assertEquals(destiny.getCityName(), edge.getDestiny().getCityName());
    }

    @Test
    public void setDestiny() {
        Vertex newDestiny = new Vertex("Diamantina");
        edge.setDestiny(newDestiny);
        assertEquals(newDestiny.getCityName(), edge.getDestiny().getCityName());
    }
}
