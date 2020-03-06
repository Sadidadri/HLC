package com.example.listview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public EditText texto;
    ListaElementos list;

    public CustomDialogClass(Activity a, ListaElementos lista) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.list = lista;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_agnade);
        yes = (Button) findViewById(R.id.aceptar);
        no = (Button) findViewById(R.id.cancelar);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String cadenaIntroducida = texto.getText().toString();

        switch (v.getId()) {
            case R.id.aceptar:
                if(!texto.equals("")){
                    list.setElemento(cadenaIntroducida);
                 }
                dismiss();
                break;
            case R.id.cancelar:
            default:
                dismiss();
                break;
        }
        dismiss();
    }
}