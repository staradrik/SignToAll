package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class matematicas extends AppCompatActivity {

    //variables
    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06,imb07,imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15;
    ImageButton [] tablero=new ImageButton[16];
    Button btnReinciar, btnSalir;
    TextView txtPuntos;
    int puntuacion, aciertos;

    //imagenes
    int [] imagenes;
    int fondo;

    //variables del juego
    ArrayList<Integer> ArrayDesordenado;
    ImageButton primero;
    int numeroprimero, numerosegundo;
    boolean bloqueo=false;
    final Handler handler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematicas);
        init();
    }

    private void cargartablero(){
        imb00 = findViewById(R.id.boton00);
        imb01 = findViewById(R.id.boton01);
        imb02 = findViewById(R.id.boton02);
        imb03 = findViewById(R.id.boton03);
        imb04 = findViewById(R.id.boton04);
        imb05 = findViewById(R.id.boton05);
        imb06 = findViewById(R.id.boton06);
        imb07 = findViewById(R.id.boton07);
        imb08 = findViewById(R.id.boton08);
        imb09 = findViewById(R.id.boton09);
        imb10 = findViewById(R.id.boton10);
        imb11 = findViewById(R.id.boton11);
        imb12 = findViewById(R.id.boton12);
        imb13 = findViewById(R.id.boton13);
        imb14 = findViewById(R.id.boton14);
        imb15 = findViewById(R.id.boton15);

        tablero [0]=imb00;
        tablero [1]=imb01;
        tablero [2]=imb02;
        tablero [3]=imb03;
        tablero [4]=imb04;
        tablero [5]=imb05;
        tablero [6]=imb06;
        tablero [7]=imb07;
        tablero [8]=imb08;
        tablero [9]=imb09;
        tablero [10]=imb10;
        tablero [11]=imb11;
        tablero [12]=imb12;
        tablero [13]=imb13;
        tablero [14]=imb14;
        tablero [15]=imb15;
    }
    private void cargarbotones(){
        btnReinciar=findViewById(R.id.btnReiniciar);
        btnSalir=findViewById(R.id.btnsalirM);

        //reiniciar
        btnReinciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                init();
            }
        });
        //salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void cargartexto(){
        txtPuntos=findViewById(R.id.txtPuntaje);
        puntuacion=0;
        aciertos=0;
        txtPuntos.setText("Puntuación: "+puntuacion);
    }
    private void cargarimagenes(){
        imagenes=new int[]{
                R.drawable.uno,
                R.drawable.dos,
                R.drawable.tres,
                R.drawable.cuatro,
                R.drawable.cinco,
                R.drawable.seis,
                R.drawable.siete,
                R.drawable.ocho,
        };
        fondo = R.drawable.fondo;
    }
    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result=new ArrayList<Integer>();
        for(int i=0; i<longitud*2;i++){
            result.add(i % longitud);
        }
        Collections.shuffle(result);
       // System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void comprobar(int i, final ImageButton imgb){
        if(primero == null){
            primero = imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[ArrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numeroprimero = ArrayDesordenado.get(i);
        } else {
            bloqueo = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[ArrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numerosegundo = ArrayDesordenado.get(i);
            if(numeroprimero == numerosegundo){
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                txtPuntos.setText("Puntuación: " + puntuacion);
                if(aciertos == imagenes.length){
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        bloqueo = false;
                        primero = null;
                        puntuacion--;
                        txtPuntos.setText("Puntuación: " + puntuacion);
                    }
                }, 1000);
            }
        }
    }

    private void init(){
        cargartablero();
        cargarbotones();
        cargartexto();
        cargarimagenes();
        ArrayDesordenado=barajar(imagenes.length);

        for (int i=0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[ArrayDesordenado.get(i)]);
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<tablero.length; i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //tablero[i].setImageResource(imagenes[ArrayDesordenado.get(i)]);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 1000);
        for(int i=0; i<tablero.length; i++) {
           final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo)
                        comprobar(j, tablero[j]);
                }
            });
        }
    }

}