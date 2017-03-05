package com.cauealmeida.androidfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase database;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        int hasUser = getData(username, password);
        Log.i("Info", String.valueOf(hasUser));

        if (hasUser > 0) {
            savePreferences(username, true);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            String error = "Usuário e/ou senha inválido(s)";
            Log.e("Error", error);
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Salva a sessão do usuário
     * @param username
     * @param keepSession
     */

    private void savePreferences(String username, Boolean keepSession) {
        SharedPreferences settings = getSharedPreferences("PREFERENCES",
                MODE_PRIVATE); // Only our app has access so

        SharedPreferences.Editor editor = settings.edit();

        editor.putString("username", username);
        editor.putBoolean("keepSession", keepSession);
        editor.commit();
    }

    /**
     * Verifica se o usuário digitado está no banco de dados
     *
     * @param username
     * @param password
     */

    public int getData(String username, String password) {
        Log.i("Info", "Vamos buscar o usuário " + username);

        try {
            int i = 0;
            database = openOrCreateDatabase("users.db", Context.MODE_PRIVATE, null);
            cursor = database.rawQuery("SELECT * FROM TAB_USERS WHERE name=" + "\"" + username.trim() + "\"" + " and password=" + "\"" + password.trim() + "\"", null);
//            c = db.rawQuery("select * from login_table where username =" + "\""+ username.trim() + "\""+" and password="+ "\""+ password.trim() + "\""+, null);

            Log.i("Info", "Temos a resposta da query");

            cursor.moveToFirst();
            i = cursor.getCount();
            cursor.close();

            return i;

        } catch (Exception e) {
            Log.e("Error", "Erro ao buscar usuário: " + e);

            Toast.makeText(this, "Erro ao encontrar usuário", Toast.LENGTH_SHORT)
                    .show();

            return 99;
        }
    }
}
