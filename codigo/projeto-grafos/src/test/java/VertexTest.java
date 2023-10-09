import entities.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VertexTest {

    Vertex vertex = new Vertex();

    @BeforeEach
    public void setUp() {
        vertex = new Vertex("Nova Era");
    }

    @Test
    public void getCityName() {
        assertEquals("Nova Era", vertex.getCityName());
    }

    @Test
    public void setCityName() {
        vertex.setCityName("Contagem");
        assertEquals("Contagem", vertex.getCityName());
    }

    @Test
    public void getId() {
        Vertex newVertex = new Vertex("Viçosa");
        assertEquals(2, newVertex.getId());
    }

}
