package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_main);

        txtNombre =(EditText) findViewById(R.id.crudNombre);
        txtApellido =(EditText) findViewById(R.id.crudApellido);
        editTextNumberSigned =(EditText) findViewById(R.id.crudDocumento);
        txtCorreo =(EditText) findViewById(R.id.crudCorreo);
        txtPass1 =(EditText) findViewById(R.id.txtPass1);
        txtPass2 =(EditText) findViewById(R.id.txtPass2);
    }

    public void Agregar_Profesor(View v){
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String ident = editTextNumberSigned.getText().toString();
        String correo = txtCorreo.getText().toString();
        String cont = txtPass1.getText().toString();
        String cont_confir = txtPass2.getText().toString();

        if (!nombre.isEmpty() && !apellido.isEmpty() && !ident.isEmpty() && !correo.isEmpty() && !cont.isEmpty() && !cont_confir.isEmpty()){
            if (txtPass1.getText().toString().equals(txtPass2.getText().toString())){
                ejecutarProfesor("http://192.168.0.6/Sign_to_All/Insertar_profesor.php");
                Intent i=new Intent(this, InicioSesion.class);
                startActivity(i);
            }
            else{
                Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Debe ingresar todo los campos", Toast.LENGTH_LONG).show();
        }

    }

    public void Agregar_Estudiante(View v){
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String ident = editTextNumberSigned.getText().toString();
        String correo = txtCorreo.getText().toString();
        String cont = txtPass1.getText().toString();
        String cont_confir = txtPass2.getText().toString();

        if (!nombre.isEmpty() && !apellido.isEmpty() && !ident.isEmpty() && !correo.isEmpty() && !cont.isEmpty() && !cont_confir.isEmpty()){
            if (txtPass1.getText().toString().equals(txtPass2.getText().toString())){
                ejecutarEstudiante("http://192.168.0.6/Sign_to_All/Insertar_estudiante.php");
                Intent i=new Intent(this, InicioSesion.class);
                startActivity(i);
            }
            else{
                Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Debe ingresar todo los campos", Toast.LENGTH_LONG).show();
        }

    }

    private void ejecutarEstudiante(String URL){

        StringRequest stringRequest=new StringRequest( Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_LONG).show();
                Limpiar();
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
                Limpiar();
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

    private void Limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        editTextNumberSigned.setText("");
        txtCorreo.setText("");
        txtPass1.setText("");
        txtPass2.setText("");
    }
}