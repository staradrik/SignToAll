package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Seleccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
    }
    public void matema (View view){
        Intent i=new Intent(this, matematicas.class);

        startActivity(i);
    }
    public void espanol (View view){
        Intent i=new Intent(this, espanol.class);
        startActivity(i);
    }
}