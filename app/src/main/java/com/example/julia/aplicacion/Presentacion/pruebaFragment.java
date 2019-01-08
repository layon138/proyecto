package com.example.julia.aplicacion.Presentacion;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.julia.aplicacion.Negocio.Usuario;
import com.example.julia.aplicacion.Persistencia.Conexion;
import com.example.julia.aplicacion.R;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pruebaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pruebaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pruebaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Conexion con;

    JSONArray ja;

    // TODO: Rename and change types of parameters
    //fragmento para el perfil del usuario
    private String mParam1;
    private String mParam2;
    private TextView t_nom,t_apl,t_ced,t_tel,t_dir,t_rh,t_fe,t_ge,t_eps;
    private ImageView foto;
    private Usuario usu;
    private String texto;
    private String rutaimagen;
    private OnFragmentInteractionListener mListener;

    public pruebaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pruebaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static pruebaFragment newInstance(String param1, String param2) {
        pruebaFragment fragment = new pruebaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    //se encarga de cargar los datos del usuario
    public void cargardatos(String url){
        Toast.makeText(getActivity().getApplicationContext(), "Bienvenido: "+url, Toast.LENGTH_SHORT).show();
        Log.i("url",url);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    response=response.replace("][",",");
                    ja = new JSONArray(response);
                    usu=new Usuario();
                    usu.setCedula(ja.getString(0));
                    usu.setNombre(ja.getString(1));
                    usu.setApellido(ja.getString(2));
                    usu.setTelefono(ja.getString(3));
                    usu.setDireccion(ja.getString(4));
                    usu.setFec_nac(ja.getString(5));
                    usu.setSexo(ja.getString(6));
                    usu.setEps(ja.getString(7));
                    usu.setRh(ja.getString(8));
                    rutaimagen=ja.getString(9);
                    t_ced.setText(usu.getCedula());
                    t_nom.setText(usu.getNombre());
                    t_apl.setText(usu.getApellido());
                    t_tel.setText(usu.getTelefono());
                    t_dir.setText(usu.getDireccion());
                    t_fe.setText(usu.getFec_nac());
                    t_ge.setText(usu.getSexo());
                    t_eps.setText(usu.getEps());
                    t_rh.setText(usu.getRh());
                    cargarimagen();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), "El usaurio no existe", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "falla:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

    }

    //se encarga de cargar la foto del usuario
    public void cargarimagen(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        ImageRequest imageRequest=new ImageRequest(con.mostrarimagen(rutaimagen), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                foto.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "el error es:"+error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(imageRequest);
    }


    //inicializa las variables
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prueba, container, false);
        t_ced=(TextView)v.findViewById(R.id.tv_ced);
        t_nom=(TextView)v.findViewById(R.id.tv_nom);
        t_apl=(TextView)v.findViewById(R.id.tv_ap);
        t_tel=(TextView)v.findViewById(R.id.tv_tl);
        t_dir=(TextView)v.findViewById(R.id.tv_dr);
        t_rh=(TextView)v.findViewById(R.id.tv_rh);
        t_fe=(TextView)v.findViewById(R.id.tv_fn);
        t_ge=(TextView)v.findViewById(R.id.tv_gn);
        t_eps=(TextView)v.findViewById(R.id.tv_eps);
        foto=(ImageView)v.findViewById(R.id.iv_foto);
        con=new Conexion();
        cargardatos(con.mostrarusuario(getArguments().getString("usuario")));
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
