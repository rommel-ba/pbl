package com.example.rommel.pbl.model;

import java.io.Serializable;

/**
 * Created by rommel on 02/10/16.
 */

public class Aluno implements Serializable {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return nome;
    }
}
