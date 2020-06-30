package com.example.agentetsp;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CarviewActivity  extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_carview);
        }
        private String Name,Adress,Phone;
        private int img;

        public String getName() {
                return Name;
        }

        public void setName(String name) {
                Name = name;
        }

        public String getAdress() {
                return Adress;
        }

        public void setAdress(String adress) {
                Adress = adress;
        }

        public String getPhone() {
                return Phone;
        }

        public void setPhone(String phone) {
                Phone = phone;
        }

        public int getImg() {
                return img;
        }

        public void setImg(int img) {
                this.img = img;
        }
/* TextView txtnombre,txtdireccion,txttelefono;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carview);
    }



    public void Regresar(View view){
        Intent R= new Intent(this, InicioActivity.class);
        startActivity(R);
    }*/
}