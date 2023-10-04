package br.pucminas.solucoesemgrafos.entities;

public class Vertex {

    private int id;
    private static int NEXT_ID = 1;
    private String cityName;

    public Vertex(String cityName) {
        this.id = NEXT_ID++;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
