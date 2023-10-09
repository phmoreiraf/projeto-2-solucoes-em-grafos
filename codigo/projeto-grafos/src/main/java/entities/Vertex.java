package entities;

public class Vertex {

    private final int id;
    private static int NEXT_ID = 0;
    private String cityName;

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

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
