package com.example.appmo.Client;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.Order.MainContainerIndex;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientIndex extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    /**
     * Estructura de la clase index
     **/
    public static ManagerFragmentClient state;
    String ip = new infoIP().getIp();
    View view;
    RecyclerView recyclerView;
    ArrayList<Client> listClient;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public ClientIndex() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index_client, container, false);
        showToolBar();
        backButton();
        recicleViewClient();
        setHasOptionsMenu(true);
        loadWebService();
        //cardView();
        return view;
    }

    /**
     *Funcion del Toolbar
     * **/
    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.client));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goIndex = new Intent(getContext(), MainContainerIndex.class);
                    startActivity(goIndex);
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
                    return true;
                }
                return false;
            }
        });
    }

    /**
     *Intancia del menu del ToolBar
     * **/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.toolbar_item, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    /**
     *Funciones del Menu del Toolbar
     * **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAdd:
                changeFragment(ManagerFragmentClient.CLIENTADD);
                break;

            case R.id.itemPdf:
                Toast.makeText(getContext(), "Función para envar PDF", Toast.LENGTH_SHORT).show();
                break;

            case R.id.itemSearch:
                Toast.makeText(getContext(), "Función para realizar busqueda", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *Elemento para acomadar la informacion del Adaptador para el Modulo de Clientes
     * **/
    private void recicleViewClient() {
        listClient = new ArrayList<>();


        recyclerView = view.findViewById(R.id.recyclerViewClient);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());
    }

    /**
     *Funcion de conexión
     * **/
    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/listClient.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    /**
     *Conexión exitosa
     * **/
    @Override
    public void onResponse(JSONObject response) {
        Client client = null;

        JSONArray json = response.optJSONArray("client");
        try {
            for (int i = 0; i < json.length(); i++) {
                client = new Client("", "", "", "",
                        "", "", "", "");
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                client.setName(jsonObject.optString("name"));
                client.setPhone(jsonObject.optString("phone"));
                client.setAdrres(jsonObject.optString("addres"));
                client.setNumberAdrres(jsonObject.optString("numberaddres"));
                client.setCp(jsonObject.optString("cp"));
                client.setColony(jsonObject.optString("colony"));
                client.setCity(jsonObject.optString("city"));
                client.setState(jsonObject.optString("state"));


                listClient.add(client);

            }

            progress.hide();
            ClientAdapter clientAdapter = new ClientAdapter(listClient);
            recyclerView.setAdapter(clientAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.insusessfull), Toast.LENGTH_LONG).show();
            progress.hide();
        }


    }

    /**
     *Conexión rota
     * **/
    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getContext(), getString(R.string.insusessfull) + error, Toast.LENGTH_SHORT).show();
        progress.hide();

    }

    /**
     *Instacia de Fragment
     * **/
    private void changeFragment(ManagerFragmentClient state) {
        this.state = ManagerFragmentClient.setState(state);
        this.state.execute((MainContainerClient) getContext());
    }


}
