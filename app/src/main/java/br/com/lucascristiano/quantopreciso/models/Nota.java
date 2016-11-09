package br.com.lucascristiano.quantopreciso.models;

/**
 * Created by Lucas Cristiano on 09/11/2016.
 */
public class Nota {

    private int unidade;
    private double notaAtual;
    private double notaNecessaria;

    public Nota(int unidade, double notaAtual) {
        this.unidade = unidade;
        this.notaAtual = notaAtual;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public double getNotaAtual() {
        return notaAtual;
    }

    public void setNotaAtual(double notaAtual) {
        this.notaAtual = notaAtual;
    }

    public double getNotaNecessaria() {
        return notaNecessaria;
    }

    public void setNotaNecessaria(double notaNecessaria) {
        this.notaNecessaria = notaNecessaria;
    }

}
