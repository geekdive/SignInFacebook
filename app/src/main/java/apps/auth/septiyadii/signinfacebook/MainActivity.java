package apps.auth.septiyadii.signinfacebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends Activity {

    private TextView info, info2;
    private LoginButton loginButton;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.txtInformasiIDUser);
        info2 = (TextView)findViewById(R.id.txtInformasiIDToken);
        loginButton = (LoginButton) findViewById(R.id.btnLoginFacebook);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                findViewById(R.id.titleID).setVisibility(View.VISIBLE);
                findViewById(R.id.titleToken).setVisibility(View.VISIBLE);
                findViewById(R.id.txtInformasiIDUser).setVisibility(View.VISIBLE);
                findViewById(R.id.txtInformasiIDToken).setVisibility(View.VISIBLE);
                info.setText("" + loginResult.getAccessToken().getUserId());
                info2.setText("" + loginResult.getAccessToken().getToken());

            }

            @Override
            public void onCancel() {
                findViewById(R.id.txtInformasiAktifitas).setVisibility(View.VISIBLE);
                TextView tvKeterangan;
                tvKeterangan = (TextView)findViewById(R.id.txtInformasiAktifitas);
                tvKeterangan.setText("Status: \n Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                findViewById(R.id.txtInformasiAktifitas).setVisibility(View.VISIBLE);
                TextView tvKeterangan;
                tvKeterangan = (TextView)findViewById(R.id.txtInformasiAktifitas);
                tvKeterangan.setText("Login attempt failed.");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
