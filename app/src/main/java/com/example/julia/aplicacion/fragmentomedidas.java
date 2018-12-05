package com.example.julia.aplicacion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragmentomedidas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragmentomedidas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentomedidas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList <Medida> med;
    private Conexion con;
    private String fot;
    private ImageView foto;
    private JSONArray ja;
    private TableRow tr_fecha,tr_peso,tr_imc,tr_igc,tr_cuello,tr_espalda,tr_brazo,tr_antebrazo,tr_gluteo,tr_pecho,tr_abdomen,tr_pierna,tr_pantorrilla;
    private TableLayout table;
    private TextView usuario,tx_fecha,tx_peso,tx_imc,tx_igc,tx_cuello,tx_espalda,tx_brazo,tx_antebrazo,tx_gluteo,tx_pecho,tx_abdomen,tx_pierna,tx_pantorrilla;
    private int im=0;
    private String f="";
    LineChart piechar;
    ArrayList<Entry> yValues =new ArrayList<>();
    int contador=0;
    Button cerrar;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public fragmentomedidas() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragmentomedidas newInstance(String param1, String param2) {
        fragmentomedidas fragment = new fragmentomedidas();
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

    //carga los datos de la base al ararylist
    public void cargardatos(String url){
        Toast.makeText(getActivity().getApplicationContext(), "Bienvenido: "+url, Toast.LENGTH_LONG).show();
        Log.i("url",url);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response=response.replace("][",",");
                try {
                    ja = new JSONArray(response);

                    for(int i=2;i<ja.length();i+=17){
                        Medida medida=new Medida();
                        medida.setFec(ja.getString(i));
                        medida.setPeso(Float.parseFloat(ja.getString(i+1)));
                        medida.setImc(Float.parseFloat(ja.getString(i+2)));
                        medida.setIgc(Float.parseFloat(ja.getString(i+3)));
                        medida.setCuello(Integer.parseInt(ja.getString(i+4)));
                        medida.setEspalda(Integer.parseInt(ja.getString(i+5)));
                        medida.setBrazo(Integer.parseInt(ja.getString(i+6)));
                        medida.setAntebrazo(Integer.parseInt(ja.getString(i+7)));
                        medida.setGluteo(Integer.parseInt(ja.getString(i+8)));
                        medida.setPecho(Integer.parseInt(ja.getString(i+9)));
                        medida.setAbdomen(Integer.parseInt(ja.getString(i+10)));
                        medida.setPierna(Integer.parseInt(ja.getString(i+11)));
                        medida.setPantorrilla(Integer.parseInt(ja.getString(i+12)));
                        medida.setFrontal(ja.getString(i+13));
                        medida.setLateral(ja.getString(i+14));
                        med.add(medida);
                    }

                    creartabla();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), "El usaurio no existe"+e.toString(), Toast.LENGTH_SHORT).show();
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

    //carga los valores del arraylist a la tabla
    public void creartabla(){
        f=med.get(0).getFrontal();
        cargarimagen(med.get(0).getFrontal());
        for(int i=0;i<med.size();i++)
        {
            TextView tvDebt=new TextView(getActivity());
            tvDebt.setText(""+med.get(i).getFec());
            final int n=i;
            tvDebt.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {
                    cargarimagen(med.get(n).getFrontal());
                    f=med.get(n).getFrontal();
                    int alto=foto.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                    int ancho=foto.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
                    foto.setLayoutParams(params);
                    ancho=0;
                    alto=0;
                    params = new LinearLayout.LayoutParams(ancho, alto);
                    piechar.setLayoutParams(params);
                    cerrar.setVisibility(View.VISIBLE);
                }
            });
            foto.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(im==0){
                        for(int j=0;j<med.size();j++){
                            if(f== med.get(j).getFrontal()){
                                cargarimagen(med.get(j).getLateral());
                                f=med.get(j).getLateral();
                            }
                        }
                        im=1;
                    }else{
                        for(int j=0;j<med.size();j++){
                            if(f== med.get(j).getLateral()){
                                cargarimagen(med.get(j).getFrontal());
                                f=med.get(j).getFrontal();
                            }
                        }
                        im=0;
                    }
                    return false;
                }
            });
            tr_fecha.addView(tvDebt);
            TextView tvpeso=new TextView(getActivity());
            tvpeso.setText(""+med.get(i).getPeso());
            tr_peso.addView(tvpeso);
            TextView tvimc=new TextView(getActivity());
            tvimc.setText(""+med.get(i).getImc());
            tr_imc.addView(tvimc);
            TextView tvigc=new TextView(getActivity());
            tvigc.setText(""+med.get(i).getIgc());
            tr_igc.addView(tvigc);
            TextView tvcuello=new TextView(getActivity());
            tvcuello.setText(""+med.get(i).getCuello());
            tr_cuello.addView(tvcuello);
            TextView tves=new TextView(getActivity());
            tves.setText(""+med.get(i).getEspalda());
            tr_espalda.addView(tves);
            TextView tvbr=new TextView(getActivity());
            tvbr.setText(""+med.get(i).getBrazo());
            tr_brazo.addView(tvbr);
            TextView tvante=new TextView(getActivity());
            tvante.setText(""+med.get(i).getAntebrazo());
            tr_antebrazo.addView(tvante);
            TextView tvgluteo=new TextView(getActivity());
            tvgluteo.setText(""+med.get(i).getGluteo());
            tr_gluteo.addView(tvgluteo);
            TextView tvpecho=new TextView(getActivity());
            tvpecho.setText(""+med.get(i).getPecho());
            tr_pecho.addView(tvpecho);
            TextView tvab=new TextView(getActivity());
            tvab.setText(""+med.get(i).getAbdomen());
            tr_abdomen.addView(tvab);
            TextView tvpierna=new TextView(getActivity());
            tvpierna.setText(""+med.get(i).getPierna());
            tr_pierna.addView(tvpierna);
            TextView tvpanto=new TextView(getActivity());
            tvpanto.setText(""+med.get(i).getPantorrilla());
            tr_pantorrilla.addView(tvpanto);
        }

    }

    //carga las imagenes
    public void cargarimagen(String rutaimagen){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        ImageRequest imageRequest=new ImageRequest(con.mostrarfotomedidas(rutaimagen), new Response.Listener<Bitmap>() {
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

    //se inicializan las variables
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragmentomedidas, container, false);
        con=new Conexion();
        med=new ArrayList<>();
        table = (TableLayout)v.findViewById(R.id.myTableLayout);
        tx_peso=(TextView)v.findViewById(R.id.Anio1);
        tx_imc=(TextView)v.findViewById(R.id.Anio2);
        tx_igc=(TextView) v.findViewById(R.id.Anio3);
        tx_cuello=(TextView) v.findViewById(R.id.Anio4);
        tx_espalda=(TextView) v.findViewById(R.id.Anio5);
        tx_brazo=(TextView) v.findViewById(R.id.Anio6);
        tx_antebrazo=(TextView) v.findViewById(R.id.Anio7);
        tx_gluteo=(TextView) v.findViewById(R.id.Anio8);
        tx_pecho=(TextView) v.findViewById(R.id.Anio9);
        tx_abdomen=(TextView) v.findViewById(R.id.Anio10);
        tx_pierna=(TextView) v.findViewById(R.id.Anio11);
        tx_pantorrilla=(TextView) v.findViewById(R.id.Anio12);
        tr_fecha = (TableRow) v.findViewById(R.id.Cabecera);
        tr_peso=(TableRow) v.findViewById(R.id.Fila1);
        tr_imc=(TableRow) v.findViewById(R.id.Fila2);
        tr_igc=(TableRow) v.findViewById(R.id.Fila3);
        tr_cuello=(TableRow) v.findViewById(R.id.Fila4);
        tr_espalda=(TableRow) v.findViewById(R.id.Fila5);
        tr_brazo=(TableRow) v.findViewById(R.id.Fila6);
        tr_antebrazo=(TableRow) v.findViewById(R.id.Fila7);
        tr_gluteo=(TableRow) v.findViewById(R.id.Fila8);
        tr_pecho=(TableRow) v.findViewById(R.id.Fila9);
        tr_abdomen=(TableRow) v.findViewById(R.id.Fila10);
        tr_pierna=(TableRow) v.findViewById(R.id.Fila11);
        tr_pantorrilla=(TableRow) v.findViewById(R.id.Fila12);
        cerrar=(Button) v.findViewById(R.id.bot_cerrar);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int alto=0;
                int ancho=0;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
                foto.setLayoutParams(params);
                piechar.setLayoutParams(params);
                cerrar.setVisibility(View.INVISIBLE);

            }
        });
        foto=(ImageView)v.findViewById(R.id.iv_fot);
        piechar=v.findViewById(R.id.piechar);
        cargardatos(con.mostrarmedida(getArguments().getString("usuario")));
        final ArrayList<String> theDates=new ArrayList<>();
        tx_peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yValues.clear();
                theDates.clear();
                int g=0;
                for(int i=0;i<med.size();i++){
                    yValues.add(new BarEntry(med.get(i).getPeso(),g));
                    theDates.add(med.get(i).getFec());
                    g++;
                }
                LineDataSet dataSet = new LineDataSet(yValues,"PESO");
                dataSet.setDrawFilled(true);
                LineData data = new LineData(theDates,dataSet);
                piechar.setData(data);
                piechar.invalidate();
                int alto=foto.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                int ancho=foto.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                Toast.makeText(getActivity().getApplicationContext(), "el error es:"+med.size(), Toast.LENGTH_LONG).show();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
                piechar.setLayoutParams(params);
                cerrar.setVisibility(View.VISIBLE);
            }
        });
        tx_imc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yValues.clear();
                theDates.clear();
                int g=0;
                for(int i=0;i<med.size();i++){
                    yValues.add(new BarEntry(med.get(i).getImc(),g));
                    theDates.add(med.get(i).getFec());
                    g++;
                }
                LineDataSet dataSet = new LineDataSet(yValues,"IMC");
                LineData data = new LineData(theDates,dataSet);
                piechar.setData(data);
                piechar.invalidate();
                int alto=foto.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                int ancho=foto.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                Toast.makeText(getActivity().getApplicationContext(), "el error es:"+med.size(), Toast.LENGTH_LONG).show();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
                piechar.setLayoutParams(params);
                cerrar.setVisibility(View.VISIBLE);

            }
        });
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
