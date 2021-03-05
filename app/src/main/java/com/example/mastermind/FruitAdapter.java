package com.example.mastermind;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    // Store a member variable for the contacts
    private List<Pair<Fruit[],Integer[]>> m_fruits;
    private List<Integer> m_hints;

    public FruitAdapter(Context context, List<Pair<Fruit[],Integer[]>> fruits){
        m_fruits = fruits;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View fruitView = inflater.inflate(R.layout.fruit_items, parent, false);

        // Return a new holder instance
        ViewHolder fruitHolder = new ViewHolder(fruitView);
        return fruitHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int position) {
        Integer[] temp = m_fruits.get(position).second;
        // Set item views based on your views and data model
        TextView hint1 = holder.hint1;
        hint1.setText(String.format(Locale.getDefault(),"%d",m_fruits.get(position).second[0]));
        TextView hint2 = holder.hint2;
        hint2.setText(String.format(Locale.getDefault(),"%d",m_fruits.get(position).second[1]));
        TextView hint3 = holder.hint3;
        hint3.setText(String.format(Locale.getDefault(),"%d",m_fruits.get(position).second[2]));
        TextView hint4 = holder.hint4;
        hint4.setText(String.format(Locale.getDefault(),"%d",m_fruits.get(position).second[3]));

        ImageButton img1 = holder.img1;
        img1.setImageResource(m_fruits.get(position).first[0].getDrawableId());
        ImageButton img2 = holder.img2;
        img2.setImageResource(m_fruits.get(position).first[1].getDrawableId());
        ImageButton img3 = holder.img3;
        img3.setImageResource(m_fruits.get(position).first[2].getDrawableId());
        ImageButton img4 = holder.img4;
        img4.setImageResource(m_fruits.get(position).first[3].getDrawableId());
    }

    @Override
    public int getItemCount() {
        return m_fruits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton img1;
        public ImageButton img2;
        public ImageButton img3;
        public ImageButton img4;
        public TextView hint1;
        public TextView hint2;
        public TextView hint3;
        public TextView hint4;

        public ViewHolder(View itemView){
            super(itemView);
            hint1 = itemView.findViewById(R.id.TV1);
            hint2 = itemView.findViewById(R.id.TV2);
            hint3 = itemView.findViewById(R.id.TV3);
             hint4 = itemView.findViewById(R.id.TV4);
             img1 = itemView.findViewById(R.id.f1);
             img2 = itemView.findViewById(R.id.f2);
             img3 = itemView.findViewById(R.id.f3);
             img4 = itemView.findViewById(R.id.f4);
            }

        }







}
