package com.example.julia.aplicacion;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link foto_fragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link foto_fragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class foto_fragmento extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Conexion con;
    private ImageView iv_fot;
    private Button bot_cerrar;
    Bundle args = new Bundle();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public foto_fragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment foto_fragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static foto_fragmento newInstance(String param1, String param2) {
        foto_fragmento fragment = new foto_fragmento();
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
        View v =inflater.inflate(R.layout.fragment_foto_fragmento, container, false);
        con=new Conexion();
        iv_fot=(ImageView) v.findViewById(R.id.iv_fot);
        bot_cerrar=(Button)v.findViewById(R.id.bot_cerrar);
        bot_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentomedidas f=new fragmentomedidas();
                args.putString("usuario", "1014268420");
                f.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.prueba,f).addToBackStack(null).commit();
            }
        });
        cargarimagen(getArguments().getString("frontal"));
        return v;
    }

    public void cargarimagen(String rutaimagen){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        ImageRequest imageRequest=new ImageRequest(con.mostrarfotomedidas(rutaimagen), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv_fot.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "el error es:"+error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(imageRequest);
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
