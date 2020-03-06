package com.example.pr2calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultado;
    Button igual;
    Button cero;
    Button uno;
    Button dos;
    Button tres;
    Button cuatro;
    Button cinco;
    Button seis;
    Button siete;
    Button ocho;
    Button nueve;
    Button punto;
    Button mas;
    Button menos;
    Button por;
    Button dividido;
    Button clear;

    String operando;
    Double valor1 = 0.0;
    Double valor2 = 0.0;
    double resultadoNum = 0;
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.resultado);
        igual = findViewById(R.id.igual);
        cero = findViewById(R.id.cero);
        uno = findViewById(R.id.uno);
        dos = findViewById(R.id.dos);
        tres = findViewById(R.id.tres);
        cuatro = findViewById(R.id.cuatro);
        cinco = findViewById(R.id.cinco);
        seis = findViewById(R.id.seis);
        siete = findViewById(R.id.siete);
        ocho = findViewById(R.id.ocho);
        nueve = findViewById(R.id.nueve);
        punto = findViewById(R.id.punto);
        mas = findViewById(R.id.mas);
        menos = findViewById(R.id.menos);
        por = findViewById(R.id.por);
        dividido = findViewById(R.id.dividido);
        clear = findViewById(R.id.clear);
    }
    public void concatena(String elemento){
        String texto = resultado.getText().toString();
        texto += elemento;
        resultado.setText(texto);
    }
    public void pulsaCero(View v){
        concatena("0");
    }
    public void pulsaUno(View v){
        concatena("1");
    }
    public void pulsaDos(View v){
        concatena("2");
    }
    public void pulsaTres(View v){
        concatena("3");
    }
    public void pulsaCuatro(View v){
        concatena("4");
    }
    public void pulsaCinco(View v){
        concatena("5");
    }
    public void pulsaSeis(View v){
        concatena("6");
    }
    public void pulsaSiete(View v){
        concatena("7");
    }
    public void pulsaOcho(View v){
        concatena("8");
    }
    public void pulsaNueve(View v){
        concatena("9");
    }
    public void pulsaPunto(View v){
        concatena(".");
    }
    public void pulsaMas(View v){
        valor1 = Double.parseDouble(resultado.getText().toString());
        resultado.setText("");
        operando = "+";
        contador = 0;
    }
    public void pulsaMenos(View v){
        valor1 = Double.parseDouble(resultado.getText().toString());
        resultado.setText("");
        operando = "-";
        contador = 0;
    }
    public void pulsaPor(View v){
        valor1 = Double.parseDouble(resultado.getText().toString());
        resultado.setText("");
        operando = "*";
        contador = 0;
    }
    public void pulsaDividido(View v){
        valor1 = Double.parseDouble(resultado.getText().toString());
        resultado.setText("");
        operando = "/";
        contador = 0;
    }
    public void pulsaClear(View v){
        resultado.setText("");
    }
    public void pulsaIgual(View v){
        valor2 = Double.parseDouble(resultado.getText().toString());
        if(operando.equals("+")){
            resultadoNum = valor1 + valor2;
        }else if(operando.equals("-")){
            resultadoNum = valor1 - valor2;
        }else if(operando.equals("*")){
            resultadoNum = valor1 * valor2;
        }else if(operando.equals("/")){
            resultadoNum = valor1 / valor2;
        }
        resultado.setText(String.valueOf(resultadoNum));
    }
    public void pulsaMasOMenos(View v){

        if (contador%2 == 0){
            resultado.setText(("-"+resultado.getText().toString()));
        }else{
            resultado.setText(resultado.getText().toString().replace("-",""));
        }
        contador++;
    }
}
