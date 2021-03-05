package com.example.mastermind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitSpinnerAdapter extends ArrayAdapter<FruitItemChoice> {
    private Context context;
    private int srcId;
    private List<FruitItemChoice> fruitList;

    public FruitSpinnerAdapter(Context context, int resource, List<FruitItemChoice> objects) {
        super(context, resource, objects);
        this.context = context;
        this.srcId = resource;
        this.fruitList = objects;
    }
    @Override
    public  int getCount(){
        return  fruitList.size();
    }

    @NonNull
    @Override
    public FruitItemChoice getItem(int position){
        return  fruitList.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if(view == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(srcId, parent ,false);

                FruitItemChoice fruitItemChoice = fruitList.get(position);
                if (fruitItemChoice != null){
                    TextView name = view.findViewById(R.id.textView2);
                    ImageView imageView = view.findViewById(R.id.imageView4);
                    name.setText(fruitItemChoice.getFruitName());
                    imageView.setImageResource(fruitItemChoice.getFruitImage());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if(view == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(srcId, parent ,false);

                FruitItemChoice fruitItemChoice = fruitList.get(position);
                if (fruitItemChoice != null){
                    TextView name = view.findViewById(R.id.textView2);
                    ImageView imageView = view.findViewById(R.id.imageView4);
                    name.setText(fruitItemChoice.getFruitName());
                    imageView.setImageResource(fruitItemChoice.getFruitImage());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

}