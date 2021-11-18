package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class espanolJugar extends AppCompatActivity {

    TextView puntos,contador,vidas,txtcorrecto,txtincorrecto;
    ImageView imagen;
    EditText txtedit;
    Button btnconfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espanol_jugar);
    }
}