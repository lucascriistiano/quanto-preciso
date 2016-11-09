package br.com.lucascristiano.quantopreciso.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.lucascristiano.quantopreciso.R;
import br.com.lucascristiano.quantopreciso.oauth.OAuthTokenRequest;

/**
 * Created by Lucas Cristiano on 09/11/2016.
 */

public class GenericLoggedInActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sair, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menusair_sair:
                OAuthTokenRequest.getInstance().logout(this, "http://apitestes.info.ufrn.br/sso-server/logout");
                goToLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToLoginActivity() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentLogin);
    }

}
