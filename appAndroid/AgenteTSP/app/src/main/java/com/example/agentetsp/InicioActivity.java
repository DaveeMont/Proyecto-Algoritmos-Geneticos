package com.example.agentetsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    //Método para el boton anterior

    public void Mainn(View view){
        Intent mainn= new Intent(this, MainActivity.class);
        startActivity(mainn);
    }
}