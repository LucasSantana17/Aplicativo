package com.lucassantana.protocolo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela_inicial extends AppCompatActivity {

    private Button btnTelaCadastro;
    private Button btnTelaBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        btnTelaCadastro = findViewById(R.id.btn_tela_cadastro);
        btnTelaBusca = findViewById(R.id.btn_tela_busca);

        btnTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_inicial.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnTelaBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_inicial.this, Tela_busca.class);
                startActivity(intent);
            }
        });
    }
}
