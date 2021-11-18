package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class espanol extends AppCompatActivity {

    Button btnJugar, btnRespuestas, bntAcerca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espanol);
        btnJugar =  (Button) findViewById(R.id.btnJugar);
        btnRespuestas=(Button)findViewById(R.id.btnRes);
        bntAcerca=(Button)findViewById(R.id.btnMas);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pagina2= new Intent(espanol.this,espanolJugar.class);
                startActivity(pagina2);
            }
        });
        btnRespuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagina3= new Intent(espanol.this,Respuesta.class);
                startActivity(pagina3);
            }
        });

    }
}