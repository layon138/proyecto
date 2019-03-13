package com.example.julia.aplicacion.Presentacion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.julia.aplicacion.Negocio.Persona;
import com.example.julia.aplicacion.Negocio.Usuario;
import com.example.julia.aplicacion.Persistencia.Conexion;
import com.example.julia.aplicacion.R;

public class Principal_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,pruebaFragment.OnFragmentInteractionListener,fragmentomedidas.OnFragmentInteractionListener,rutina_fragmento.OnFragmentInteractionListener,foto_fragmento.OnFragmentInteractionListener,Dieta_Fragment.OnFragmentInteractionListener {
        private String clave;
        private Usuario usu=null;
        private TextView imagen;
        Conexion con;
    ImageView icono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras=getIntent().getExtras();
        usu=(Usuario) extras.getSerializable("usuario");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        con=new Conexion();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.textito);
        icono=(ImageView) hView.findViewById(R.id.imageperfil);
        nav_user.setText(usu.getNombre());
        Toast.makeText(getApplicationContext(), ""+con.mostrarimagen(usu.getFotoperfil()), Toast.LENGTH_SHORT).show();
        cargarimagen(usu.getFotoperfil());
        navigationView.setNavigationItemSelectedListener(this);
        //Toast.makeText(getApplicationContext(), ""+extras.getString("usuario"), Toast.LENGTH_SHORT).show();
    }

    public void cargarimagen(String rutaimagen){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        ImageRequest imageRequest=new ImageRequest(con.mostrarimagen(rutaimagen), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                icono.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "el error es:"+error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(imageRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//se seleccionan el menu a abrir
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment f=null;
        Bundle args=new Bundle();
        boolean Fragmentoelegido=false;
        if (id == R.id.nav_camera) {
            args.putSerializable("usuario", usu);
            f=new pruebaFragment();
            Fragmentoelegido=true;
        } else if (id == R.id.nav_gallery) {
            args.putString("usuario", usu.getCedula());
            f=new fragmentomedidas();
            Fragmentoelegido=true;
        } else if (id == R.id.nav_slideshow) {
            args.putInt("usuario", usu.getRutina());
            f=new rutina_fragmento();
            Fragmentoelegido=true;
        } else if (id == R.id.nav_manage) {
            //args.putString("usuario", usu.getCedula());
            f=new Dieta_Fragment();
            Fragmentoelegido=true;
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(getApplicationContext(),Inicio_Activity.class);
            startActivity(intent);
            finish();
        }
        if(Fragmentoelegido){
            f.setArguments(args);
            FragmentManager fragmentManager =getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.prueba,f).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
