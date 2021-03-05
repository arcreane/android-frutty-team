package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnTouchListener{
    String choice1 = "", choice2 = "", choice3 = "", choice4 = "";
    String[] user_input = {choice1, choice2, choice3, choice4};
    private List<Pair<Fruit[],Integer[]>> history = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;

    // list of available fruit
    Fruit banana = new Fruit("Banana", false, true, R.drawable.banana);
    Fruit kiwi = new Fruit("Kiwi", false, true, R.drawable.kiwi);
    Fruit strawberry = new Fruit("Strawberry", false, false, R.drawable.strawberry);
    Fruit raspberry = new Fruit("Raspberry", false, false, R.drawable.raspberry);
    Fruit grapes = new Fruit("Grapes", true, false, R.drawable.grapes);
    Fruit orange = new Fruit("Orange", false, true, R.drawable.orange);
    Fruit lemon = new Fruit("Lemon", false, true, R.drawable.lemon);
    Fruit plum = new Fruit("Plum", true, false, R.drawable.plum);

    // Fruit basket = Fruits
    Map<String,Fruit> fruits = new HashMap<>();
    //{Banana, Kiwi, Strawberry, Raspberry, Grapes, Orange, Lemon, Plum};
    Fruit[] generated_answer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fruits.put(banana.getName(),banana);
        fruits.put(kiwi.getName(),kiwi);
        fruits.put(strawberry.getName(),strawberry);
        fruits.put(raspberry.getName(),raspberry);
        fruits.put(grapes.getName(),grapes);
        fruits.put(orange.getName(),orange);
        fruits.put(lemon.getName(),lemon);
        fruits.put(plum.getName(),plum);
        generated_answer = generate_answer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Integer[] test_user = {2, 2, 2, 2};
        check_if_is_won(test_user);

        RecyclerView rvFruits = findViewById(R.id.rvFruits);
        rvFruits.setLayoutManager(mLayoutManager);

        FruitAdapter fruitAdapter = new FruitAdapter(this, history);
        rvFruits.setAdapter(fruitAdapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fruits_array, android.R.layout.simple_spinner_item);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        defineSpinner(spinner1,adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        defineSpinner(spinner2,adapter);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        defineSpinner(spinner3,adapter);

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        defineSpinner(spinner4,adapter);

        Button peelable_button, seeds_button;
        LinearLayout peelable_layout, seeds_layout;

        peelable_button = findViewById(R.id.peelable);
        seeds_button = findViewById(R.id.seeds);
        peelable_layout = findViewById(R.id.peelableLL);
        seeds_layout = findViewById(R.id.seedsLL);

        peelable_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                peelable_layout.setVisibility(View.VISIBLE);
                TextView TV1 = findViewById(R.id.peelable1);
                TV1.setText(generated_answer[0].getPealable().toString());
                TextView TV2 = findViewById(R.id.peelable2);
                TV2.setText(generated_answer[1].getPealable().toString());
                TextView TV3 = findViewById(R.id.peelable3);
                TV3.setText(generated_answer[2].getPealable().toString());
                TextView TV4 = findViewById(R.id.peelable4);
                TV4.setText(generated_answer[3].getPealable().toString());
            }
        });

        seeds_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                seeds_layout.setVisibility(View.VISIBLE);
                TextView TV1 = findViewById(R.id.seeds1);
                TV1.setText(generated_answer[0].getSeed().toString());
                TextView TV2 = findViewById(R.id.seeds2);
                TV2.setText(generated_answer[1].getSeed().toString());
                TextView TV3 = findViewById(R.id.seeds3);
                TV3.setText(generated_answer[2].getSeed().toString());
                TextView TV4 = findViewById(R.id.seeds4);
                TV4.setText(generated_answer[3].getSeed().toString());
            }
        });

        Button submit_button = findViewById(R.id.submit_user_input);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer[] hints = user_attempt_checker(generated_answer, user_input);

                Fruit[] fruitsArray = new Fruit[4];

                for (int i=0; i<fruitsArray.length;i++) {
                    fruitsArray[i] = fruits.get(user_input[i]);
                }
                history.add(new Pair<>(fruitsArray,hints));
                fruitAdapter.notifyDataSetChanged();
            }
        });

    }

    private ArrayAdapter<CharSequence> defineSpinner(Spinner spinner, ArrayAdapter<CharSequence> adapter) {
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return adapter;
    }

    public Fruit[] generate_answer() {
        int inserted_fruit = 0;
        Fruit[] result = new Fruit[4];

        while (inserted_fruit != 4) {
            Random rnd = new Random();
            int proposition = rnd.nextInt(fruits.size());
            boolean isValid = true;

            for (int i = 0; i < result.length; i++) {
                if (result[i] != null) {
                    if (result[i].getName().equals(fruits.get(proposition).getName())) {
                        isValid = false;
                        break;
                    }
                }
            }

            if (isValid) {
                result[inserted_fruit] = fruits.get(proposition);
                inserted_fruit++;
            }
        }
        return result;
    }

    /**
     * Function who take a generated answer in argument and check if the attempt is ok
     * p_generated_answer : returned by generate_answer()
     * p_user_input : an array of string who represent the user answer
     * return :
     *
     * @return an array of integer
     */
    public Integer[] user_attempt_checker(Fruit[] p_generated_answer, String[] p_user_input) {
        Integer[] result = new Integer[4];
        // on boucle sur les resultats de l'utilisateur
        // si le fruit est absent on met 0 dans un tableau
        for (int i = 0; i < p_user_input.length; i++) {
            int checked_result = 0;
            for (int j = 0; j < p_generated_answer.length; j++) {
                if (p_user_input[i].equals(p_generated_answer[j].getName())) {
                    if (i == j) {
                        // si le fruit est présent et bien placé on met 2 dans un tableau
                        checked_result = 2;
                    } else {
                        // si le fruit est présent on met 1 dans un tableau
                        checked_result = 1;
                    }
                }
            }
            result[i] = checked_result;
        }
        // on fait un sort() descendant sur le tableau
        Arrays.sort(result, Collections.reverseOrder());
        // on retourne le tableau ex: [2,1,0,0]
        return result;
    }

    public String check_if_is_won(Integer[] user_attempt) {
        // on vérifie le tableau
        String result = "Je ne sais pas";
        Integer[] won = {2, 2, 2, 2};
        int available_attempt = 10;
        // si gagné => menu gagné
        if (Arrays.equals(user_attempt, won)) {
            result = "Gagné";
        }
        // sinon
        else {
            if (available_attempt > 0) {
                // si reste des essais => on refait une proposition
                result = "Essaie encore";
            } else {
                // sinon perdu
                result = "Perdu";
            }
        }
        return result;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        switch (parent.getId()) {
            case R.id.spinner1:
                user_input[0] = text;
                break;
            case R.id.spinner2:
                user_input[1] = text;
                break;
            case R.id.spinner3:
                user_input[2] = text;
                break;
            case R.id.spinner4:
                user_input[3] = text;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}