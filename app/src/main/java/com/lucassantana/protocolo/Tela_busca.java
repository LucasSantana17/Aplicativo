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
    private TextView tela_saida;
    private Button btn_buscar;
    private BancoDados bancoDados; // Adicione a instância do BancoDados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_busca);

        tela_saida = findViewById(R.id.tela_saida);
        id_referencia = findViewById(R.id.identificador);
        btn_buscar = findViewById(R.id.busca_dados);

        bancoDados = new BancoDados(this); // Inicialize a instância do BancoDados

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = id_referencia.getText().toString(); // Converter o ID para String
                buscarReference(Integer.parseInt(id)); // Chamar o método buscarReference com o ID convertido
            }
        });
    }

    public void buscarReference(int id) {

        SQLiteDatabase db = bancoDados.getReadableDatabase();

        String[] projection = {"id", "referencia", "quantidade"};
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)}; // Converter o ID para String

        Cursor cursor = db.query(
                "Protocolo_dados", // A tabela a ser consultada
                projection,        // As colunas que deseja recuperar
                selection,         // A cláusula WHERE
                selectionArgs,     // Os argumentos da cláusula WHERE
                null,              // GROUP BY
                null,              // HAVING
                null               // ORDER BY
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Se houver resultados, você pode acessá-los aqui
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String referencia = cursor.getString(cursor.getColumnIndexOrThrow("referencia"));
            String quantidade = cursor.getString(cursor.getColumnIndexOrThrow("quantidade"));

            // Exemplo: textView.setText("ID: " + id + ", Referencia: " + referencia + ", Quantidade: " + quantidade);
            String resultado = "ID: " + id + "\n" +
                    "Referência: " + referencia + "\n" +
                    "Quantidade: " + quantidade;

            // Define o texto do TextView tela_saida
            tela_saida.setText(resultado);
        } else {
            // Se não houver resultados, exibe uma mensagem indicando isso
            tela_saida.setText("Nenhum resultado encontrado para o ID: " + id);
        }

        // Sempre feche o cursor após o uso para liberar os recursos
        if (cursor != null) {
            cursor.close();
        }
    }
}
