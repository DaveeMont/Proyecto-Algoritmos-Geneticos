package com.example.agentetsp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    public ArrayList<CarviewActivity> getMyList() {
        BaseSQLiteOpenHelper admin = new BaseSQLiteOpenHelper(this, "TSP", null, 1);
        SQLiteDatabase Basededatos = admin.getReadableDatabase();

        Cursor cursor = Basededatos.rawQuery("SELECT * FROM TSP", null );


        ArrayList<CarviewActivity> tsp = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{
                CarviewActivity m = new CarviewActivity();
                tsp.add(new CarviewActivity());
                m.setName("Nombre");
                m.setAdress("Direccion");
                m.setPhone("Telefono");
                m.setImg(R.drawable.contacto);
            }while(cursor.moveToNext());
        }

        return tsp;
    }

}



