package com.example.julia.aplicacion.Negocio;

import java.io.Serializable;

public class Persona implements Serializable{

    private String cedula;
    private String nombre;
    private String apellido;
    private String fec_nac;
    private String correo;
    private String telefono;
    private String eps;
    private String rh;
    private String sexo;
    private String celular;




    public Persona(){
        cedula="";
        nombre="";
        apellido="";
        telefono="";
        eps="";
        sexo="";
        rh="";
        correo="";
        fec_nac="";
        celular="";
    }

    public Persona(String cedula, String nombre, String apellido, String fec_nac, String correo, String telefono, String eps, String rh, String sexo, String celular) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fec_nac = fec_nac;
        this.correo = correo;
        this.telefono = telefono;
        this.eps = eps;
        this.rh = rh;
        this.sexo = sexo;
        this.celular = celular;
    }


    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Persona:" + "\ncedula=" + cedula + "\nnombre=" + nombre + "\napellido=" + apellido + "\nfec_nac=" + fec_nac.toString() + "\ndireccion=" + correo + "\ntelefono=" + telefono  + "\neps=" + eps + "\nrh=" + rh + "\nsexo=" + sexo;
    }




}

