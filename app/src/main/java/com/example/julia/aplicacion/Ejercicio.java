package com.example.julia.aplicacion;

public class Ejercicio {

    private String nombre;
    private String musculo;
    private int repeticion;
    private int serie;
    private int descanso;
    private String video;


    public Ejercicio() {
        this.nombre = "";
        this.musculo="";
        this.repeticion = 0;
        this.serie = 0;
        this.descanso=0;
        this.video="";

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(int repeticion) {
        this.repeticion = repeticion;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }
}
