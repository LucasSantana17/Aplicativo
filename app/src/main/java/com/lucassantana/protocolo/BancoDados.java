package com.lucassantana.protocolo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BancoDados extends SQLiteOpenHelper {

     private static final String nome_data_base = "ProtocoloReferencia";
     private static final int data_base_version = 1;

     private static final String TABLE_CREATE = "CREATE TABLE Protocolo_dados (id INTEGER PRIMARY KEY AUTOINCREMENT, referencia TEXT, quantidade INTEGER);";

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
