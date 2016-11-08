package br.com.lucascristiano.quantopreciso.views;

import android.content.Context;
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
import br.com.lucascristiano.quantopreciso.adapter.VinculosAdapter;
import br.com.lucascristiano.quantopreciso.models.Vinculo;
import br.com.lucascristiano.quantopreciso.oauth.OAuthTokenRequest;
import br.com.lucascristiano.quantopreciso.util.UfrnServiceUtil;


public class ListaVinculosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewVinculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vinculos);

        recyclerViewVinculos = (RecyclerView) findViewById(R.id.rv_vinculos_lista);

        consultarVinculos(this);
    }

    private void consultarVinculos(final Context context) {
        String url = "http://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
        OAuthTokenRequest.getInstance().resourceRequest(context, Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<Vinculo> vinculos = UfrnServiceUtil.getVinculosFromJson(response);

                        VinculosAdapter vinculosAdapter = new VinculosAdapter(vinculos);
                        recyclerViewVinculos.setAdapter(vinculosAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", error.toString());
                        Log.d("VolleyError", error.getStackTrace().toString());
                        Toast.makeText(context, "Falhou ao consultar vínculos.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
