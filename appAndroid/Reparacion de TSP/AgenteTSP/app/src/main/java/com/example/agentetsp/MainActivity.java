package com.example.agentetsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        // Metodo con ésto se abre la otra actvivity de inicio

    public void Inicio(View view){
        Intent inicio= new Intent(this, InicioActivity.class);
        startActivity(inicio);
    }

    public void Salir (View view){
        finish();
        System.exit(0);
    }
}