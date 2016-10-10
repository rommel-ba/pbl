package com.example.rommel.pbl.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.rommel.pbl.model.Aluno;
import com.example.rommel.pbl.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by rommel on 03/10/16.
 */

public class AlunoDao {
    private Banco banco;

    public AlunoDao(Context context){
        banco = new Banco(context);
    }

    public void inserir(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        if (aluno.getId() == 0){
            banco.getWritableDatabase().insert("aluno", null, values);
            System.out.println("Id aluno = " + aluno.getId());
        }else{
            String args[] = {"" + aluno.getId()};
            banco.getWritableDatabase().update("aluno", values, "idAluno = ?", args);
        }
    }

    public void deletar(Aluno aluno){
        ContentValues values = new ContentValues();
        String args[] = {"" + aluno.getId()};
        banco.getWritableDatabase().delete("aluno", "idAluno = ?", args);
    }

    public ArrayList<Aluno> getAlunos(){
        Aluno aluno;
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "select * from aluno;" ;
        Cursor cursor = banco.getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()){
            aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("idAluno")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunos.add(aluno);
        }
        return alunos;
    }
}
