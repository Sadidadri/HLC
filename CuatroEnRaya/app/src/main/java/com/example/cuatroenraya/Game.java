package com.example.cuatroenraya;

import android.widget.Toast;

public class Game {
    //Dimensiones del tablero
    static final int N_FILAS = 6;
    static final int N_COLUMNAS = 7;

    static final int VACIO = 0;
    static final int MAQUINA = 1;
    static final int JUGADOR = 2;

    static final String MAQGANADOR = "1111";
    static final String JUGGANADOR = "2222";

    private int turno = JUGADOR;
    private char estado; //J Jugado, G Ganado, T empate

    public int tablero[][];

    public Game(int jugador){
        tablero = new int[N_FILAS][N_COLUMNAS];

        for (int i=0;i<N_FILAS;i++){
            for (int j=0;j<N_COLUMNAS;j++){
                tablero[i][j] = VACIO;
            }
        }
        this.estado = 'J';
        this.turno = jugador;
    }
    public void setTurno(int t){
        this.turno = t;
    }
    public int getTurno(){
        return this.turno;
    }
    public char getEstado(){
        return this.estado;
    }
    public void setEstado(char estado){
        this.estado=estado;
    }

    public boolean actualizarTablero(Coordenada c){
        boolean lActualizado = false;

        int columna = c.getColumna();
        int fila = c.getFila();

        if(tablero[fila][columna] == VACIO){
            lActualizado = true;
            tablero[fila][columna] = getTurno();
        }

        return lActualizado;

    }
    public void cambiarTurno(){
        if (getTurno() == MAQUINA){
            setTurno(JUGADOR);
        }
        else{
            setTurno(MAQUINA);
        }

    }

    /**
     * Devuelve la fila libre de más abajo.
     * @param columna
     */
    public int seleccionarFila(int columna){
        int i = N_FILAS-1;
        int fill = -1;
        boolean lsel = false;
        while(i>=0 && !lsel){
            if (tablero[i][columna] == VACIO){
                fill = i;
                lsel = true;
            }
            i--;
        }
        return fill;
    }

    public boolean compruebaColumnaLlena(int columna){
        boolean lColumnaCompleta = false;

        if(tablero[0][columna] != VACIO){
            lColumnaCompleta = true;
        }

        return lColumnaCompleta;
    }

    /**
     * Devuelve el valor de la fila de donde se ha clickado
     * @param coordenada
     * @return
     */
    public String fila (Coordenada coordenada){
        String cadena = "";
        int f = coordenada.getFila();
        int c;
        for(c=0;c<N_COLUMNAS;c++){
            cadena += Integer.toString(tablero[f][c]);
        }
        return cadena;
    }
    /**
     * Devuelve el valor de la columna de donde se ha clickado
     * @param coordenada
     * @return
     */
    public String columna (Coordenada coordenada){
        String cadena = "";
        int c = coordenada.getColumna();
        int f;
        for(f=0;f<N_FILAS;f++){
            cadena += Integer.toString(tablero[f][c]);
        }
        return cadena;
    }
    /**
     * Devuelve el valor de la diagonal izq derecha de donde se ha clickado
     * @param coordenada
     * @return
     */
    public String diagonal1 (Coordenada coordenada){
        String cadena = "";
        int c = coordenada.getColumna();
        int f = coordenada.getFila();

        int i = f - Math.min(f,c);
        int j = c - Math.min(f,c);

        while(i<N_FILAS && j<N_COLUMNAS){
            cadena += Integer.toString(tablero [i][j]);
            i++;
            j++;
        }
        return cadena;
    }
    /**
     * Devuelve el valor de la diagonal der izquierda de donde se ha clickado
     * @param coordenada
     * @return
     */
    public String diagonal2 (Coordenada coordenada){
        String cadena = "";
        int c = 0;
        int f = 0;

       for(f=0;f<N_FILAS;f++){
           for (c=0;c<N_COLUMNAS;c++){
               if(f+c == coordenada.getColumna()+coordenada.getFila()){
                   cadena += Integer.toString(tablero[f][c]);
               }
           }
       }
        return cadena;
    }
    public int jugadaGanadora(Coordenada coordenada){
        int resultado = -1;

        //if(this.estado == 'G'){ return -1;}
        String patron = this.getTurno() == MAQUINA ? MAQGANADOR : JUGGANADOR;

        if (fila(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 1;
        }
        if (columna(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 2;
        }
        if (diagonal1(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 3;
        }
        if(diagonal2(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 4;
        }

        return resultado;
    }

    public boolean empate(){
        boolean empate = true;
        int f = 0;
        int c = 0;
        for (c = 0;c<N_COLUMNAS;c++){
            if(tablero[f][c] == 0){
                empate = false;
            }
        }
        if (empate){
            this.estado = 'T';
        }

        return empate;
    }
    public String tableroToString(){
        String str = "";
        for (int i = 0; i<N_FILAS; i++){
            for (int j = 0; j<N_COLUMNAS; j++)
                str += tablero[i][j];
        }
        return str;
    }

    public void stringToTablero(String str){
        int contador = 0;
        for(int i = 0; i<N_FILAS;i++){
            for (int j = 0; j < N_COLUMNAS; j++) {
                tablero[i][j] = Integer.parseInt(String.valueOf(str.charAt(contador)));
                contador++;
            }
        }
    }
}
