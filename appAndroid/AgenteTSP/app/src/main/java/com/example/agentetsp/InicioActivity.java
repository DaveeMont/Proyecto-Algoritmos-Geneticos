package com.example.agentetsp;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
=======
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
>>>>>>> cb715e59651cd7d0bdf6d230e493fbefff1d306e

public class InicioActivity extends AppCompatActivity {

    EditText textNom,textDirr,textTele,id ;


    Button btnGuardar, btnVer, btnRegresar;
    EditText edtAdress;
    TextView txtCoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
<<<<<<< HEAD
        textNom = findViewById(R.id.txtNombre);
        textDirr= findViewById(R.id.txtAddress);
        textTele = findViewById(R.id.txtTelefono);

=======

        btnGuardar = (Button) findViewById(R.id.bGuardar);
        btnVer = (Button) findViewById(R.id.bVer);
        btnRegresar = (Button) findViewById(R.id.bRegresa);
        edtAdress = (EditText) findViewById(R.id.eAddress);
        txtCoord = (TextView) findViewById(R.id.txtCoordenadas);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCoordinates().execute(edtAdress.getText().toString().replace(" ","+"));
            }
        });
>>>>>>> cb715e59651cd7d0bdf6d230e493fbefff1d306e
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

    private class GetCoordinates extends AsyncTask<String,Void,String> {
        ProgressDialog dialog = new ProgressDialog(InicioActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Porfavor espere...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response;
            try{
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                response = http.getHttpData(url);
                return response;
            }catch (Exception ex){

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lat").toString();
                String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lng").toString();

                txtCoord.setText(String.format("Coordenadas: %s / %s ",lat,lng));
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}