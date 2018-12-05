package com.example.julia.aplicacion;

import java.util.ArrayList;

public class Rutina {

    private int cod_rutina;
    private ArrayList<Ejercicio>ejercicios;

    public Rutina(int cod_rutina, ArrayList<Ejercicio> ejercicios) {
        this.cod_rutina = cod_rutina;
        this.ejercicios = ejercicios;
    }

    public Rutina() {
        this.cod_rutina = 0;
        this.ejercicios = new ArrayList<Ejercicio>();
    }

    public int getCod_rutina() {
        return cod_rutina;
    }

    public void setCod_rutina(int cod_rutina) {
        this.cod_rutina = cod_rutina;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
