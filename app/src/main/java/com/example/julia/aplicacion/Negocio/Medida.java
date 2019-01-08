package com.example.julia.aplicacion.Negocio;

public class Medida {
        String fec;
        float peso;
        float imc;
        float igc;
        int altura;
        int cuello;
        int pecho;
        int espalda;
        int abdomen;
        int gluteo;
        int brazo;
        int antebrazo;
        int pierna;
        int pantorrilla;
        String frontal;
        String lateral;


public Medida(){
        this.fec="";
        this.peso=0;
        this.imc=0;
        this.igc=0;
        this.altura=0;
        this.cuello=0;
        this.pecho=0;
        this.espalda=0;
        this.brazo=0;
        this.antebrazo=0;
        this.abdomen=0;
        this.gluteo=0;
        this.pierna=0;
        this.pantorrilla=0;
        this.frontal="";
        this.lateral="";

    }

public Medida(String fec,float peso,float imc,float igc,int altura,int cuello, int pecho, int espalda,int brazo,int antebrazo, int abdomen, int gluteo ,int pierna, int pantorrilla,String frontal,String lateral){
        this.fec=fec;
        this.peso=peso;
        this.imc=imc;
        this.igc=igc;
        this.altura=altura;
        this.cuello=cuello;
        this.pecho=pecho;
        this.espalda=espalda;
        this.brazo=brazo;
        this.antebrazo=antebrazo;
        this.abdomen=abdomen;
        this.gluteo=gluteo;
        this.pierna=pierna;
        this.pantorrilla=pantorrilla;
        this.frontal=frontal;
        this.lateral=lateral;
    }

        public String getFec() {
        return fec;
    }

        public void setFec(String fec) {
        this.fec = fec;
    }

        public Float getPeso() {
        return peso;
    }

        public void setPeso(float peso) {
        this.peso = peso;
    }

        public float getImc() {
        return imc;
    }

        public void setImc(float imc) {
        this.imc = imc;
    }

        public float getIgc() {
        return igc;
    }

        public void setIgc(float igc) {
        this.igc = igc;
    }

        public int getAltura() {
        return altura;
    }

        public void setAltura(int altura) {
        this.altura = altura;
    }

        public int getCuello() {
        return cuello;
    }

        public void setCuello(int cuello) {
        this.cuello = cuello;
    }

        public int getPecho() {
        return pecho;
    }

        public void setPecho(int pecho) {
        this.pecho = pecho;
    }

        public int getEspalda() {
        return espalda;
    }

        public void setEspalda(int espalda) {
        this.espalda = espalda;
    }

        public int getAbdomen() {
        return abdomen;
    }

        public void setAbdomen(int abdomen) {
        this.abdomen = abdomen;
    }


        public int getGluteo() {
        return gluteo;
    }

        public void setGluteo(int gluteo) {
        this.gluteo = gluteo;
    }

        public int getBrazo() {
        return brazo;
    }

        public void setBrazo(int brazo) {
        this.brazo = brazo;
    }

        public int getAntebrazo() {
        return antebrazo;
    }

        public void setAntebrazo(int antebrazo) {
        this.antebrazo = antebrazo;
    }

        public int getPierna() {
        return pierna;
    }

        public void setPierna(int pierna) {
        this.pierna = pierna;
    }

        public int getPantorrilla() {
        return pantorrilla;
    }

        public void setPantorrilla(int pantorrilla) {
        this.pantorrilla = pantorrilla;
    }

    public String getFrontal() {
        return frontal;
    }

    public void setFrontal(String frontal) {
        this.frontal = frontal;
    }

    public String getLateral() {
        return lateral;
    }

    public void setLateral(String lateral) {
        this.lateral = lateral;
    }

    @Override
        public String toString() {
        return "Medida{" + "peso=" + peso + ", imc=" + imc + ", igc=" + igc + ", altura=" + altura + ", cuello=" + cuello + ", pecho=" + pecho + ", espalda=" + espalda + ", abdomen=" + abdomen + ", gluteo=" + gluteo + ", brazo=" + brazo + ", antebrazo=" + antebrazo + ", pierna=" + pierna + ", pantorrilla=" + pantorrilla + '}';
    }







    }
