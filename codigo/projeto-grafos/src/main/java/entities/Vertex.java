package entities;

public class Vertex {

    private static int NEXT_ID = 0;
    private final int id;
    private String cityName;

    /**
     * @param cityName nome da cidade do vertice
     */
    public Vertex(String cityName) {
        this.id = NEXT_ID++;
        this.cityName = cityName;
    }

    public Vertex() {
        this.id = NEXT_ID++;
        this.cityName = null;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName nova da cidade do vertice
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
