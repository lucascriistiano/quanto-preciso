package br.com.lucascristiano.quantopreciso.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.oauth.OAuthTokenRequest;

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
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                OAuthTokenRequest.getInstance().getTokenCredential(LoginActivity.this,
                        "http://apitestes.info.ufrn.br/authz-server", getString(R.string.client_id),
                        getString(R.string.client_secret), intent);
            }
        });
    }
}
