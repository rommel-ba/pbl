package com.example.rommel.pbl.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rommel.pbl.model.Aluno;
import com.example.rommel.pbl.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by rommel on 01/10/16.
 */

public class Banco extends SQLiteOpenHelper {

    private static final String DATABASE = "PBL";
    private static final int VERSAO = 1;

    public Banco(Context context){
        super(context, DATABASE, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabelaDisciplina());
        sqLiteDatabase.execSQL(tabelaAluno());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //Criação de Tabelas
    private String tabelaAluno(){
        return "CREATE TABLE aluno (" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT UNIQUE NOT NULL);";
    }

    private String tabelaDisciplina(){
        return "CREATE TABLE disciplina (" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT UNIQUE NOT NULL);";
    }
}
