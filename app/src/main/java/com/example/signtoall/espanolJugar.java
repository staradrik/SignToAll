package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class espanolJugar extends AppCompatActivity {

    TextView puntos,contador,vidas;
    ImageView imagen;
    EditText txtedit;
    Button btnconfirmar;

    String[] NombreAnimal={"pingúino","sapo","perro","oso","búho","gorila","ardilla","león","pajaro","tigre"};
    String[] NombreAnimal2={"pinguino","sapo","perro","oso","buho","gorila","ardilla","leon","pajaro","tigre"};
    String[] ImagenAnimal={"img1","img2","img3","img4","img5","img6","img7","img8","img9","img10"};

    int intpunto=0;
    int intvidas=3;
    int numerogenerado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espanol_jugar);

        puntos=(TextView)findViewById(R.id.txtPuntos);
        vidas=(TextView)findViewById(R.id.txtVidas);
        contador=(TextView)findViewById(R.id.Cuenta);
        imagen=(ImageView)findViewById(R.id.ImagenTablero);
        txtedit=(EditText)findViewById(R.id.editTextTextPersonName);
        btnconfirmar=(Button)findViewById(R.id.btnConfirmar);

        Toast toastP = Toast.makeText(getApplicationContext(), "Has perdido!!", Toast.LENGTH_LONG);
        Toast toastI = Toast.makeText(getApplicationContext(), "Incorrecto!!", Toast.LENGTH_LONG);

        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoconfirmar= txtedit.getText().toString().toLowerCase();
                if(intvidas==0){
                    toastP.show();
                    finish();
                }
                if(textoconfirmar.equals(NombreAnimal[numerogenerado])|textoconfirmar.equals(NombreAnimal2[numerogenerado])){
                    intpunto = intpunto + 1;
                    puntos.setText("Puntos: " + intpunto);
                    txtedit.setText("");
                    if(numerogenerado == NombreAnimal.length - 1){
                        Toast toastG = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                        toastG.show();
                        finish();
                    }else{
                        Toast toastC = Toast.makeText(getApplicationContext(), "Correcto!!", Toast.LENGTH_LONG);
                        toastC.show();
                        esperar1();
                    }
                }
                else {
                    intvidas = intvidas - 1;
                    vidas.setText("Vidas: " + intvidas);
                    toastI.show();
                    txtedit.setText("");
                    esperar2();
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
                txtedit.setHint("Ingrese el animal");
            }
        }.start();
    }
    void esperar1(){
        new CountDownTimer(2000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador.setText(""+(millisUntilFinished/1000 +1));
                btnconfirmar.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnconfirmar.setVisibility(View.VISIBLE);
                contador.setText("");
                numerogenerado = numerogenerado + 1;
                establecer_imagen(numerogenerado);
                txtedit.setHint("Ingrese el animal");
            }
        }.start();
    }
    void establecer_imagen(int numero){
        int id = getResources().getIdentifier(ImagenAnimal[numero],"drawable",getPackageName());
        imagen.setImageResource(id);
    }
}
