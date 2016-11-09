package br.com.lucascristiano.quantopreciso.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.models.SituacaoTurma;
import br.com.lucascristiano.quantopreciso.models.Turma;
import br.com.lucascristiano.quantopreciso.oauth.OAuthTokenRequest;
import br.com.lucascristiano.quantopreciso.util.UfrnServiceUtil;

public class NotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        Intent intent = getIntent();
        Turma turma = (Turma) intent.getSerializableExtra("turma");
        int idTurma = turma.getIdTurma();
        int idDiscente = intent.getIntExtra("idDiscente", 0);
        consultarSituacaoTurma(this, idTurma, idDiscente);
    }

    private void consultarSituacaoTurma(final Context context, int idTurma, int idDiscente) {
        String url = UfrnServiceUtil.getSituacaoTurmaUrl(idDiscente, idTurma);
        OAuthTokenRequest.getInstance().resourceRequest(context, Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE", response);

                        SituacaoTurma situacaoTurma = UfrnServiceUtil.getSituacaoTurmaFromJson(response);
                        Toast.makeText(context, situacaoTurma.getUnidades() + " " + situacaoTurma.getNotas(), Toast.LENGTH_LONG).show();

                        //TODO Do calculations
                        //TODO Fill fields with calculations results
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", error.toString());
                        error.printStackTrace();
                        Toast.makeText(context, "Falhou ao consultar situação do discente na turma.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
