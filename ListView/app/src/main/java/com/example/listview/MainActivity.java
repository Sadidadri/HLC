package com.example.listview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity
{
    // Array of strings...
    ListView simpleList;
    ListaElementos lista;
    Dialog customDialog = null;

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_main);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        lista = new ListaElementos();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listitem_titular, R.id.textView, lista.getArraylist());
        simpleList.setAdapter(arrayAdapter);
    }

    public void agnadeElemento(View v) {

        CustomDialogClass cdd = new CustomDialogClass(MainActivity.this,lista);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }


}
