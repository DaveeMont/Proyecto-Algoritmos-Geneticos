package com.example.agentetsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InicioActivity extends AppCompatActivity {


    Button btnGuardar;
    EditText edtAdress;
    TextView txtCoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnGuardar = (Button) findViewById(R.id.button3);
    }

    //Método para el boton anterior

    public void Mainn(View view){
        Intent mainn= new Intent(this, MainActivity.class);
        startActivity(mainn);
    }
}