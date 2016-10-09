package com.example.rommel.pbl.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.rommel.pbl.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by rommel on 03/10/16.
 */

public class DisciplinaDao {
    private Banco banco;

    public DisciplinaDao(Context context){
        banco = new Banco(context);
    }

    public void inserir(Disciplina disciplina){
        ContentValues values = new ContentValues();
        values.put("nome", disciplina.getNome());
        if (disciplina.getCodigo() == 0){
            banco.getWritableDatabase().insert("disciplina", null, values);
            System.out.println("Id disciplina = " + disciplina.getCodigo());
        }else{
            String args[] = {"" + disciplina.getCodigo()};
            banco.getWritableDatabase().update("disciplina", values, "id = ?", args);
        }
    }

    public void deletar(Disciplina disciplina){
        ContentValues values = new ContentValues();
        String args[] = {"" + disciplina.getCodigo()};
        banco.getWritableDatabase().delete("disciplina", "id = ?", args);
    }

    public ArrayList<Disciplina> getDisciplinas(){
        Disciplina disciplina;
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String sql = "select * from disciplina;" ;
        Cursor cursor = banco.getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()){
            disciplina = new Disciplina();
            disciplina.setCodigo(cursor.getInt(cursor.getColumnIndex("id")));
            disciplina.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }
}
