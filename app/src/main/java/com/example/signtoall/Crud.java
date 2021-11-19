package com.example.signtoall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class Crud extends AppCompatActivity {

    String ip = "192.168.0.106:80"; //ip del host para ahorrar tiempo
    EditText crudNombre, crudApellido, crudDocumento, crudCorreo;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        crudDocumento =(EditText) findViewById(R.id.txtDocumento);
        crudNombre =(EditText) findViewById(R.id.txtNombre);
        crudApellido =(EditText) findViewById(R.id.txtApellido);
        crudCorreo =(EditText) findViewById(R.id.txtCorreo);
    }

    public void Buscar(View v){
        buscarEstudiante ( "http://" + ip + "/Sign_to_All/Buscar_estudiante.php?documento="+crudDocumento.getText ()+"");
    }

    private void buscarEstudiante(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest ( URL, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length (); i++) {
                    try {
                        jsonObject = response.getJSONObject ( i );
                        crudNombre.setText ( jsonObject.getString ( "nombre" ) );
                        crudApellido.setText ( jsonObject.getString ( "apellido" ) );
                        crudCorreo.setText ( jsonObject.getString ( "correo" ) );
                    } catch (JSONException e) {
                        Toast.makeText ( getApplicationContext (), e.getMessage (), Toast.LENGTH_LONG ).show ();
                    }
                }
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText ( getApplicationContext (),"ERROR DE CONEXION",Toast.LENGTH_LONG ).show ();
            }
        }
        );
        requestQueue= Volley.newRequestQueue ( this );
        requestQueue.add ( jsonArrayRequest );
    }

    public void Eliminar(View v){
        eliminarEstudiante ( "http://" + ip + "/Sign_to_All/Eliminar_estudiante.php" );
    }

    private void eliminarEstudiante(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "EL ESTUDIANTE FUE ELIMINADO", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_LONG).show();
                Limpiar();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("documento",crudDocumento.getText().toString());

                return parametros;
            }
        };
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void Editar(View v){
        editarEstudiante ( "http://" + ip + "/Sign_to_All/Editar_estudiante.php" );
    }

    private void editarEstudiante(String URL){

        StringRequest stringRequest=new StringRequest( Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "El DATO SE HA MODIFICADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
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
                parametros.put("nombre",crudNombre.getText().toString());
                parametros.put("apellido",crudApellido.getText().toString());
                parametros.put("documento",crudDocumento.getText().toString());
                parametros.put("correo",crudCorreo.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public  void  Cerrar(View v){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        Intent i = new Intent(getApplicationContext(),InicioSesion.class);
        startActivity(i);
        finish();
    }

    private void Limpiar(){
        crudNombre.setText("");
        crudApellido.setText("");
        crudDocumento.setText("");
        crudCorreo.setText("");
    }
}