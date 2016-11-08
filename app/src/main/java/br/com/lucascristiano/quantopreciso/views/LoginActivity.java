package br.com.lucascristiano.quantopreciso.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.lucascristiano.quantopreciso.oauth.OAuthTokenRequest;
import br.com.lucascristiano.quantopreciso.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = (Button) findViewById(R.id.bt_login_acessar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ListaVinculosActivity.class);
                OAuthTokenRequest.getInstance().getTokenCredential(LoginActivity.this,
                        "http://apitestes.info.ufrn.br/authz-server", getString(R.string.client_id),
                        getString(R.string.client_secret), intent);
            }
        });
    }
}
