package br.com.lucascristiano.quantopreciso.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.models.Turma;
import br.com.lucascristiano.quantopreciso.views.NotasActivity;

/**
 * Created by lucascriistiano on 07/11/16.
 */

public class TurmasAdapter extends RecyclerView.Adapter<TurmasAdapter.TurmasItemViewHolder> {

    private List<Turma> turmas;

    public TurmasAdapter(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public TurmasItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item_turma, parent, false);
        return new TurmasItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TurmasItemViewHolder holder, int position) {
        String descricao = turmas.get(position).getDescricao();
        String local = turmas.get(position).getLocal();
        String horario = turmas.get(position).getHorario();

        holder.textViewDescricao.setText(descricao);
        holder.textViewLocal.setText(local);
        holder.textViewHorario.setText(horario);
    }

    @Override
    public int getItemCount() {
        return turmas.size();
    }

    public class TurmasItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewDescricao;
        TextView textViewLocal;
        TextView textViewHorario;

        public TurmasItemViewHolder(View itemView) {
            super(itemView);
            textViewDescricao = (TextView) itemView.findViewById(R.id.tv_itemturma_descricao);
            textViewLocal = (TextView) itemView.findViewById(R.id.tv_itemturma_local);
            textViewHorario = (TextView) itemView.findViewById(R.id.tv_itemturma_horario);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Turma turma = turmas.get(position);

            Context context = view.getContext();

            Intent intentTurmas = new Intent(context, NotasActivity.class);
            intentTurmas.putExtra("turma", turma);
            context.startActivity(intentTurmas);
        }
    }

}
