package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class espanolJugar extends AppCompatActivity {

    TextView puntos,contador,vidas,txtcorrecto,txtincorrecto;
    ImageView imagen;
    EditText txtedit;
    Button btnconfirmar;

    String[] NombreAnimal={"pingúino","sapo","perro","oso","búho","gorila","ardilla","león","pajaro","tigre"};
    String[] NombreAnimal2={"pinguino","Sapo","Perro","Oso","Búho","Gorila","Ardilla","Leon","Pajaro","Tigre"};
    String[] ImagenAnimal={"img1","img2","img3","img4","img5","img6","img7","img8","img9","img10"};

    int intpunto=0;
    int intvidas=3;
    int numerogenerado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espanol_jugar);

        txtcorrecto=(TextView)findViewById(R.id.txtcorrecto);
        txtincorrecto=(TextView)findViewById(R.id.txtincorrecto);
        puntos=(TextView)findViewById(R.id.txtPuntos);
        vidas=(TextView)findViewById(R.id.txtVidas);
        contador=(TextView)findViewById(R.id.Cuenta);
        imagen=(ImageView)findViewById(R.id.ImagenTablero);
        txtedit=(EditText)findViewById(R.id.editTextTextPersonName);
        btnconfirmar=(Button)findViewById(R.id.btnConfirmar);


        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoconfirmar= txtedit.getText().toString().toLowerCase();
                if(textoconfirmar.equals(NombreAnimal[numerogenerado])|textoconfirmar.equals(NombreAnimal2[numerogenerado])){
                    txtcorrecto.setVisibility(View.VISIBLE);
                    intpunto = intpunto + 1;
                    puntos.setText("Puntos: " + intpunto);
                    esperar1();
                }
                else {
                    txtincorrecto.setVisibility(View.VISIBLE);
                    intvidas = intvidas - 1;
                    vidas.setText("Vidas: " + intvidas);
                    esperar2();
                }
                if(intvidas==0){
                    finish();
                }
            }
        });
    }
    void esperar2() {
        new CountDownTimer(2000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnconfirmar.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnconfirmar.setVisibility(View.VISIBLE);
                txtincorrecto.setVisibility(View.INVISIBLE);
                txtedit.setText("");
                txtedit.setHint("Ingrese el animal");
            }
        }.start();
    }
    void esperar1(){
        new CountDownTimer(4000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador.setText(""+(millisUntilFinished/1000 +1));
                btnconfirmar.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnconfirmar.setVisibility(View.VISIBLE);
                numerogenerado = numerogenerado + 1;
                contador.setText("");
                establecer_imagen(numerogenerado);
                txtcorrecto.setVisibility(View.INVISIBLE);
                txtedit.setText("");
                txtedit.setHint("Ingrese el animal");
            }
        }.start();
    }
    void establecer_imagen(int numero){
        int id = getResources().getIdentifier(ImagenAnimal[numero],"drawable",getPackageName());
        imagen.setImageResource(id);
    }
}
