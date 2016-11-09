package br.com.lucascristiano.quantopreciso.models;

/**
 * Created by Lucas Cristiano on 09/11/2016.
 */
public class Nota {

    private int unidade;
    private double notaAtual;
    private boolean lancada;
    private double notaMinimaNecessaria;
    private boolean reposicao;

    public Nota(int unidade, double notaAtual, boolean lancada, boolean reposicao) {
        this.unidade = unidade;
        this.notaAtual = notaAtual;
        this.lancada = lancada;
        this.reposicao = reposicao;
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

    public boolean isReposicao() {
        return reposicao;
    }

    public void setReposicao(boolean reposicao) {
        this.reposicao = reposicao;
    }

    public double getNotaMinimaNecessaria() {
        return notaMinimaNecessaria;
    }

    public void setNotaMinimaNecessaria(double notaMinimaNecessaria) {
        this.notaMinimaNecessaria = notaMinimaNecessaria;
    }

}
