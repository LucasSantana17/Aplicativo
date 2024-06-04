package com.lucassantana.protocolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tela_busca extends AppCompatActivity {

    private EditText id_referencia;
    private EditText nome_referencia;
    private TextView tela_saida;
    private Button btn_buscar;
    private BancoDados bancoDados; // Adicione a instância do BancoDados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_busca);

        tela_saida = findViewById(R.id.tela_saida);
        nome_referencia = findViewById(R.id.identificador);
        id_referencia = findViewById(R.id.identificador);
        btn_buscar = findViewById(R.id.busca_dados);

        bancoDados = new BancoDados(this); // Inicialize a instância do BancoDados

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Referencia = nome_referencia.getText().toString();
                buscaNome(Referencia);
            }
        });
    }

    public void buscaNome(String referencia) {
        SQLiteDatabase db = bancoDados.getReadableDatabase();
        Tratamento tra = new Tratamento();

        String[] projection = {"id", "referencia", "quantidade"};
        String selection = "referencia = ?";
        String[] selectionArgs = {referencia};

        Cursor cursor = db.query(
                "Protocolo_dados",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            referencia = cursor.getString(cursor.getColumnIndexOrThrow("referencia"));
            String quantidade = cursor.getString(cursor.getColumnIndexOrThrow("quantidade"));


            String resultado = "ID: " + id + "\n" +

                    "Referência: " + referencia + "\n" +
                    "Quantidade: " + quantidade;

            tela_saida.setText(resultado);
        } else {
            tela_saida.setText(tra.Saida());
        }

        if (cursor != null) {
            cursor.close();
        }

    }
}
