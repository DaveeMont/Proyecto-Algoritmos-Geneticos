package com.example.agentetsp;

import androidx.appcompat.app.AppCompatActivity;

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

public class InicioActivity extends AppCompatActivity {


    Button btnGuardar, btnVer, btnRegresar;
    EditText edtAdress;
    TextView txtCoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

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
    }

    //Método para el boton anterior

    public void Mainn(View view){
        Intent mainn= new Intent(this, MainActivity.class);
        startActivity(mainn);
    }

    //Método del boton guardar

    public void Guardar(View view){

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