package com.cauealmeida.androidfinal;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SuperHeroDetailActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtBrand;
    private String heroId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_hero_detail);

        edtName = (EditText) findViewById(R.id.edtDetailName);
        edtBrand = (EditText) findViewById(R.id.edtDetailBrand);

        // Retrieve dos dados que vêm da outra página
        Intent i = getIntent();
        String heroname = i.getStringExtra("name");
        String herobrand = i.getStringExtra("brand");
        String heroid = i.getStringExtra("id");

        edtName.setText(heroname);
        edtBrand.setText(herobrand);
        heroId = heroid;
    }

    public void doSuperUpdate (View v) {
        SQLiteDatabase db = openOrCreateDatabase("users.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        String heroname = edtName.getText().toString();
        String herobrand = edtBrand.getText().toString();
        String heroid = heroId;

        if (heroname.isEmpty() || herobrand.isEmpty()) {
            Toast.makeText(v.getContext(), R.string.title_add_error, Toast.LENGTH_LONG).show();
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put("NAME", heroname);
        cv.put("BRAND", herobrand);

        db.update("TAB_HEROES", cv, "ID = ?", new String[]{heroid});

        Toast.makeText(this, R.string.app_resp_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
