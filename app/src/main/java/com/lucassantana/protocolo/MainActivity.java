package com.lucassantana.protocolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private View view;
    private EditText quantidade_pct;
    private EditText numero_referencia;
    private Button button;
    private BancoDados bancoDados;

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bancoDados = new BancoDados(this);

        quantidade_pct = findViewById(R.id.Quantidade_pcs);
        numero_referencia = findViewById(R.id.text_numero_referencia);
        button = findViewById(R.id.Button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String referencia = numero_referencia.getText().toString();
                String quantidade = quantidade_pct.getText().toString();

                Log.d(TAG, "quantidade = " + quantidade);
                Log.d(TAG, "referencia = " + referencia);

                addReference(referencia,quantidade);
            }
        });


    }

    private void addReference(String referencia, String quantidade) {
        ContentValues values = new ContentValues();
        values.put("referencia", referencia);
        values.put("quantidade", quantidade);

        // Inserir dados no banco de dados
        bancoDados.getWritableDatabase().insert("Protocolo_dados", null, values);

        Log.d(TAG, "Referencia adicionada: " + referencia + ", Quantidade: " + quantidade);
    }

}
