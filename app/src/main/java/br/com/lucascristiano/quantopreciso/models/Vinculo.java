package br.com.lucascristiano.quantopreciso.models;

import java.io.Serializable;

/**
 * Created by lucascriistiano on 07/11/16.
 */
public class Vinculo implements Serializable {

    private int idUsuario;
    private int idDiscente;
    private String matricula;
    private String curso;

    public Vinculo(int idUsuario, int idDiscente, String matricula, String curso) {
        this.idUsuario = idUsuario;
        this.idDiscente = idDiscente;
        this.matricula = matricula;
        this.curso = curso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDiscente() {
        return idDiscente;
    }

    public void setIdDiscente(int idDiscente) {
        this.idDiscente = idDiscente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}
