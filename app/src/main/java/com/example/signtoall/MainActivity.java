package com.example.signtoall;

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
   public void Registro (View view){
        Intent i=new Intent(this, Registro.class);
        startActivity(i);
    }
    public void InicioSesion (View view){
        Intent i=new Intent(this, InicioSesion.class);
        startActivity(i);
    }

}