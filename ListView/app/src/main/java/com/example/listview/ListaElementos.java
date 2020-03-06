package com.example.listview;

import java.util.ArrayList;

public class ListaElementos{

    ArrayList<String> elementos = new ArrayList<String>();


    ListaElementos(){
        //Arraylist de base.
        setElemento("India");
        setElemento("China");
        setElemento("Australia");
        setElemento("Portugal");
        setElemento("América");
        setElemento("Nueva Zelanda");
    }

    public void setElemento(String cadena){
        if(!cadena.equals(""))
            elementos.add(cadena);
    }
    public ArrayList<String> getArraylist(){
        return elementos;
    }
}
