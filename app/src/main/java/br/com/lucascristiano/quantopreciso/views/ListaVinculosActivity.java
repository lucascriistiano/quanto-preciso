package br.com.lucascristiano.quantopreciso.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.lucascristiano.quantopreciso.oauth.OAuthTokenRequest;
import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.adapter.VinculosAdapter;
import br.com.lucascristiano.quantopreciso.models.Vinculo;


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
        OAuthTokenRequest.getInstance().resourceRequest(context, Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<Vinculo> vinculos = getVinculosFromJson(response);

                        VinculosAdapter vinculosAdapter = new VinculosAdapter(vinculos);
                        recyclerViewVinculos.setAdapter(vinculosAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Falhou ao consultar vínculos. Erro: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private List<Vinculo> getVinculosFromJson(String response) {
        List<Vinculo> vinculos = new ArrayList<>();
        try {
            JSONObject rootObject = new JSONObject(response);
            JSONArray vinculosArray = rootObject.getJSONArray("discentes");

            for(int i = 0; i < vinculosArray.length(); i++) {
                JSONObject vinculoObject = vinculosArray.getJSONObject(i);

                int idUsuario = vinculoObject.getInt("idUsuario");
                int idDiscente = vinculoObject.getInt("id");
                String matricula = vinculoObject.getString("matricula");
                String curso = vinculoObject.getString("curso");

                Vinculo vinculo = new Vinculo(idUsuario, idDiscente, matricula, curso);
                vinculos.add(vinculo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return vinculos;
    }

}
