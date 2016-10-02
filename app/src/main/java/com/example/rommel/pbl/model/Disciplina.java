package com.example.rommel.pbl.model;

import java.io.Serializable;

/**
 * Created by rommel on 01/10/16.
 */

public class Disciplina implements Serializable{
    private int codigo;
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return codigo + " " + nome;
    }
}
