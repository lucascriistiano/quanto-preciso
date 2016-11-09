package br.com.lucascristiano.quantopreciso.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lucas Cristiano on 09/11/2016.
 */
public class SituacaoTurma implements Serializable {

    private List<Nota> notas;
    private boolean consolidada;

    public SituacaoTurma(List<Nota> notas, boolean consolidada) {
        this.notas = notas;
        this.consolidada = consolidada;
    }

    public Nota getNota(int unidade) {
        return notas.get(unidade);
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public int getUnidades() {
        return notas.size();
    }

    public void addNota(Nota nota) {
        this.notas.add(nota);
    }

    public boolean isConsolidada() {
        return consolidada;
    }

    public void setConsolidada(boolean consolidada) {
        this.consolidada = consolidada;
    }

}
