package com.example.pr1marcadorbaloncesto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {

    Button botonInicio;
    EditText txt_local;
    EditText txt_visitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        botonInicio = (Button)findViewById(R.id.botonIniciar);
        txt_local = (EditText)findViewById(R.id.txt_local);
        txt_visitante = (EditText)findViewById(R.id.txt_visitante);
    }

    public void inicioPartido(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("texto_eq_local", txt_local.getText().toString());
        intent.putExtra("texto_eq_visitante", txt_visitante.getText().toString());
        startActivityForResult(intent, 0);
    }
}
