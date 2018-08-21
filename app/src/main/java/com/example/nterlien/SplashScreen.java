package com.example.nterlien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splashcreentrans);
        final Intent intent = new Intent(this,Login_Screen.class);

        Thread timer =new Thread(){
            public void run () {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
                }
            };
        timer.start();

    }

}
