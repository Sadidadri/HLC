package com.example.pr1marcadorbaloncesto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView puntosLocal;
    TextView puntosVisitante;
    Button botonReinicio;
    Button localResta1;
    Button localSuma1;
    Button localSuma2;
    Button localSuma3;
    Button visitanteResta1;
    Button visitanteSuma1;
    Button visitanteSuma2;
    Button visitanteSuma3;
    TextView local;
    TextView visitante;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        puntosLocal = findViewById(R.id.puntosLocal);
        puntosVisitante = findViewById(R.id.puntosVisitante);
        botonReinicio = findViewById(R.id.reiniciopartido);
        localResta1 = findViewById(R.id.localminus1);
        localSuma1 = findViewById(R.id.localplus1);
        localSuma2 = findViewById(R.id.localplus2);
        localSuma3 = findViewById(R.id.localplus3);
        visitanteResta1 = findViewById(R.id.visitanteminus1);
        visitanteSuma1 = findViewById(R.id.visitanteplus1);
        visitanteSuma2 = findViewById(R.id.visitanteplus2);
        visitanteSuma3 = findViewById(R.id.visitanteplus3);

        local = findViewById(R.id.local);
        visitante = findViewById(R.id.visitante);

        asignarNombresEquipos();
    }

    private void asignarNombresEquipos() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            local.setText(bundle.getString("texto_eq_local"));
            visitante.setText(bundle.getString("texto_eq_visitante"));
        }
    }
    public void reinicioPartido(View v){
        AlertDialog.Builder avisoReinicioPartido = new AlertDialog.Builder(this);
        avisoReinicioPartido.setTitle("¿Reiniciar Partido?");
        avisoReinicioPartido.setMessage("¿Estás seguro de que quieres reiniciar los marcadores?");
        avisoReinicioPartido.setCancelable(false);
        avisoReinicioPartido.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface avisoReinicioPartido, int id) {
                aceptar();
            }
        });
        avisoReinicioPartido.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface avisoReinicioPartido, int id) {

            }
        });

        avisoReinicioPartido.show();

    }
    public void aceptar(){
        Toast t = Toast.makeText(this,"Partido reiniciado",Toast.LENGTH_SHORT);
        t.show();
        puntosLocal.setText("00");
        puntosVisitante.setText("00");
    }
    public void restaUnoLocal(View v){
        int puntos = Integer.parseInt(puntosLocal.getText().toString());
        if (puntos > 0){
            puntosLocal.setText(Integer.toString(--puntos));
        }
    }
    public void sumaUnoLocal(View v){
        int puntos = Integer.parseInt(puntosLocal.getText().toString()) + 1;
        puntosLocal.setText(Integer.toString(puntos));
    }
    public void sumaDosLocal(View v){
        int puntos = Integer.parseInt(puntosLocal.getText().toString()) + 2;
        puntosLocal.setText(Integer.toString(puntos));
    }
    public void sumaTresLocal(View v){
        int puntos = Integer.parseInt(puntosLocal.getText().toString()) + 3;
        puntosLocal.setText(Integer.toString(puntos));
    }
    public void restaUnoVisitante(View v){
        int puntos = Integer.parseInt(puntosVisitante.getText().toString());
        if (puntos > 0){
            puntosVisitante.setText(Integer.toString(--puntos));
        }
    }
    public void sumaUnoVisitante(View v){
        int puntos = Integer.parseInt(puntosVisitante.getText().toString()) + 1;
        puntosVisitante.setText(Integer.toString(puntos));
    }
    public void sumaDosVisitante(View v){
        int puntos = Integer.parseInt(puntosVisitante.getText().toString()) + 2;
        puntosVisitante.setText(Integer.toString(puntos));
    }
    public void sumaTresVisitante(View v){
        int puntos = Integer.parseInt(puntosVisitante.getText().toString()) + 3;
        puntosVisitante.setText(Integer.toString(puntos));
    }
}
