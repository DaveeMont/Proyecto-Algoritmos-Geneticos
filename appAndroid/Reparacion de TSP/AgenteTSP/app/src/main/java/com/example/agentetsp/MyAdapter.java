package com.example.agentetsp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder>{

    Context c;
    ArrayList<CarviewActivity> models;

    public MyAdapter(Context c, ArrayList<CarviewActivity> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_carview,null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myholder, int i) {

        myholder.Name.setText(models.get(i).getName());//i es la posicion
        myholder.Adress.setText(models.get(i).getAdress());
        myholder.Phone.setText(models.get(i).getPhone());
        myholder.imageView.setImageResource(models.get(i).getImg());//se usa la imgen dada
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
