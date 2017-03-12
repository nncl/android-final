package com.cauealmeida.androidfinal;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cauealmeida.androidfinal.model.SuperHero;

public class SuperHeroActivity extends AppCompatActivity {

    private EditText heroName;
    private EditText heroBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_hero);

        heroName = (EditText) findViewById(R.id.edtSuperName);
        heroBrand = (EditText) findViewById(R.id.edtSuperBrand);
    }

    public void doSaveHero(View v) {

        Log.i("Info", "Vamos cadastrar um super herói");

        String name = heroName.getText().toString();
        String brand = heroBrand.getText().toString();

        Log.i("Info", "Temos os valores dos campos, vamos validá-los");

        /**
         * Validação de campos vazios
         */

        if (name.isEmpty() || brand.isEmpty()) {
            Log.e("Error", "Um dos ou mais campos é(são) inválido(s)");
            Toast.makeText(this, R.string.title_add_error, Toast.LENGTH_LONG).show();
            return;
        }

        Log.i("Info", "Campos válidos, vamos salvar no banco de dados");

        SuperHero superHero = new SuperHero(name, brand);
        storeSuperHero(superHero);
    }

    /**
     * Salva super herói no SQLite
     *
     * @param {SuperHero} superHero
     */

    private void storeSuperHero(SuperHero superHero) {

        Log.i("Info", "Salvando Super Herói no banco de dados");

        SQLiteDatabase db = openOrCreateDatabase("users.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        final String CREATE_TABLE_CONTAIN = "CREATE TABLE IF NOT EXISTS TAB_HEROES ("
                + "ID INTEGER primary key AUTOINCREMENT,"
                + "NAME TEXT, BRAND TEXT )";
        db.execSQL(CREATE_TABLE_CONTAIN);

        Log.i("Info", "Banco de dados atualizado");
        Log.i("Info", "Vamos inserir um herói");

        String sql =
                "INSERT or replace INTO TAB_HEROES (NAME, BRAND) VALUES('" + superHero.getName() + "', '" + superHero.getBrand() + "')";
        db.execSQL(sql);

        Log.i("Info", "Herói inserido com sucesso");
        Toast.makeText(this, R.string.title_add_success, Toast.LENGTH_SHORT).show();
        heroName.setText("");
        heroBrand.setText("");
    }
}
