package com.example.cuatroenraya;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Game game;
    Musica musica;


    private final int ids[][] = {
            {R.id.ficha00,R.id.ficha01,R.id.ficha02,R.id.ficha03,R.id.ficha04,R.id.ficha05,R.id.ficha06},
            {R.id.ficha10,R.id.ficha11,R.id.ficha12,R.id.ficha13,R.id.ficha14,R.id.ficha15,R.id.ficha16},
            {R.id.ficha20,R.id.ficha21,R.id.ficha22,R.id.ficha23,R.id.ficha24,R.id.ficha25,R.id.ficha26},
            {R.id.ficha30,R.id.ficha31,R.id.ficha32,R.id.ficha33,R.id.ficha34,R.id.ficha35,R.id.ficha36},
            {R.id.ficha40,R.id.ficha41,R.id.ficha42,R.id.ficha43,R.id.ficha44,R.id.ficha45,R.id.ficha46},
            {R.id.ficha50,R.id.ficha51,R.id.ficha52,R.id.ficha53,R.id.ficha54,R.id.ficha55,R.id.ficha56}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musica.play(this,R.raw.musica);

        game = new Game(2);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuAbout:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                return true;
            case R.id.sendMessage:
                Intent intentMsg = new Intent(Intent.ACTION_SEND);
                intentMsg.setType("text/plain");
                intentMsg.putExtra(Intent.EXTRA_SUBJECT, R.string.conecta4);
                intentMsg.putExtra(Intent.EXTRA_TEXT, R.string.nuevaApp);
                startActivity(intentMsg);
                return true;
            case R.id.preferences:
                startActivity(new Intent(this, Preferences.class));
                return true;
            default:
                return true;
        }
    }
    protected void onStop(){
        super.onStop();
        musica.stop(this);
    }
    protected void onResume(){
        super.onResume();
        /*Boolean play = false;

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.contains(Preferencias.PLAY_MUSIC_KEY))
            play = sharedPreferences.getBoolean(Preferencias.PLAY_MUSIC_KEY,
                    Preferencias.PLAY_MUSIC_DEFAULT);
        if(play)
            Musica.play(this,R.raw.musica);
        }*/
        musica.play(this,R.raw.musica);
    }

    protected void onRestart() {
        super.onRestart();
        musica.play(this,R.raw.musica);
    }

    public void pulsado(View v){
        int fila,columna,id = v.getId();
        Coordenada coordenada = new Coordenada(0,0);
        ImageButton iButton = (ImageButton) v;
        coordenada = coorJuego(id);
         switch(game.getEstado()) {
             case 'G':
                 ventanaGanar();
                 break;
             case 'T':
                 ventanaEmpate();
                 break;
             case 'J':
                 if(!game.compruebaColumnaLlena(coordenada.getColumna())){
                     if(game.actualizarTablero(coordenada)) {
                         dibujaFicha(coordenada, game.getTurno());
                         if (game.jugadaGanadora(coordenada) >= 0) {
                             Toast toast2 =
                                     Toast.makeText(getApplicationContext(),
                                             getString(R.string.toastGanador1) + game.getTurno() + getString(R.string.toastGanador2), Toast.LENGTH_SHORT);
                             toast2.show();
                         } else if (game.empate()) {
                             Toast toast3 =
                                     Toast.makeText(getApplicationContext(),
                                             R.string.resultadoEmpate, Toast.LENGTH_SHORT);
                             toast3.show();
                         } else {
                             game.cambiarTurno();
                         }
                     }
                 }else{
                     Toast toast1 =
                             Toast.makeText(getApplicationContext(),
                                     R.string.columnaLlena, Toast.LENGTH_SHORT);
                     toast1.show();
                 }
                 break;
         }

    }
    private void dibujaFicha(Coordenada c,int jugador){

        int columna = c.getColumna();
        int fila = c.getFila();

        int id = ids[fila][columna];
        ImageButton iButton = (ImageButton)findViewById(id);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(jugador == game.JUGADOR){
                iButton.setImageResource(R.drawable.circuloverdel);
            }
            else if(jugador == game.MAQUINA){
                iButton.setImageResource(R.drawable.circulorojol);
            }
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if(jugador == game.JUGADOR){
                iButton.setImageResource(R.drawable.circuloverde);
            }
            else if(jugador == game.MAQUINA){
                iButton.setImageResource(R.drawable.circulorojo);
            }
        }

    }
    private Coordenada coorJuego(int id){
        int fila = 0;
        int columna = 0;

        for (int i=0;i<Game.N_FILAS;i++) {
            for (int j=0;j<Game.N_COLUMNAS;j++){
                if (ids[i][j] == id){

                    columna = j;
                    fila = game.seleccionarFila(columna);
                }
            }
        }

        Coordenada coordenada = new Coordenada(fila,columna);
        return coordenada;
    }

    private void ventanaGanar(){
        AlertDialog.Builder ventanaGanar = new AlertDialog.Builder(this);
        ventanaGanar.setTitle(getString(R.string.partidaGanadaMsg));
        ventanaGanar.setMessage(getString(R.string.jugarOtraVez));
        ventanaGanar.setCancelable(false);
        ventanaGanar.setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface ventanaGanar, int id) {
                game = new Game(2);
                pintarTableroBlanco();
            }
        });
        ventanaGanar.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface ventanaGanar, int id) {

            }
        });

        ventanaGanar.show();
    }
    private void ventanaEmpate(){
        AlertDialog.Builder ventanaEmpate = new AlertDialog.Builder(this);
        ventanaEmpate.setTitle(getString(R.string.empateMsg));
        ventanaEmpate.setMessage(getString(R.string.jugarOtraVezMsg));
        ventanaEmpate.setCancelable(false);
        ventanaEmpate.setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface ventanaEmpate, int id) {
                game = new Game(2);
                pintarTableroBlanco();
            }
        });
        ventanaEmpate.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface ventanaEmpate, int id) {

            }
        });

        ventanaEmpate.show();
    }
    public void pintarTableroBlanco(){
        for(int f = 0;f<game.N_FILAS;f++){
            for(int c=0;c<game.N_COLUMNAS;c++){
                ImageButton iButton = (ImageButton)findViewById(ids[f][c]);

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    iButton.setImageResource(R.drawable.circulol);

                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    iButton.setImageResource(R.drawable.circulo);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("TABLERO",game.tableroToString());
        outState.putInt("TURNO",game.getTurno());
        outState.putChar("ESTADO",game.getEstado());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        game.stringToTablero(savedInstanceState.getString("TABLERO"));
        game.setEstado(savedInstanceState.getChar("ESTADO"));
        game.setTurno(savedInstanceState.getInt("TURNO"));
        dibujarTablero();
        super.onRestoreInstanceState(savedInstanceState);
    }
    private void dibujarTablero() {
        ImageButton imageButton;
        for(int i = 0, cont = 0; i<Game.N_FILAS;i++){
            for (int j = 0; j < Game.N_COLUMNAS; j++) {
                Coordenada coor = new Coordenada(i,j);
                dibujaFicha(coor,game.tablero[i][j]);
            }
        }
    }

}
