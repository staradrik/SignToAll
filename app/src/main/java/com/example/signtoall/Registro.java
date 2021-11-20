package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


    String ip = "192.168.0.106:80"; //ip del host para ahorrar tiempo
    EditText txtNombre, txtApellido, editTextNumberSigned, txtCorreo, txtPass1, txtPass2 ;
    Button ButtonProfesor, ButtonEstudiante;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_main);

        txtNombre =(EditText) findViewById(R.id.txtNombre);
        txtApellido =(EditText) findViewById(R.id.txtApellido);
        editTextNumberSigned =(EditText) findViewById(R.id.txtDocumento);
        txtCorreo =(EditText) findViewById(R.id.txtCorreo);
        txtPass1 =(EditText) findViewById(R.id.txtPass1);
        txtPass2 =(EditText) findViewById(R.id.txtPass2);
        ButtonProfesor=(Button)findViewById (R.id.btnRegDoc);
        ButtonEstudiante=(Button)findViewById (R.id.btnRegEst);

        //evento clic agregar docente
        ButtonProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().toString().isEmpty() && !txtApellido.getText().toString().isEmpty() && !editTextNumberSigned.getText().toString().isEmpty() && !txtCorreo.getText().toString().isEmpty() && !txtPass1.getText().toString().isEmpty() && !txtPass2.getText().toString().isEmpty()) {
                    if (txtPass1.getText().toString().equals(txtPass2.getText().toString())){
                        ejecutarProfesor("http://" + ip + "/Sign_to_All/Insertar_profesor.php");
                    }else{
                        Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Algunos de los campos esta vacio", Toast.LENGTH_LONG).show();
                }
            }
        });

        //evento clic agregar estudiante
        ButtonEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().toString().isEmpty() && !txtApellido.getText().toString().isEmpty() && !editTextNumberSigned.getText().toString().isEmpty() && !txtCorreo.getText().toString().isEmpty() && !txtPass1.getText().toString().isEmpty() && !txtPass2.getText().toString().isEmpty()){
                    if (txtPass1.getText().toString().equals(txtPass2.getText().toString())){
                        ejecutarEstudiante("http://" + ip + "/Sign_to_All/Insertar_estudiante.php");
                    }else{
                        Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Algunos de los campos esta vacio", Toast.LENGTH_LONG).show();
                }
            }
        });
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