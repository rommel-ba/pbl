package com.example.rommel.pbl.model;

/**
 * Created by rommel on 10/10/16.
 */

public class Nota {
    private int idNota;
    private float participacao;
    private float segmento;
    private float frequencia;
    private Sessao sessao;

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public float getParticipacao() {
        return participacao;
    }

    public void setParticipacao(float participacao) {
        this.participacao = participacao;
    }

    public float getSegmento() {
        return segmento;
    }

    public void setSegmento(float segmento) {
        this.segmento = segmento;
    }

    public float getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(float frequencia) {
        this.frequencia = frequencia;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
