package br.com.lucascristiano.quantopreciso.models;

import java.io.Serializable;

/**
 * Created by Lucas Cristiano on 08/11/2016.
 */

public class Turma implements Serializable {

    private int idTurma;
    private String descricao;
    private String local;
    private String horario;

    public Turma(int idTurma, String descricao, String local, String horario) {
        this.idTurma = idTurma;
        this.descricao = descricao;
        this.local = local;
        this.horario = horario;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
