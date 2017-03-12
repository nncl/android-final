package com.cauealmeida.androidfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SuperHeroListActivity extends AppCompatActivity {

    SQLiteDatabase database;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_hero_list);

        loadSuperHeroes();
    }

    /**
     * Carrega os super heróis do banco
     */

    private void loadSuperHeroes() {
        Log.i("Info", "Vamos carregar os super heróis");

        try {
            int i = 0;
            database = openOrCreateDatabase("users.db", Context.MODE_PRIVATE, null);
            cursor = database.rawQuery("SELECT * FROM TAB_HEROES", null);

            Log.i("Info", "Temos a resposta da query");

            cursor.moveToFirst();
            i = cursor.getCount();
            cursor.close();

            Log.i("Info", "Temos " + i + " heróis");
            // TODO Show on recycler view

        } catch (Exception e) {
            Log.e("Error", "Erro ao buscar usuário: " + e);

            Toast.makeText(this, "Erro ao encontrar usuário", Toast.LENGTH_SHORT)
                    .show();

        }
    }
}
