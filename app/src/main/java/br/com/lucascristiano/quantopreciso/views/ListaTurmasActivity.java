package br.com.lucascristiano.quantopreciso.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.adapter.TurmasAdapter;
import br.com.lucascristiano.quantopreciso.models.Turma;
import br.com.lucascristiano.quantopreciso.models.Vinculo;
import br.com.lucascristiano.quantopreciso.oauth.OAuthTokenRequest;
import br.com.lucascristiano.quantopreciso.util.UfrnServiceUtil;


public class ListaTurmasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTurmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turmas);

        recyclerViewTurmas = (RecyclerView) findViewById(R.id.rv_turmas_lista);

        Intent intent = getIntent();
        Vinculo vinculo = (Vinculo) intent.getSerializableExtra("vinculo");
        consultarTurmas(this, vinculo);
    }

    private void consultarTurmas(final Context context, Vinculo vinculo) {
        int idDiscente = vinculo.getIdDiscente();
        String url = "http://apitestes.info.ufrn.br/ensino-services/services/consulta/turmas/usuario/discente/" + idDiscente;
        OAuthTokenRequest.getInstance().resourceRequest(context, Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<Turma> turmas = UfrnServiceUtil.getTurmasFromJson(response);

                        TurmasAdapter turmasAdapter = new TurmasAdapter(turmas);
                        recyclerViewTurmas.setAdapter(turmasAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", error.toString());
                        Log.d("VolleyError", error.getStackTrace().toString());
                        Toast.makeText(context, "Falhou ao consultar turmas.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
