package com.example.examenmarzoandroid;

import androidx.annotation.NonNull;

public class Coordenada {
    private int fila;
    private int columna;

    public Coordenada(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila(){return this.fila;}
    public int getColumna(){return this.columna;}

    public static Coordenada getCoorIntermedia(Coordenada c1,Coordenada c2){
        Coordenada cIntermedia;
        int filaIntermedia;
        int colIntermedia;

        if(c1.getFila() == c2.getFila()) {
            filaIntermedia = c1.getFila();
            colIntermedia = (c1.getColumna() + c2.getColumna())/2;
        }else{
                colIntermedia = c1.getColumna();
                filaIntermedia = (c1.getFila() + c2.getFila())/2;
        }

        cIntermedia = new Coordenada(filaIntermedia,colIntermedia);
        return cIntermedia;
    }
    @NonNull
    @Override
    public String toString() {
        return "x:"+fila+"y:"+columna;
    }
}
