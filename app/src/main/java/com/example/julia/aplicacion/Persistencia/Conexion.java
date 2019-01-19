package com.example.julia.aplicacion.Persistencia;

public class Conexion {
    public String url="http://soren.itiud.org/";

    public Conexion() {

    }

    public String entrarapp(String palabra){
        return getUrl()+"consultar_usuario.php?user="+palabra;
    }

    public String mostrarusuario(String palabra){
        return getUrl()+"mostrar_usuario.php?user="+palabra;
    }

    public String mostrarimagen(String palabra){
        return getUrl()+palabra;
    }

    public String mostrarejercicio(String palabra){
        return getUrl()+"ejercicios/"+palabra;
    }

    public String mostrarfotomedidas(String palabra){
        return getUrl()+"fotos_medidas/"+palabra;
    }

    public String mostrarmedida(String palabra){
        return url+"mostrar_medida.php?user="+palabra;
    }

    public String mostrarrutina(String palabra){
        return url+"mostrar_rutina.php?user="+palabra;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
