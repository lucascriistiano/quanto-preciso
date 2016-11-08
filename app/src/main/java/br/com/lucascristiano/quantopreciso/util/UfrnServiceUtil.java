package br.com.lucascristiano.quantopreciso.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.lucascristiano.quantopreciso.models.Turma;
import br.com.lucascristiano.quantopreciso.models.Vinculo;

/**
 * Created by Lucas Cristiano on 08/11/2016.
 */

public class UfrnServiceUtil {

    public static List<Vinculo> getVinculosFromJson(String response) {
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

    public static List<Turma> getTurmasFromJson(String response) {
        List<Turma> turmas = new ArrayList<>();
        try {
            JSONArray rootArray = new JSONArray(response);
            for(int i = 0; i < rootArray.length(); i++) {
                JSONObject turmaObject = rootArray.getJSONObject(i);

                int idTurma = turmaObject.getInt("id");
                String descricao = turmaObject.getString("descricao");
                String local = turmaObject.getString("local");
                String horario = turmaObject.getString("horario");

                Turma turma = new Turma(idTurma, descricao, local, horario);
                turmas.add(turma);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return turmas;
    }

}
