package com.example.agentetsp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;


public class RecyclerViews extends AppCompatActivity  {

    RecyclerView reciclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_views);

        reciclerView = findViewById(R.id.recyclertsp);
        reciclerView.setLayoutManager(new LinearLayoutManager(this ));


        myAdapter= new MyAdapter(this, getMyList());
        reciclerView.setAdapter(myAdapter);
    }

    private ArrayList<CarviewActivity> getMyList() {
        ArrayList<CarviewActivity> models = new ArrayList<>();

        CarviewActivity m = new CarviewActivity();
        m.setName(" Ernesto");
        m.setAdress("4ta cerrada de Nicolas Romero");
        m.setPhone("5514949559");
        m.setImg(R.drawable.contacto);
        models.add(m);

        m = new CarviewActivity();
        m.setName(" Daved");
        m.setAdress("San andres");
        m.setPhone("5559643125");
        m.setImg(R.drawable.contacto);
        models.add(m);

        m = new CarviewActivity();
        m.setName(" Martin");
        m.setAdress("san pedro");
        m.setPhone("5513857251");
        m.setImg(R.drawable.contacto);
        models.add(m);

        return models;
    }

}



