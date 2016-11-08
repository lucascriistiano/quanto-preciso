package br.com.lucascristiano.quantopreciso.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.models.Vinculo;

public class ListaTurmasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turmas);

        Intent intent = getIntent();
        Vinculo vinculo = (Vinculo) intent.getSerializableExtra("vinculo");

        Toast.makeText(this, "Clicou no vínculo " + vinculo.getCurso(), Toast.LENGTH_LONG).show();
    }

}
