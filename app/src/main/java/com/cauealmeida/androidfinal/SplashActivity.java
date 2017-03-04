package com.cauealmeida.androidfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
                // TODO create database and insert dummy data, for now
                // TODO get user from API and insert this user into our DB

                createDatabase();
                redirectUser();
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
            openScreen();
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    public void openScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish(); // Destroy SplashScreen View
    }

    public void createDatabase() {
        Log.i("Info", "Animação finalizada. Vamos criar o banco de dados");
        SQLiteDatabase db;
        db = openOrCreateDatabase("Users", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        final String CREATE_TABLE_CONTAIN = "CREATE TABLE IF NOT EXISTS TAB_USERS ("
                + "ID INTEGER primary key AUTOINCREMENT,"
                + "NAME TEXT )";
        db.execSQL(CREATE_TABLE_CONTAIN);

        Log.i("Info", "Banco de dados criado");
        Log.i("Info", "Vamos inserir um usuário");

        String sql =
                "INSERT or replace INTO TAB_USERS (NAME) VALUES('Caue')";
        db.execSQL(sql);

        Log.i("Info", "Usuário inserido com sucesso");
        Log.i("Info", "Vamos redirecionar o usuário para a tela de login");
    }
}
