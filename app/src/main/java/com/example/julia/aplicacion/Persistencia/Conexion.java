package com.example.julia.aplicacion.Persistencia;

public class Conexion {
    public String url="http://192.168.0.4:81/proy_gimnasio/";

    public Conexion() {

    }

    public String entrarapp(String id,String clave){
        return getUrl()+"consultar_usuario.php?user="+id+"&"+"clave="+clave;
    }

    public String mostrarusuario(String palabra){
        return getUrl()+"mostrar_usuario.php?user="+palabra;
    }

    public String mostrarimagen(String palabra){
        return getUrl()+"imagenes/"+palabra;
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
