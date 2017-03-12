package com.cauealmeida.androidfinal;

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
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
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
        // TODO Save hero in SQLite

        Log.i("Info", superHero.getName());
        Log.i("Info", superHero.getBrand());
    }
}
