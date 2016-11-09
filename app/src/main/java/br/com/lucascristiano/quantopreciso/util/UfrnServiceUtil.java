package br.com.lucascristiano.quantopreciso.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.lucascristiano.quantopreciso.models.Nota;
import br.com.lucascristiano.quantopreciso.models.SituacaoTurma;
import br.com.lucascristiano.quantopreciso.models.Turma;
import br.com.lucascristiano.quantopreciso.models.Vinculo;

/**
 * Created by Lucas Cristiano on 08/11/2016.
 */

public class UfrnServiceUtil {

    private static final String ROOT_URL = "http://apitestes.info.ufrn.br/ensino-services/services";

    public static String getVinculosUrl() {
        return ROOT_URL + "/consulta/listavinculos/usuario";
    }

    public static String getTurmasUrl(int idDiscente) {
        return ROOT_URL + "/consulta/turmas/usuario/discente/" + idDiscente;
    }

    public static String getSituacaoTurmaUrl(int idDiscente, int idTurma) {
        return ROOT_URL + "/consulta/matriculacomponente/discente/" + idDiscente + "/" + idTurma;
    }

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
                String horario = turmaObject.getString("descricaoHorario");

                Turma turma = new Turma(idTurma, descricao, local, horario);
                turmas.add(turma);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return turmas;
    }

    public static SituacaoTurma getSituacaoTurmaFromJson(String response) {
        SituacaoTurma situacaoTurma = null;
        try {
            JSONObject rootObject = new JSONObject(response);
            boolean consolidada = rootObject.getBoolean("consolidada");

            List<Nota> notas = new ArrayList<>();
            JSONArray notasUnidadeArray = rootObject.getJSONArray("notasUnidade");
            for(int i = 0; i < notasUnidadeArray.length(); i++) {
                JSONObject notaObject = notasUnidadeArray.getJSONObject(i);

                int unidade = notaObject.getInt("unidade");
                double media = notaObject.getDouble("media");

                Nota nota = new Nota(unidade, media);
                notas.add(nota);
            }

            situacaoTurma = new SituacaoTurma(notas, consolidada);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return situacaoTurma;
    }

}
