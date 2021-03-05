package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        // intancier la classe qui traite la base de données
        DataBaseHelper db = new DataBaseHelper(Score.this);

        // display the high score table
        ArrayList<String>  listTopTen = new ArrayList<String>();
        listTopTen = db.topTen();

         String chaine ="";
        for (int i = 0; i < 10;i++){

             chaine += "position  "+(i+1)+ " : " + listTopTen.get(i) +"\n";

        }

        TextView textScore = findViewById(R.id.score);
        textScore.setText(chaine);


    }

}
