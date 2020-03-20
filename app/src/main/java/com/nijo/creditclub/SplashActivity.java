package com.nijo.creditclub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                launchWizard();

            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void launchWizard(){
        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                //  Launch app MAIN
                final Intent i = new Intent(SplashActivity.this, MainActivity.class);

                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        startActivity(i);
                        finish();
                    }
                });
            }
        });

        // Start the thread
        t.start();
    }


}