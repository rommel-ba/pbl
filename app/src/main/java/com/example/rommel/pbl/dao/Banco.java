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

    public void inserir(Disciplina disciplina){
        ContentValues values = new ContentValues();
        values.put("nome", disciplina.getNome());
        if (disciplina.getCodigo() == 0){
            getWritableDatabase().insert("disciplina", null, values);
            System.out.println("Id disciplina = " + disciplina.getCodigo());
        }else{
            String args[] = {"" + disciplina.getCodigo()};
            getWritableDatabase().update("disciplina", values, "id = ?", args);
        }
    }

    public void deletar(Disciplina disciplina){
        ContentValues values = new ContentValues();
        String args[] = {"" + disciplina.getCodigo()};
        getWritableDatabase().delete("disciplina", "id = ?", args);
    }

    public ArrayList<Disciplina> getDisciplinas(){
        Disciplina disciplina;
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String sql = "select * from disciplina;" ;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()){
            disciplina = new Disciplina();
            disciplina.setCodigo(cursor.getInt(cursor.getColumnIndex("id")));
            disciplina.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }

    public void inserir(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        if (aluno.getId() == 0){
            getWritableDatabase().insert("aluno", null, values);
            System.out.println("Id aluno = " + aluno.getId());
        }else{
            String args[] = {"" + aluno.getId()};
            getWritableDatabase().update("aluno", values, "id = ?", args);
        }
    }

    public void deletar(Aluno aluno){
        ContentValues values = new ContentValues();
        String args[] = {"" + aluno.getId()};
        getWritableDatabase().delete("aluno", "id = ?", args);
    }

    public ArrayList<Aluno> getAlunos(){
        Aluno aluno;
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "select * from aluno;" ;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()){
            aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunos.add(aluno);
        }
        return alunos;
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
