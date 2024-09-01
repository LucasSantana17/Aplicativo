package com.lucassantana.protocolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView saida_err;
    private EditText quantidade_pct;
    private EditText numero_referencia;
    private Button button;
    private BancoDados bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bancoDados = new BancoDados(this);

        saida_err = findViewById(R.id.saida_erro);
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

                if (!verificaExistencia(referencia)) {
                    addReference(referencia, quantidade);
                } else {
                    saida_err.setText("Referência já existe no banco de dados.");
                }
            }
        });
    }

    private void addReference(String referencia, String quantidade) {
        Tratamento tra = new Tratamento();
        ContentValues values = new ContentValues();
        values.put("referencia", referencia);
        values.put("quantidade", quantidade);

        try {
            bancoDados.getWritableDatabase().insert("Protocolo_dados", null, values);
            Log.d(TAG, "Referencia adicionada: " + referencia + ", Quantidade: " + quantidade);
        } catch (SQLiteException e) {
            saida_err.setText(tra.msgErr());
            Log.e(TAG, "Erro ao adicionar referência", e);
        }
    }

    private boolean verificaExistencia(String referencia) {
        SQLiteDatabase db = bancoDados.getReadableDatabase();
        Cursor cursor = db.query("Protocolo_dados", null, "referencia = ?", new String[]{referencia}, null, null, null
        );

        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();

        return exists;
    }
}
