package br.com.lucascristiano.quantopreciso.models;

/**
 * Created by Lucas Cristiano on 09/11/2016.
 */
public class Nota {

    private int unidade;
    private double notaAtual;
    private boolean lancada;
    private double notaNecessaria;

    public Nota(int unidade, double notaAtual, boolean lancada) {
        this.unidade = unidade;
        this.notaAtual = notaAtual;
        this.lancada = lancada;
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

    public boolean isLancada() {
        return lancada;
    }

    public void setLancada(boolean lancada) {
        this.lancada = lancada;
    }

    public double getNotaNecessaria() {
        return notaNecessaria;
    }

    public void setNotaNecessaria(double notaNecessaria) {
        this.notaNecessaria = notaNecessaria;
    }

}
