package br.com.lucascristiano.quantopreciso.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.models.Nota;

/**
 * Created by lucascriistiano on 07/11/16.
 */

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.NotaItemViewHolder> {

    private List<Nota> notas;

    public NotasAdapter(List<Nota> notas) {
        this.notas = notas;
    }

    @Override
    public NotaItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item_nota, parent, false);
        return new NotaItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotaItemViewHolder holder, int position) {
        Nota nota = notas.get(position);

        if(!nota.isReposicao()) {
            int unidade = nota.getUnidade();
            holder.textViewUnidade.setText("Unidade " + unidade);
        } else {
            holder.textViewUnidade.setText("Reposição");
        }

        boolean lancada = nota.isLancada();
        double valorNota = lancada ? nota.getNotaAtual() : nota.getNotaMinimaNecessaria();
        holder.textViewNota.setText(String.format("%.1f", valorNota));
        holder.textViewSituacao.setText(lancada ? "LANÇADA" : "MÍNIMO NECESSÁRIO");
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public class NotaItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUnidade;
        TextView textViewNota;
        TextView textViewSituacao;

        public NotaItemViewHolder(View itemView) {
            super(itemView);
            textViewUnidade = (TextView) itemView.findViewById(R.id.tv_itemnota_unidade);
            textViewNota = (TextView) itemView.findViewById(R.id.tv_itemnota_nota);
            textViewSituacao = (TextView) itemView.findViewById(R.id.tv_itemnota_situacao);
        }

    }

}
