package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    EditText txtNombre, txtApellido, editTextNumberSigned, txtCorreo, txtPass1, txtPass2 ;
    RadioButton radioButtonProfesor, radioButtonEstudiante;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_main);

        txtNombre =(EditText) findViewById(R.id.txtNombre);
        txtApellido =(EditText) findViewById(R.id.txtApellido);
        editTextNumberSigned =(EditText) findViewById(R.id.editTextNumberSigned);
        txtCorreo =(EditText) findViewById(R.id.txtCorreo);
        txtPass1 =(EditText) findViewById(R.id.txtPass1);
        txtPass2 =(EditText) findViewById(R.id.txtPass2);
    }

    public void Agregar(View v){
        ejecutarProfesor("http://192.168.0.6/Sign_to_All/Insertar_profesor.php");
    }

    private void ejecutarEstudiante(String URL){

        StringRequest stringRequest=new StringRequest( Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("nombre",txtNombre.getText().toString());
                parametros.put("apellido",txtApellido.getText().toString());
                parametros.put("documento",editTextNumberSigned.getText().toString());
                parametros.put("correo",txtCorreo.getText().toString());
                parametros.put("contrasena",txtPass1.getText().toString());
                parametros.put("confir_contrasena",txtPass2.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void ejecutarProfesor(String URL){

        StringRequest stringRequest=new StringRequest( Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("nombre",txtNombre.getText().toString());
                parametros.put("apellido",txtApellido.getText().toString());
                parametros.put("documento",editTextNumberSigned.getText().toString());
                parametros.put("correo",txtCorreo.getText().toString());
                parametros.put("contrasena",txtPass1.getText().toString());
                parametros.put("confir_contrasena",txtPass2.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}