package com.example.rommel.pbl.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.rommel.pbl.model.Aluno;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.model.Turma;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rommel on 10/10/16.
 */

public class TurmaDao {

    private Banco banco;
    private Turma turma;

    public TurmaDao (Context context){
        banco = new Banco(context);
    }

    public void inserir(Disciplina disciplina, Aluno aluno, int idTurma){
        System.out.println("Id da turma = " + idTurma);
        System.out.println("Disciplina id = "+ disciplina.getCodigo() + " nome = "+ disciplina.getNome());
        System.out.println("Aluno id = "+aluno.getId() + " nome = " + aluno.getNome());

        ContentValues values = new ContentValues();
        values.put("idTurma", idTurma);
        values.put("idDisciplina", disciplina.getCodigo());
        values.put("idAluno", aluno.getId());
        banco.getReadableDatabase().insert("turma", null, values);

        if (aluno.getId() == 0){
            banco.getWritableDatabase().insert("aluno", null, values);
            System.out.println("Id aluno = " + aluno.getId());
        }else{
            String args[] = {"" + aluno.getId()};
            banco.getWritableDatabase().update("aluno", values, "idAluno = ?", args);
        }

    }

    public int getIdTurma(){
        Cursor cursor = banco.getReadableDatabase().rawQuery("select max(idTurma) from turma", null);
        cursor.moveToNext();
        return (cursor.getInt(0) + 1);
    }

    public ArrayList<Aluno> alunosTurma(int id){
        ArrayList<Aluno> alunos = new ArrayList<>();
        Aluno aluno;
        String sql = "select aluno.idAluno, aluno.nome, turma.idTurma from aluno " +
                     "inner join turma on aluno.idAluno = turma.idAluno " +
                     "where turma.idTurma = " + id + ";";
        Cursor cursor = banco.getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()){
            aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("idAluno")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunos.add(aluno);
        }
        return alunos;
    }

    public ArrayList<Turma> listaTurmas(Disciplina disciplina){
        String sql;
        sql = "select distinct turma.idTurma, disciplina.idDisciplina, disciplina.nome from turma " +
              "inner join disciplina on turma.idDisciplina = disciplina.idDisciplina " +
                "where disciplina.idDisciplina = " +disciplina.getCodigo() +";";
        Cursor cursor = banco.getReadableDatabase().rawQuery(sql, null);
        ArrayList<Turma> turmas = new ArrayList<>();
        while (cursor.moveToNext()){
            turma = new Turma();
            turma.setIdTurma(cursor.getInt(cursor.getColumnIndex("idTurma")));
            Disciplina disciplinaBanco = new Disciplina();
            disciplinaBanco.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            disciplinaBanco.setCodigo(cursor.getInt(cursor.getColumnIndex("idDisciplina")));
            turma.setDisciplina(disciplinaBanco);
            turmas.add(turma);
        }
        return turmas;
    }

    public ArrayList<Turma> listaTodasTurmas(){
        String sql;
        sql ="select distinct turma.idTurma, disciplina.idDisciplina, disciplina.nome from turma " +
                "inner join disciplina on turma.idDisciplina = disciplina.idDisciplina;";
        Cursor cursor = banco.getReadableDatabase().rawQuery(sql, null);
        ArrayList<Turma> turmas = new ArrayList<>();
        while (cursor.moveToNext()){
            turma = new Turma();
            turma.setIdTurma(cursor.getInt(cursor.getColumnIndex("idTurma")));
            Disciplina disciplinaBanco = new Disciplina();
            disciplinaBanco.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            disciplinaBanco.setCodigo(cursor.getInt(cursor.getColumnIndex("idDisciplina")));
            turma.setDisciplina(disciplinaBanco);
            turmas.add(turma);
        }
        return turmas;
    }
}
