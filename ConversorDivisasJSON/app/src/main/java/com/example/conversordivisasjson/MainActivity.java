package com.example.conversordivisasjson;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    String decimal;
    double resultado;
    double num;

    DecimalFormat df = new DecimalFormat("#.00");

    String monedaBase = "EUR";
    String monedaDestino = "GBP";

    Button calcula;
    TextView textView;

    Spinner spinnerMonedaEntrada;
    EditText monedaEntrada;
    TextView monedaSalida;
    Spinner spinnerMonedaSalida;

    RequestQueue queue;
    String URL = "https://api.exchangeratesapi.io/latest?base="+monedaBase+"&symbols="+monedaDestino+"";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        calcula = findViewById(R.id.botonCalcular);
        spinnerMonedaEntrada = findViewById(R.id.divisaEntrada);
        monedaEntrada = findViewById(R.id.inputCantidad);
        monedaSalida = findViewById(R.id.inputCantidadSaliente);
        spinnerMonedaSalida = findViewById(R.id.divisaSalida);
        queue = Volley.newRequestQueue(this);


    }

    public void pulsaBoton (View V){
        monedaBase = spinnerMonedaEntrada.getSelectedItem().toString();
        monedaDestino = spinnerMonedaSalida.getSelectedItem().toString();
        try {
            num = Double.parseDouble(monedaEntrada.getText().toString());
        } catch (NumberFormatException e) {
            monedaSalida.setText("Error");
        }

        if (monedaBase.equals(monedaDestino)){
            monedaSalida.setText(Double.toString(num));
        }
        URL = "https://api.exchangeratesapi.io/latest?base="+monedaBase+"&symbols="+monedaDestino+"";
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response.toString());
                    decimal = obj.getJSONObject("rates").getString(monedaDestino);
                    resultado = Double.parseDouble(decimal) * num;
                    monedaSalida.setText(df.format(resultado));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //textView.setText(response.toString());
                //Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });
        queue.add(request);
    }
}