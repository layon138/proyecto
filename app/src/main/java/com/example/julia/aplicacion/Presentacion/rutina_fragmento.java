package com.example.julia.aplicacion.Presentacion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.julia.aplicacion.Negocio.Ejercicio;
import com.example.julia.aplicacion.Persistencia.Conexion;
import com.example.julia.aplicacion.R;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.Locale;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link rutina_fragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link rutina_fragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rutina_fragmento extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tv_ser,tv_ediser,tv_rep,tv_edirep,tv_nom,tv_mus,tv_edimus,tv_edinom,text_view_countdown;
    private Button b_listo;
    private ImageView ejercicio;
    private ArrayList<Ejercicio> rutina;
    private JSONArray ja;
    private Conexion con;
    private  long START_TIME_IN_MILLIS = 5000;
    private int numserie=0;
    private int numejercio=0;
    private int tamrutina=0;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private View v;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public rutina_fragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rutina_fragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static rutina_fragmento newInstance(String param1, String param2) {
        rutina_fragmento fragment = new rutina_fragmento();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_rutina_fragmento, container, false);
        con=new Conexion();
        rutina=new ArrayList<Ejercicio>();
        iniciarpantalla();
        Glide.with(getActivity()).load("http://m.gifmania.com.mx/Gifs-Animados-Mensajes/Gif-Animadas-Iniciar/Inicio-77644.gif").into(ejercicio);


        final int a=0;
        final int c=0;
        if(b_listo.getVisibility()== View.INVISIBLE){
            ejercicio.setClickable(true);
            ejercicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    visibilidadobjetos(false);
                    cargarrutina(con.mostrarrutina(getArguments().getString("usuario")));
                }
            });
            b_listo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        resetTimer();
                        visibilidadobjetos(true);
                        text_view_countdown.setVisibility(View.VISIBLE);
                        text_view_countdown.setTextSize(100);
                        startTimer();
                }
            });

        }
        return v;
    }

    public void iniciarpantalla(){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int ancho = display.getWidth();
        int alto= display.getHeight();
        ejercicio=(ImageView)v.findViewById(R.id.iv_ejercicio);
        text_view_countdown=(TextView)v.findViewById(R.id.text_view_countdown);
        tv_rep=(TextView)v.findViewById(R.id.tv_rep);
        tv_edirep=(TextView)v.findViewById(R.id.tv_edrep);
        tv_ser=(TextView)v.findViewById(R.id.tv_ser);
        tv_ediser=(TextView)v.findViewById(R.id.tv_ediser);
        tv_edinom=(TextView)v.findViewById(R.id.tv_edieje);
        tv_edimus=(TextView)v.findViewById(R.id.tv_edimus);
        tv_mus=(TextView)v.findViewById(R.id.tv_MUS);
        tv_nom=(TextView)v.findViewById(R.id.tv_eje);
        b_listo=(Button)v.findViewById(R.id.b_listo);
        ejercicio.getLayoutParams().height=(alto/5)*3;
        tv_edinom.getLayoutParams().width=(ancho/4);
        tv_nom.getLayoutParams().width=(ancho/4);
        tv_rep.getLayoutParams().width=(ancho/4);
        tv_edirep.getLayoutParams().width=(ancho/4);
        tv_ser.getLayoutParams().width=(ancho/4);
        tv_ediser.getLayoutParams().width=(ancho/4);
        tv_mus.getLayoutParams().width=(ancho/4);
        tv_edimus.getLayoutParams().width=(ancho/4);
        b_listo.getLayoutParams().width=((ancho/5)*3);
    }

    public void visibilidadobjetos(boolean validar){
        if(validar){
            b_listo.setVisibility(View.INVISIBLE);
            tv_edirep.setVisibility(View.INVISIBLE);
            tv_rep.setVisibility(View.INVISIBLE);
            tv_ser.setVisibility(View.INVISIBLE);
            tv_ediser.setVisibility(View.INVISIBLE);
            tv_edimus.setVisibility(View.INVISIBLE);
            tv_edinom.setVisibility(View.INVISIBLE);
            tv_mus.setVisibility(View.INVISIBLE);
            tv_nom.setVisibility(View.INVISIBLE);
        }else{
            b_listo.setVisibility(View.VISIBLE);
            tv_edirep.setVisibility(View.VISIBLE);
            tv_rep.setVisibility(View.VISIBLE);
            tv_ser.setVisibility(View.VISIBLE);
            tv_ediser.setVisibility(View.VISIBLE);
            tv_edimus.setVisibility(View.VISIBLE);
            tv_edinom.setVisibility(View.VISIBLE);
            tv_mus.setVisibility(View.VISIBLE);
            tv_nom.setVisibility(View.VISIBLE);
        }


    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                text_view_countdown.setVisibility(View.INVISIBLE);
                text_view_countdown.setTextSize(0);
                if(numejercio==tamrutina){
                    Glide.with(getActivity()).load("https://media1.tenor.com/images/c82efdb4947f166b3de039a980744ff1/tenor.gif?itemid=5439244").into(ejercicio);
                    visibilidadobjetos(true);
                }
                visibilidadobjetos(false);
                numserie=numserie-1;
                tv_ediser.setText(""+numserie);
                if(numserie==0){
                    numejercio=numejercio+1;
                    if(numejercio==2){
                        Glide.with(getActivity()).load("https://media1.tenor.com/images/c82efdb4947f166b3de039a980744ff1/tenor.gif").into(ejercicio);
                        visibilidadobjetos(true);
                    }else{
                        inicio(numejercio);
                    }

                }
            }
        }.start();
        mTimerRunning = true;

    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        text_view_countdown.setText(timeLeftFormatted);
    }

    public void cargarrutina(String url){
        Toast.makeText(getActivity().getApplicationContext(), "Bienvenido: "+url, Toast.LENGTH_SHORT).show();
        Log.i("url",url);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response=response.replace("][",",");
                try {
                    ja = new JSONArray(response);
                        for(int i=0;i<ja.length();i+=6) {
                            Ejercicio eje = new Ejercicio();
                            eje.setNombre(ja.getString(i));
                            eje.setMusculo(ja.getString(i + 1));
                            eje.setSerie(Integer.parseInt(ja.getString(i + 2)));
                            eje.setRepeticion(Integer.parseInt(ja.getString(i + 3)));
                            eje.setDescanso(Integer.parseInt(ja.getString(i + 4)));
                            eje.setVideo(ja.getString(i + 5));
                            rutina.add(eje);
                        }
                        tamrutina=ja.length();
                    inicio(numejercio);
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

    public void inicio(int e){
        tv_edirep.setText(""+rutina.get(e).getRepeticion());
        tv_ediser.setText(""+rutina.get(e).getSerie());
        numserie=rutina.get(e).getSerie();
        tv_edimus.setText(""+rutina.get(e).getMusculo());
        tv_edinom.setText(""+rutina.get(e).getNombre());
        cargarvideo(con.mostrarejercicio(rutina.get(e).getVideo()));
    }

    public void cargarvideo(String url){
        Toast.makeText(getActivity().getApplicationContext(), ""+con.mostrarejercicio(rutina.get(0).getVideo()), Toast.LENGTH_SHORT).show();
        Glide.with(getContext()).load(url).asGif().crossFade().into(ejercicio);
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
