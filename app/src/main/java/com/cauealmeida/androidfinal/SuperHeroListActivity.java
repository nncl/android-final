package com.cauealmeida.androidfinal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cauealmeida.androidfinal.adapters.HeroesAdapter;
import com.cauealmeida.androidfinal.listener.ClickListener;
import com.cauealmeida.androidfinal.listener.PersonTouchListener;
import com.cauealmeida.androidfinal.model.SuperHero;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroListActivity extends AppCompatActivity {

    SQLiteDatabase database;
    Cursor cursor;
    private RecyclerView recyclerView;
    private List<SuperHero> heroes = new ArrayList<>();
    private HeroesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_hero_list);

        recyclerView = (RecyclerView) findViewById(R.id.rvHeroes);
        mAdapter = new HeroesAdapter(heroes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new PersonTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SuperHero hero = heroes.get(position);
                Intent i = new Intent(getApplicationContext(), SuperHeroDetailActivity.class);
                i.putExtra("name", hero.getName());
                i.putExtra("brand", hero.getBrand());
                i.putExtra("id", hero.getId());

                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                // TODO
            }
        }));

        loadSuperHeroes();
    }

    /**
     * Carrega os super heróis do banco
     */

    private void loadSuperHeroes() {


        Log.i("Info", "Vamos carregar os super heróis");

        try {
            SuperHero hero;
            int i = 0;
            database = openOrCreateDatabase("users.db", Context.MODE_PRIVATE, null);
            cursor = database.rawQuery("SELECT * FROM TAB_HEROES", null);

            Log.i("Info", "Temos a resposta da query");

            cursor.moveToFirst();
            i = cursor.getCount();

            Log.i("Info", "Temos " + i + " heróis");

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                hero = new SuperHero(cursor.getString(1), cursor.getString(2), cursor.getString(0));
                heroes.add(hero);
            }

            // Lista atualizada
            mAdapter.notifyDataSetChanged();

            // Nunca esquecer de fechar o cursor
            cursor.close();

        } catch (Exception e) {
            Log.e("Error", "Erro ao buscar usuário: " + e);

            Toast.makeText(this, "Erro ao encontrar usuário", Toast.LENGTH_SHORT)
                    .show();

        }
    }
}
