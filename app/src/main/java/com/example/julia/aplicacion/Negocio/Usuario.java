package com.example.julia.aplicacion.Negocio;

import com.example.julia.aplicacion.Negocio.Persona;

import java.io.Serializable;

public class Usuario extends Persona  {
    private String fotoperfil;
    private int rutina;
    private int instructor;
    private int dieta;


    public Usuario(){
            super();
        }

        public String getFotoperfil() {
            return fotoperfil;
        }

        public void setFotoperfil(String Discapacidad) {
            this.fotoperfil = Discapacidad;
        }

        public int getRutina() {
            return rutina;
        }

        public void setRutina(int rutina) {
            this.rutina = rutina;
        }

        public int getInstructor() {
            return instructor;
        }

        public void setInstructor(int instructor) {
            this.instructor = instructor;
        }

        public int getDieta() {
            return dieta;
        }

        public void setDieta(int dieta) {
            this.dieta = dieta;
        }

        @Override
        public String toString() {

            return super.toString() + "\nDiscapacidad=" + fotoperfil + "\nrutina=" + rutina + "\ninstructor=" + instructor + "\ndieta=" + dieta;
        }
}
