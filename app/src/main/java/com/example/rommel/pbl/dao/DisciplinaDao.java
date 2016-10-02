package com.example.rommel.pbl.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rommel.pbl.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by rommel on 01/10/16.
 */

public class DisciplinaDao extends SQLiteOpenHelper {

    private static final String DATABASE = "PBL";
    private static final int VERSAO = 1;
    private static final String TABELA = "disciplina";

    public DisciplinaDao (Context context){
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ TABELA + "(" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT UNIQUE NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void inserir(Disciplina disciplina){
        ContentValues values = new ContentValues();
        values.put("nome", disciplina.getNome());
        getWritableDatabase().insert(TABELA, null, values);
    }

    public ArrayList<Disciplina> getDisciplinas(){
        Disciplina disciplina;
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String sql = "select * from " + TABELA + ";" ;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()){
            disciplina = new Disciplina();
            disciplina.setCodigo(cursor.getInt(cursor.getColumnIndex("id")));
            disciplina.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            disciplinas.add(disciplina);
        }
        for (Disciplina d : disciplinas){
            System.out.println(d.getCodigo() + " " + d.getNome());
        }
        return disciplinas;
    }
}
