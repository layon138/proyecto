package com.example.julia.aplicacion.Presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.julia.aplicacion.Persistencia.Conexion;
import com.example.julia.aplicacion.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Inicio_Activity extends AppCompatActivity {

    //se llaman las variables que vienen del xml
    private Button bt_ingresar;
    private EditText edi_usuario,edi_contra;
    private ImageView iv_logo;
    //se crean los objetos para llamar la base de datos
    private Conexion con;
    private JSONArray ja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv_logo=(ImageView)findViewById(R.id.iv_logo);
        iv_logo.setImageResource(R.drawable.app);
        edi_usuario=(EditText)findViewById(R.id.edi_usuario);
        edi_contra=(EditText)findViewById(R.id.edi_contra);
        bt_ingresar=(Button)findViewById(R.id.bt_ingresar);
        bt_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con=new Conexion();
                Toast.makeText(getApplicationContext(), "Bienvenido: "+con.entrarapp(edi_usuario.getText().toString()), Toast.LENGTH_SHORT).show();
                Consultapassword(con.entrarapp(edi_usuario.getText().toString()));
            }
        });
    }

    //se verifica la cedula de la base de datos para permitir entrar a la aplicacion
    public void Consultapassword(String url){
        Toast.makeText(getApplicationContext(), "Bienvenido: "+url, Toast.LENGTH_SHORT).show();
        Log.i("url",url);
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ja = new JSONArray(response);
                    String contra = ja.getString(0);
                    Toast.makeText(getApplicationContext(), "Bienvenido: "+contra, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Principal_Activity.class);
                    intent.putExtra("usuario",edi_usuario.getText().toString());
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "El usaurio no existe", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "falla:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}
