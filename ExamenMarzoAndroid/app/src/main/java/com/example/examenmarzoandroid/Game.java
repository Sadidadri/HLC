package com.example.examenmarzoandroid;

import java.util.ArrayList;

public class Game {
    //Dimensiones del tablero
    static final int N_FILAS = 4;
    static final int N_COLUMNAS = 3;

    static final int VACIO = 0;
    static final int LLENO = 1;

    public static final int PRIMER_MOVIMIENTO = 0;
    public static final int SEGUNDO_MOVIMIENTO = 1;

    public int tablero[][];

    public int tableroInicial[][] = {
            {LLENO,LLENO,VACIO},
            {LLENO,LLENO,LLENO},
            {LLENO,LLENO,VACIO},
            {LLENO,LLENO,LLENO},
            };

    public int estado;

    ArrayList<Coordenada> alrededores = new ArrayList<Coordenada>();


    public Game(){
        tablero = new int[N_FILAS][N_COLUMNAS];

        for (int i=0;i<N_FILAS;i++){
            for (int j=0;j<N_COLUMNAS;j++){
                tablero[i][j] = tableroInicial[i][j];
            }
        }

        estado = 0;
    }

    public void llenaFicha(Coordenada c){
        tablero[c.getFila()][c.getColumna()] = LLENO;

        alrededores.clear();
    }
    public void vaciaFicha(Coordenada c){
        tablero[c.getFila()][c.getColumna()] = VACIO;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int movimiento) {
        this.estado = movimiento;
    }

    public boolean estaLibre(Coordenada coor){
        if(tablero[coor.getFila()][coor.getColumna()] == VACIO){
            return true;
        }
        return false;
    }
    public ArrayList<Coordenada> getAlrededores(Coordenada coor){
        int f = coor.getFila();
        int c = coor.getColumna();

        if(Math.min(f-2,c) >= 0){
            if(tablero[f-2][c] == VACIO){
                alrededores.add(new Coordenada(f-2,c));
            }
        }
        if(Math.max(f+2,c) < N_FILAS){
            if(tablero[f+2][c] == VACIO) {
                alrededores.add(new Coordenada(f + 2, c));
            }
        }

        if(Math.min(f,c-2) >= 0){
            if(tablero[f][c-2] == VACIO) {
                alrededores.add(new Coordenada(f, c - 2));
            }
        }
        if(Math.max(f,c+2) < N_COLUMNAS){
            if(tablero[f][c+2] == VACIO) {
                alrededores.add(new Coordenada(f, c + 2));
            }
        }

        return alrededores;
    }
    public boolean estaEnAlrededor(Coordenada coor){
        for ( Coordenada c : alrededores ) {
            if(c.getFila() == coor.getFila() && c.getColumna() == coor.getColumna()){
                return true;
            }
        }
        return false;
    }
    public boolean compruebaVictoria(){
        int cuentaLlenas = 0;
        for (int i = 0; i <N_FILAS ; i++) {
            for (int j = 0; j <N_COLUMNAS ; j++) {
                if(tablero[i][j] == LLENO){
                    cuentaLlenas++;
                }
            }
        }
        if(cuentaLlenas <= 1){
            return true;
        }
        return false;
    }

}
