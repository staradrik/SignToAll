package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadEspanol extends AppCompatActivity {


        Button btnJugar, btnRespuestas;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_actividad_espanol);
            btnJugar =  (Button) findViewById(R.id.btnJugar);
            btnRespuestas=(Button)findViewById(R.id.btnRes);


            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pagina2= new Intent(com.example.signtoall.ActividadEspanol.this,espanolJugar.class);
                    startActivity(pagina2);
                }
            });
            btnRespuestas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pagina3= new Intent(com.example.signtoall.ActividadEspanol.this,Respuesta.class);
                    startActivity(pagina3);
                }
            });

        }
    }