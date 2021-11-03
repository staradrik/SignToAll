package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Crud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
    }
    public void irlistado(View view){
        Intent i=new Intent(this, Listado.class);

        startActivity(i);
    }
}