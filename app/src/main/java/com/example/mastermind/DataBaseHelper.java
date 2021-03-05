package com.example.mastermind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String GAMER_TABLE = "Gamer_TABLE";
    public static final String LOGIN = "Login";
    public static final String SCORE = "score";
    public static final String ID = "ID";

    public DataBaseHelper(@Nullable Context context) {

        super(context, "Gamer.db", null, 1);
    }
// called first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        //requête
        String sql = "CREATE TABLE " + GAMER_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + LOGIN + " TEXT, " + SCORE + " INT)";
                db.execSQL(sql);
    }
// called when datbase change.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
 public  boolean addOne(Gamer gamer){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LOGIN,gamer.getLogin());
        cv.put(SCORE, gamer.getScore());

     long insert = db.insert(GAMER_TABLE, null, cv);

     if(insert == -1)
     {return false;
     } else
         {return true;
     }
 }


 public int maxScor(String login){

     SQLiteDatabase db = this.getReadableDatabase();
     //get the database that was created in this instance
     Cursor score = db.rawQuery("select "+ SCORE +" from " + GAMER_TABLE +" where "+ LOGIN + "=?", new String[]{login});
     score.moveToFirst();

     int count;
     count = Integer.parseInt( score.getString(0));
     return count;

 }

 /*--------teste si un utilisateur   existe déjà-------*/
 public boolean checkGamerExist(String login) {


         SQLiteDatabase db = this.getReadableDatabase();
         //get the database that was created in this instance
         Cursor c = db.rawQuery("select count(*) from " + GAMER_TABLE +" where "+ LOGIN + "=?", new String[]{login});
         c.moveToFirst();

         int count;
         count = Integer.parseInt( c.getString(0));
         if (count == 0){
             return false;
         }else{
             Log.e(" login existe " + count , "choose other");
             return true;
         }


 }


 /* fonctionne retourne les 10 premier score*/

    public ArrayList<String> topTen() {

         ArrayList<String> listeTopTen =  new ArrayList<String>();

         SQLiteDatabase db = this.getReadableDatabase();
        //get the database that was created in this instance
        Cursor c = db.rawQuery("select * from " + GAMER_TABLE + " ORDER BY " + SCORE + " DESC ", null );

        c.moveToFirst();

            do {
                String score = c.getString(2);

               listeTopTen.add(score);
            } while(c.moveToNext());

        c.close();
        db.close();

        return listeTopTen;

 }









}
