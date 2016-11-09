package br.com.lucascristiano.quantopreciso.util;

import android.util.Log;

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
    private static final double NOTA_MINIMA_NECESSARIA = 3.0;
    private static final double MEDIA_APROVACAO_POR_NOTA = 5.0;
    private static final double MEDIA_APROVACAO = 7.0;


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
                String nomeComponente = turmaObject.getString("nomeComponente");
                String descricao = turmaObject.getString("descricao");
                String local = turmaObject.getString("local");
                String horario = turmaObject.getString("descricaoHorario");

                Turma turma = new Turma(idTurma, nomeComponente, descricao, local, horario);
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
                double media;
                boolean lancada;
                try {
                    media = notaObject.getDouble("media");
                    lancada = true;
                } catch (JSONException e) {
                    media = 0;
                    lancada = false;
                    Log.d("TEST", "Nota da unidade " + unidade + " vazia.");
                }

                Nota nota = new Nota(unidade, media, lancada, false);
                notas.add(nota);
            }

            situacaoTurma = new SituacaoTurma(notas, consolidada);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return situacaoTurma;
    }

    public static SituacaoTurma calcularSituacaoTurma(SituacaoTurma situacaoTurma) {
        List<Nota> notas = situacaoTurma.getNotas();

        double mediaNecessaria = MEDIA_APROVACAO_POR_NOTA;
        double somaNotasUnidades = 0;

        int unidades = situacaoTurma.getUnidades();
        int unidadesLancadas = 0;
        for(Nota nota : notas) {
            if(nota.isLancada()) {
                unidadesLancadas++;
                somaNotasUnidades += nota.getNotaAtual();
                if(nota.getNotaAtual() < NOTA_MINIMA_NECESSARIA) {
                    mediaNecessaria = MEDIA_APROVACAO;
                }
            }
        }

        int unidadesNaoLancadas = unidades - unidadesLancadas;
        if(unidadesNaoLancadas > 0) {
            double notaMinima = getNotaMinima(mediaNecessaria, unidades, somaNotasUnidades, unidadesNaoLancadas);
            if(notaMinima < NOTA_MINIMA_NECESSARIA) {
                notaMinima = Math.min(NOTA_MINIMA_NECESSARIA, getNotaMinima(MEDIA_APROVACAO, unidades, somaNotasUnidades, unidadesNaoLancadas));
            }

            for(Nota nota : notas) {
                if(!nota.isLancada()) {
                   nota.setNotaMinimaNecessaria(notaMinima);
                }
            }
        }

        return situacaoTurma;
    }

    private static double getNotaMinima(double mediaNecessaria, int totalUnidades, double somaNotasUnidades, int unidadesNaoLancadas) {
        //media_necessaria = (soma_unidades + (unidades_nao_lancadas * nota_minima)) / total_unidades
        return ((mediaNecessaria * totalUnidades) - somaNotasUnidades) / unidadesNaoLancadas;
    }

}
