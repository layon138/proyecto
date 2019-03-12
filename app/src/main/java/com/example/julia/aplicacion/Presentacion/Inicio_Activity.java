package com.example.julia.aplicacion.Presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.julia.aplicacion.Negocio.Persona;
import com.example.julia.aplicacion.Negocio.Usuario;
import com.example.julia.aplicacion.Persistencia.Conexion;
import com.example.julia.aplicacion.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;

public class Inicio_Activity extends AppCompatActivity {

    //se llaman las variables que vienen del xml
    private Button bt_ingresar;
    private EditText edi_usuario,edi_contra;
    private TextView tex_usuario,tex_contra;
    private ImageView iv_logo;
    private LinearLayout pantalla;
    private Usuario usu;
    //se crean los objetos para llamar la base de datos
    private Conexion con;
    private JSONArray ja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iniciarpantalla();
        bt_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con=new Conexion();
                Consultapassword(con.entrarapp(edi_usuario.getText().toString(),edi_contra.getText().toString()));
            }
        });
    }

    //Se encarga de Iniciar los objetos del XML y de acomodarlos al tamaño de la pantalla
    public void iniciarpantalla(){
        Display display = getWindowManager().getDefaultDisplay();
        int ancho = display.getWidth();
        int alto= display.getHeight();
        iv_logo=(ImageView)findViewById(R.id.iv_logo);
        iv_logo.setImageResource(R.drawable.app);
        edi_usuario=(EditText)findViewById(R.id.edi_usuario);
        bt_ingresar=(Button)findViewById(R.id.bt_ingresar);
        edi_contra=(EditText)findViewById(R.id.edi_contra);
        tex_usuario=findViewById(R.id.tex_usuario);
        tex_contra=findViewById(R.id.tex_contra);
        iv_logo.getLayoutParams().width=((ancho/5)*3);
        iv_logo.getLayoutParams().height=(alto/3);
        edi_usuario.getLayoutParams().width=((ancho/5)*3);
        edi_contra.getLayoutParams().width=((ancho/5)*3);
        bt_ingresar.getLayoutParams().width=ancho/3;
        tex_usuario.getLayoutParams().width=((ancho/5)*3);
        tex_contra.getLayoutParams().width=((ancho/5)*3);

    }


    //se verifica la cedula de la base de datos para permitir entrar a la aplicacion
    public void Consultapassword(String url){
        Log.i("url",url);
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ja = new JSONArray(response);
                    usu=new Usuario();
                    usu.setCedula(ja.getString(0));
                    usu.setNombre(ja.getString(1));
                    usu.setApellido(ja.getString(2));
                    usu.setFotoperfil(ja.getString(4));

                    Intent intent = new Intent(getApplicationContext(),Principal_Activity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("usuario", usu);
                    Toast.makeText(getApplicationContext(), "Bienvenido: "+usu.getNombre(), Toast.LENGTH_SHORT).show();
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "CEDULA O CONTRASEÑA INVALIDOS", Toast.LENGTH_SHORT).show();
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
