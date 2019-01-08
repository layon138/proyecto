package com.example.julia.aplicacion.Negocio;

public class Persona {

    String cedula;
    String nombre;
    String apellido;
    String fec_nac;
    String direccion;
    String telefono;
    String eps;
    String rh;
    String sexo;




    public Persona(){
        cedula="";
        nombre="";
        apellido="";
        telefono="";
        eps="";
        sexo="";

        rh="";
        direccion="";
        fec_nac="";

    }

    public Persona(String cedula, String nombre, String apellido, String fec_nac, String direccion, String telefono, String eps, String sexo, String rh){
        this.cedula=cedula;
        this.nombre=nombre;
        this.apellido=apellido;
        this.fec_nac=fec_nac;
        this.direccion=direccion;
        this.telefono=telefono;

        this.eps=eps;
        this.sexo=sexo;
        this.rh=rh;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Persona:" + "\ncedula=" + cedula + "\nnombre=" + nombre + "\napellido=" + apellido + "\nfec_nac=" + fec_nac.toString() + "\ndireccion=" + direccion + "\ntelefono=" + telefono  + "\neps=" + eps + "\nrh=" + rh + "\nsexo=" + sexo;
    }




}

