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
        sqLiteDatabase.execSQL(tabelaTurma());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //Criação de Tabelas
    private String tabelaAluno(){
        return "CREATE TABLE aluno (" +
                "  idAluno INTEGER PRIMARY KEY," +
                "  nome TEXT);";
    }

    private String tabelaDisciplina(){
        return "CREATE TABLE disciplina (" +
                "  idDisciplina INTEGER PRIMARY KEY," +
                "  nome TEXT);";
    }

    private String tabelaTurma(){
        return "CREATE TABLE turma (" +
                "  idAluno INTEGER NOT NULL," +
                "  idDisciplina INTEGER NOT NULL," +
                "  PRIMARY KEY (idAluno, idDisciplina)," +
                "    FOREIGN KEY (idAluno)" +
                "    REFERENCES aluno (idAluno)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION," +
                "    FOREIGN KEY (idDisciplina)" +
                "    REFERENCES disciplina (idDisciplina)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION);";
    }

    private String tabelaProblema(){
        return "CREATE TABLE problema (" +
                "  idProblema INTEGER AUTOINCREMENT," +
                "  descricao TEXT," +
                "  dataInicio TEXT," +
                "  dataEntrega TEXT," +
                "  PRIMARY KEY (idProblema));";
    }

    private String tabelaSessao(){
        return "CREATE TABLE sessao (" +
                "  idSessao INTEGER NOT NULL," +
                "  idProblema INTEGER NOT NULL," +
                "  data TEXT ," +
                "  turma_idAluno INTEGER NOT NULL," +
                "  turma_idDisciplina INTEGER NOT NULL," +
                "  PRIMARY KEY (idSessao, turma_idAluno, turma_idDisciplina)," +
                "    FOREIGN KEY (idProblema)" +
                "    REFERENCES problema (idProblema)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION," +
                "   FOREIGN KEY (turma_idAluno , turma_idDisciplina)" +
                "    REFERENCES `turma` (idAluno , idDisciplina)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION);";
    }

    private String tabelaNota(){
        return "CREATE TABLE nota (" +
                "  idNota INTEGER NOT NULL," +
                "  participacao FLOAT," +
                "  segmento FLOAT," +
                "  frequencia FLOAT," +
                "  sessao_idSessao INTEGER NOT NULL," +
                "  sessao_turma_idAluno INTEGER NOT NULL," +
                "  sessao_turma_idDisciplina INTEGER NOT NULL," +
                "  PRIMARY KEY (idNota, sessao_idSessao, sessao_turma_idAluno, sessao_turma_idDisciplina)," +
                "     FOREIGN KEY (sessao_idSessao , sessao_turma_idAluno , sessao_turma_idDisciplina)" +
                "    REFERENCES sessao (idSessao , turma_idAluno , turma_idDisciplina)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION);";
    }
}
