package com.example.signtoall;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InicioSesion extends AppCompatActivity {

    String ip = "192.168.0.106:80"; //ip del host para ahorrar tiempo
    EditText user, cont;
    String user1,cont1;
    Button btnIni_estu, btnIni_doce,btncer;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion_main);

        user =(EditText) findViewById(R.id.logCorreo);
        cont =(EditText) findViewById(R.id.logCont);

        btnIni_doce =(Button) findViewById(R.id.btnIngresarDoce);
        btnIni_estu =(Button) findViewById(R.id.btnIngresarEstu);
        btncer =(Button) findViewById(R.id.btnCerrar3);

        Recuperarpreferencias();
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        boolean sesion =preferences.getBoolean("sesion",false);
        if(sesion){

            user.setEnabled(false);
            cont.setEnabled(false);
            btnIni_estu.setEnabled(false);
            btnIni_doce.setEnabled(false);
        }else{
            btncer.setEnabled(false);
        }
    }

    public void Iniciar_Docente(View v){
        user1=user.getText().toString();
        cont1=cont.getText().toString();
        if(!user1.isEmpty() && !cont1.isEmpty()){
            Validar_Docente("http://" + ip + "/Sign_to_All/Login_profesor.php");
        }
        else {
            Toast.makeText(InicioSesion.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
        }

    }

    private void Validar_Docente(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Guardarpreferencias();
                    Limpiar();
                    Intent i = new Intent(getApplicationContext(), Crud.class);
                    startActivity(i);
                } else {
                    Toast.makeText(InicioSesion.this, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InicioSesion.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", user1);
                parametros.put("contrasena", cont1);
                return parametros;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    public void Iniciar_Estudiante(View v){
        user1=user.getText().toString();
        cont1=cont.getText().toString();
        if(!user1.isEmpty() && !cont1.isEmpty()){
            Validar_Estudiante("http://" + ip + "/Sign_to_All/Login_estudiante.php");
        }
        else {
            Toast.makeText(InicioSesion.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
        }

    }

    private void Validar_Estudiante(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Guardarpreferencias();
                    Limpiar();
                    Intent i = new Intent(getApplicationContext(),Seleccion.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(InicioSesion.this, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InicioSesion.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("correo",user1);
                parametros.put("contrasena",cont1);
                return parametros;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void Guardarpreferencias(){
        SharedPreferences Preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =Preferences.edit();

        editor.putString("correo",user1);
        editor.putString("contrasena",cont1);
        editor.putBoolean("sesion",true);
        editor.commit();
    }

    private  void Recuperarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        user.setText(preferences.getString("correo",""));
        cont.setText(preferences.getString("contrasena",""));
    }

    public  void  Cerrar(View v){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        Intent i = new Intent(getApplicationContext(),InicioSesion.class);
        startActivity(i);
        finish();
    }

    private void Limpiar(){
        user.setText("");
        cont.setText("");
    }
}