package com.example.mastermind;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterUser extends AppCompatActivity {
  EditText score,login;
  Button valider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         login =findViewById(R.id.name);
         //score = findViewById(R.id.scorValue);
         valider= findViewById(R.id.validate);


         valider.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Gamer gamer = null;

                 try {
                      gamer = new Gamer(1, login.getText().toString(), Integer.parseInt(score.getText().toString()));


                 }catch (Exception e){
                     Toast.makeText(RegisterUser.this, "Error", Toast.LENGTH_SHORT);
                 }

                     DataBaseHelper dataBaseHelper = new DataBaseHelper(RegisterUser.this);

                if (!dataBaseHelper.checkGamerExist(gamer.getLogin())){
                    boolean succes = dataBaseHelper.addOne(gamer);

                     Toast.makeText(RegisterUser.this, "Succes =" +succes, Toast.LENGTH_SHORT);

                    dataBaseHelper.checkGamerExist("err");


                }else{
                    Log.e(" login existe " , "choose other");
                }


                 //dataBaseHelper.checkGamerExist("err");



             }
         });





    }
}