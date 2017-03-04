package com.cauealmeida.androidfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
