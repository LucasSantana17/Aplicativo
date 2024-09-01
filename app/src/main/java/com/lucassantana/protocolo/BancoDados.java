package com.lucassantana.protocolo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;


public class BancoDados extends SQLiteOpenHelper {

     private static final String nome_data_base = "Protocolo";
     private static final int data_base_version = 2;

     private static final String TABLE_CREATE = "CREATE TABLE Protocolo_dados (" +
             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
             "referencia TEXT NOT NULL UNIQUE, " +
             "quantidade INTEGER NOT NULL, " +
             "data_hora DATETIME DEFAULT CURRENT_TIMESTAMP" +
             ");";



     public BancoDados(Context context){
          super(context, nome_data_base, null, data_base_version);
     }

     @Override
     public void onCreate(SQLiteDatabase db){
          db.execSQL(TABLE_CREATE);
     }

     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          // Este método é chamado quando a versão do banco de dados é aumentada
          db.execSQL("DROP TABLE IF EXISTS Protocolo_dados");
          onCreate(db);
     }

}
