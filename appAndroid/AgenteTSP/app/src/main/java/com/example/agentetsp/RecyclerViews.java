package com.example.agentetsp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jgap.IChromosome;

import java.util.ArrayList;


public class RecyclerViews extends AppCompatActivity  {

    RecyclerView reciclerView;
    MyAdapter myAdapter;
    TextView text,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_views);

        reciclerView = findViewById(R.id.recyclertsp);
        reciclerView.setLayoutManager(new LinearLayoutManager(this ));
        text=findViewById(R.id.tView);
        text2=findViewById(R.id.tView2);



        myAdapter= new MyAdapter(this, getMyList());
        reciclerView.setAdapter(myAdapter);


        try {
            Mapa t = new Mapa();
            IChromosome optimal = t.findOptimalPath(null);
            text.setText("Solución: "+optimal);
            text2.setText("Score: "+(Integer.MAX_VALUE / 2 - optimal.getFitnessValue()));
            System.out.println("Solution: ");
            System.out.println(optimal);
            System.out.println(optimal.toString());
            System.out.println("Score " +
                    (Integer.MAX_VALUE / 2 - optimal.getFitnessValue()));

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


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



