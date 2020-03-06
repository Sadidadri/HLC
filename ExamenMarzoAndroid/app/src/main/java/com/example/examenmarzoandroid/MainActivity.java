package com.example.examenmarzoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nJugadas;
    int contadorMovimientos;

    private final int ids[][] = {
            {R.id.ficha00,R.id.ficha01,R.id.ficha02},
            {R.id.ficha10,R.id.ficha11,R.id.ficha12},
            {R.id.ficha20,R.id.ficha21,R.id.ficha22},
            {R.id.ficha30,R.id.ficha31,R.id.ficha32}
    };

    Game game;
    Coordenada primeraCoordenada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
        nJugadas = findViewById(R.id.nJugadas);
        contadorMovimientos = 0;
    }

    public void tocado(View v) {
        int fila, columna, id = v.getId();
        Coordenada coordenada = new Coordenada(0,0);
        coordenada = coorJuego(id);

        if(game.getEstado() == game.PRIMER_MOVIMIENTO){
            if(!game.estaLibre(coordenada)){
                if(game.getAlrededores(coordenada).size() != 0){
                    //Cambio al segundo movimiento ya que el primero ha sido correcto  guardo la coordenada pulsada
                    game.setEstado(1);
                    primeraCoordenada = coordenada;
                }
                else{
                    Toast toast2 =
                            Toast.makeText(getApplicationContext(),"Esa ficha no tiene donde moverse", Toast.LENGTH_SHORT);
                    toast2.show();
                }
            }
            else{
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),"Has tocado una casilla vacía", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }else{
            Coordenada coorIntermedia = Coordenada.getCoorIntermedia(coordenada,primeraCoordenada);
            if(game.estaEnAlrededor(coordenada)){
                if(!game.estaLibre(coorIntermedia)){
                    limpiaFicha(ids[primeraCoordenada.getFila()][primeraCoordenada.getColumna()]);
                    game.vaciaFicha(primeraCoordenada);

                    limpiaFicha(ids[coorIntermedia.getFila()][coorIntermedia.getColumna()]);
                    game.vaciaFicha(coorIntermedia);

                    llenaFicha(ids[coordenada.getFila()][coordenada.getColumna()]);
                    game.llenaFicha(coordenada);

                    contadorMovimientos++;
                    nJugadas.setText(Integer.toString(contadorMovimientos));
                    if(game.compruebaVictoria()){
                        Toast toast5 =
                                Toast.makeText(getApplicationContext(),"Has GANADO!!", Toast.LENGTH_SHORT);
                        toast5.show();
                    }
                }
                else{
                    Toast toast4 =
                            Toast.makeText(getApplicationContext(),"Has tocado donde la casilla intermedia esta vacia", Toast.LENGTH_SHORT);
                    toast4.show();
                }
            }
            else{
                Toast toast3 =
                        Toast.makeText(getApplicationContext(),"Has tocado una casilla no válida", Toast.LENGTH_SHORT);
                toast3.show();
            }
            game.setEstado(0);
        }


    }
    private void limpiaFicha(int id){

        ImageView iView = (ImageView)findViewById(id);

        iView.setImageResource(0);

    }
    private void llenaFicha(int id){

        ImageView iView = (ImageView)findViewById(id);

        iView.setImageResource(R.drawable.rojo);

    }
    private Coordenada coorJuego(int id){
        int fila = 0;
        int columna = 0;

        for (int i=0;i<Game.N_FILAS;i++) {
            for (int j=0;j<Game.N_COLUMNAS;j++){
                if (ids[i][j] == id){
                    columna = j;
                    fila = i;
                }
            }
        }

        Coordenada coordenada = new Coordenada(fila,columna);
        return coordenada;
    }

    public void reinicio(View v) {
        contadorMovimientos = 0;
        nJugadas.setText(Integer.toString(contadorMovimientos));
        game = new Game();
        for (int i=0;i<game.N_FILAS;i++){
            for(int j = 0;j<game.N_COLUMNAS;j++){
                if((i==0 && j==2) || (i==2 && j==2)){
                    limpiaFicha(ids[i][j]);
                }else{
                    llenaFicha(ids[i][j]);
                }
            }
        }
    }
}
