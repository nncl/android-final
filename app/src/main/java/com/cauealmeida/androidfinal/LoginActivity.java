package com.cauealmeida.androidfinal;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO test: list users from db
    }

    public void doSignIn(View v) {
        Log.i("Info", "Vamos realizar o login");
        String username = ((EditText) findViewById(R.id.edtUsername))
                .getText()
                .toString();

        String password = ((EditText) findViewById(R.id.edtPassword))
                .getText()
                .toString();

        Log.i("Info", "Temos usuário e senha. Vamos verificar acesso");

        savePreferences(username, true);

        SplashActivity splash = new SplashActivity();
        splash.openScreen();
    }

    private void savePreferences(String username, Boolean keepSession) {
        SharedPreferences settings = getSharedPreferences("PREFERENCES",
                MODE_PRIVATE); // Only our app has access so

        SharedPreferences.Editor editor = settings.edit();

        editor.putString("username", username);
        editor.putBoolean("keepSession", keepSession);
        editor.commit();
    }
}
