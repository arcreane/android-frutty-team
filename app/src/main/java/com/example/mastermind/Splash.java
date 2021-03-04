package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // creating a thread for the main menu
        Thread background = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    // verifier différent type Exceptions qu'on pourrait rencontrer
                } finally {
                    // display Main menu
                    Intent i = new Intent("com.example.mastermind.MAIN");
                    startActivity(i);
                }
            }
        };

        background.start();
    }
}