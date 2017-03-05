package com.cauealmeida.androidfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    ImageView image;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image = (ImageView) findViewById(R.id.imgLogo);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadein);
        image.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO get user from API and insert this user into our DB
                getUserFromAPI();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * Verifica se o usuário já logou para que seja automaticamente
     * redirecionado para a tela principal. Do contrário, terá de fazer login.
     */

    private void redirectUser() {
        SharedPreferences settings = getSharedPreferences("PREFERENCES", MODE_PRIVATE);

        if (settings.getBoolean("keepSession", false)) {
            Log.i("Info", "Usuário já entrou uma vez, vamos redirecioná-lo");
            openScreen();
        } else {
            Log.i("Info", "Usuário precisa logar");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    public void openScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish(); // Destroy SplashScreen View
    }

    public void createDatabase(String name, String password) {
        Log.i("Info", "Animação finalizada. Vamos criar o banco de dados caso não exista");

        SQLiteDatabase db = openOrCreateDatabase("users.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        final String CREATE_TABLE_CONTAIN = "CREATE TABLE IF NOT EXISTS TAB_USERS ("
                + "ID INTEGER primary key AUTOINCREMENT,"
                + "NAME TEXT, PASSWORD TEXT )";
        db.execSQL(CREATE_TABLE_CONTAIN);

        Log.i("Info", "Banco de dados criado");
        Log.i("Info", "Vamos inserir um usuário");

        String sql =
                "INSERT or replace INTO TAB_USERS (NAME, PASSWORD) VALUES('" + name + "', '" + password + "')";
        db.execSQL(sql);

        Log.i("Info", "Usuário inserido com sucesso");
        Log.i("Info", "Vamos redirecionar o usuário para a tela de login");
    }

    public class SearchUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {

                Log.i("Info", "Vamos fazer o GET para a API");

                URL url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
                Log.i("Info", "Temos a URL");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                Log.i("Info", "Realizamos o request");

                if (connection.getResponseCode() == 200) {
                    Log.i("Info", "Response 200");

                    BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = "";
                    StringBuilder response = new StringBuilder();

                    Log.i("Info", "Vamos adicionar os usuários");

                    while ((line = stream.readLine()) != null) {
                        response.append(line);
                    }

                    Log.i("Info", "Usuários adicionados. Vamos desconectar e retornar os dados");

                    connection.disconnect();

                    return response.toString();

                } else {
                    Log.e("Error", "Response diferente de 200");
                }

            } catch (Exception e) {
                Log.e("Error", "Erro ao buscar usuário da API");
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String name = jsonObject.getString("usuario");
                    String password = jsonObject.getString("senha");

                    Log.i("Info", "User name: " + name + ", User password: " + password);
                    createDatabase(name, password);

                    redirectUser();

                } catch (Exception e) {
                    Log.e("Error", "Erro ao transformar resposta em JSON");
                    e.printStackTrace();
                }
            }
        }
    }

    public void getUserFromAPI() {
        SearchUser searchUser = new SearchUser();
        searchUser.execute();
    }
}
