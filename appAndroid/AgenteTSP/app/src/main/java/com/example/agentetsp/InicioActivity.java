package com.example.agentetsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {

    EditText textNom,textDirr,textTele,id ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        textNom = findViewById(R.id.txtNombre);
        textDirr= findViewById(R.id.txtAddress);
        textTele = findViewById(R.id.txtTelefono);

    }

    //Método para el boton anterior

    public void Mainn(View view){
        Intent mainn= new Intent(this, MainActivity.class);
        startActivity(mainn);
    }

    //Método del boton guardar

    public void Guardar(View view){

        BaseSQLiteOpenHelper admin = new BaseSQLiteOpenHelper(this, "TSP", null, 1);
        SQLiteDatabase Basededatos = admin.getReadableDatabase();

        int id = 0;
        String Nombre= textNom.getText().toString();
        String Direccion =textDirr.getText(). toString();
        String  Telefono =textTele.getText().toString();

        if(Nombre.isEmpty()){

            Toast.makeText(this,"Ingresa el Nombre",Toast.LENGTH_LONG).show();

        }else if(Direccion.isEmpty()){

            Toast.makeText(this,"Ingresa la Dirreccion",Toast.LENGTH_LONG).show();

        } else if (Telefono.isEmpty()) {

            Toast.makeText(this, "Ingresa el Teléfono ", Toast.LENGTH_LONG).show();

        }else{

            ContentValues registro = new ContentValues();
            registro.put("id",id);
            registro.put("Nombre",Nombre);
            registro.put("Dirreccion",Direccion);
            registro.put("Telefono", Telefono);


            Basededatos.insert("tsp",null,registro);
            Basededatos.close();


            this.textNom.setText("");
            this.textDirr.setText("");
            this.textTele.setText("");


            Toast.makeText(this, "Datos guardados correctamente "/*+ Nombre+" "+Direccion+" "+Telefono*/, Toast.LENGTH_SHORT).show();
        }



    }


    public void Mostrar(View view){
        Intent mostrar= new Intent(this,RecyclerViews.class);
        startActivity(mostrar);
    }
}