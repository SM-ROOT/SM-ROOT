package com.example.appmo.Purchases;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPurchanseMaterial extends Fragment  {
    public static ManagerFragmentRoomStock state;
    String ip = new infoIP().getIp();
    View view;
    EditText coste, quantity, name;
    Spinner type;
    Button btnSave, btnFinish;
    ListView list;
    ProgressDialog progress;

    public AddPurchanseMaterial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_product, container, false);
        saveProduct();
        showToolBar();
        setHasOptionsMenu(true);
        backButton();
        //fontStyle();
        return view;
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.productAdd));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertBack();
                }
            });
        }
    }

    private void backButton() {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (coste.getText().length()>=0){
                        getContext();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void alertBack() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.proTM));
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(ManagerFragmentRoomStock.ROOMSTOCKINDEX);
            }
        });
        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create().show();
    }

    private void saveProduct() {
        quantity = view.findViewById(R.id.txtNumberAddres);
        type = view.findViewById(R.id.spType);
        name = view.findViewById(R.id.txtName);
        coste = view.findViewById(R.id.txtCp);
        btnSave = view.findViewById(R.id.btnSave);
        btnFinish = view.findViewById(R.id.btnFinish);
        list = view.findViewById(R.id.lvLista);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registro = "http://" + ip + "/hotelejemplo/registrarReserva.php?idr=NULL" +
                        "&NHab=" + name.getText() +
                        "&fe=" + quantity.getText().toString() +
                        "&fs=" + type.getSelectedItem().toString() +
                        "&nc=" + coste.getText().toString();
                quantity.getText().clear();
                cargarWebService(registro);

                String consulta = "http://" + ip + "/hotelejemplo/consultarReserva.php";
                cargarWebService(consulta);

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(ManagerFragmentRoomStock.ROOMSTOCKINDEX);
            }
        });


    }

    public void cargarWebService(String URL) {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][", ",");
                if (response.length() > 0) {
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson", "" + ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }

    public void CargarListView(final JSONArray ja) {

        final ArrayList<String> lista = new ArrayList<>();

        for (int i = 0; i < ja.length(); i += 4) {

            try {
                lista.add("Nombre: " + ja.getString(i) + "\n" +
                        "Cantidad: " + ja.getString(i + 1) + "\n" +
                        "Type: " + ja.getString(i + 2) + "\n" +
                        "Costo: " + ja.getString(i + 3));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                Toast.makeText(getContext(), "" + ja.getString(i + 1), Toast.LENGTH_SHORT);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }

        });


    }

    private void validation() {
        if (name.getText().length() <= 0) {
            name.setError(getString(R.string.errorAddPr));
        } else if (quantity.getText().length() <= 0) {
            quantity.setError(getString(R.string.errorAddPr));
        } else if (coste.getText().length()<=0){
            coste.setError(getString(R.string.errorAddPr));
        } else {
            alerSaveProduc();
        }
    }

    private void alerSaveProduc(){
        AlertDialog.Builder alertSaveRoute = new AlertDialog.Builder(getContext());
        alertSaveRoute.setTitle(getString(R.string.proT));
        alertSaveRoute.setMessage(getString(R.string.proM));
        alertSaveRoute.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cargarWebService();
                changeFragment(ManagerFragmentRoomStock.ROOMSTOCKINDEX);
            }
        });
        alertSaveRoute.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertSaveRoute.create().show();
    }

    private void changeFragment(ManagerFragmentRoomStock state) {
        this.state = ManagerFragmentRoomStock.setState(state);
        this.state.execute((MainContainerRoomStock) getContext());
    }

}
