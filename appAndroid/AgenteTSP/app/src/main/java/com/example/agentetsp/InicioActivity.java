package com.example.agentetsp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InicioActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    Button btnGuardar, btnVer, btnRegresar;
    EditText textNom,edtAdress,textTele;
    TextView txtCoord;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        textNom = findViewById(R.id.txtNombre);
        edtAdress = (EditText) findViewById(R.id.eAddress);
        textTele = findViewById(R.id.txtTelefono);
        txtCoord=findViewById(R.id.textCoordenadas);
        request= Volley.newRequestQueue(this);

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
        String Direccion =edtAdress.getText(). toString();
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
            cargarWebService();


            new GetCoordinates().execute(edtAdress.getText().toString().replace(" ","+"));



            Toast.makeText(this, "Datos guardados correctamente "/*+ Nombre+" "+Direccion+" "+Telefono*/, Toast.LENGTH_SHORT).show();
        }



    }

    private void cargarWebService() {
        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        //String ip=getString(R.string.ip);

        String url="https://practica4basededatosavanzada.000webhostapp.com/ejemploBDRemota/wsJSONRegistroMovil.php?nombre="+textNom.getText().toString()+"&direccion="+edtAdress.getText(). toString()+"&telefono="+textTele.getText().toString();
        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"No se ha podido conectar"+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"Se ha registrado con exito",Toast.LENGTH_SHORT).show();
        progreso.hide();
        this.textNom.setText("");
        this.edtAdress.setText("");
        this.textTele.setText("");
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
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+address+
                        ",+CA&key="+BuildConfig.GoogleGeoCoding;
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


    public void Ver (View view){
        Intent V = new Intent(this,RecyclerViews.class);

        startActivity(V);
    }
}