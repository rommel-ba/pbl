package com.example.rommel.pbl.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rommel on 10/10/16.
 */

public class Turma implements Serializable{

    private int idTurma;
    private Disciplina disciplina;
    private ArrayList<Aluno> alunos;



    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    @Override
    public String toString(){
        return idTurma + " " + disciplina.getNome();
    }
}

