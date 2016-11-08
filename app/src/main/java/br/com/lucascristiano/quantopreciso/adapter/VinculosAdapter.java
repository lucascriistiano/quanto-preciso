package br.com.lucascristiano.quantopreciso.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.lucascristiano.quantopreciso.views.ListaTurmasActivity;
import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.models.Vinculo;

/**
 * Created by lucascriistiano on 07/11/16.
 */

public class VinculosAdapter extends RecyclerView.Adapter<VinculosAdapter.VinculosItemViewHolder> {

    private List<Vinculo> vinculos;

    public VinculosAdapter(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }

    @Override
    public VinculosItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item_vinculo, parent, false);
        return new VinculosItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VinculosItemViewHolder holder, int position) {
        String curso = vinculos.get(position).getCurso();
        String matricula = vinculos.get(position).getMatricula();

        holder.textViewCurso.setText(curso);
        holder.textViewMatricula.setText(matricula);
    }

    @Override
    public int getItemCount() {
        return vinculos.size();
    }

    public class VinculosItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewMatricula;
        TextView textViewCurso;

        public VinculosItemViewHolder(View itemView) {
            super(itemView);
            textViewCurso = (TextView) itemView.findViewById(R.id.tv_itemvinculo_curso);
            textViewMatricula = (TextView) itemView.findViewById(R.id.tv_itemvinculo_matricula);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Vinculo vinculo = vinculos.get(position);

            Context context = view.getContext();

            Intent intentTurmas = new Intent(context, ListaTurmasActivity.class);
            intentTurmas.putExtra("vinculo", vinculo);
            context.startActivity(intentTurmas);
        }
    }

}
