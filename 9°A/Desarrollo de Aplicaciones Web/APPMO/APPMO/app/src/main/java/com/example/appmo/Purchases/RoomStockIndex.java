package com.example.appmo.Purchases;

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
public class RoomStockIndex extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentRoomStock state;
    String ip = new infoIP().getIp();
    View view;
    RecyclerView recyclerView;
    ArrayList<Material> listProdcut;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public RoomStockIndex() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index_roomstock, container, false);
        showToolBar();
        backButton();
        setHasOptionsMenu(true);
        listAdapter();
        loadWebService();
        return view;

    }

    private void listAdapter() {
        listProdcut = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());
    }


    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.roomStock));
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.toolbar_item, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAdd:
                changeFragment(ManagerFragmentRoomStock.ADDPURCHASE);
                break;

            case R.id.itemPdf:
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.itemSearch:
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/list_PurchaseMaterial.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Material material = null;

        JSONArray json = response.optJSONArray("purchaseMaterial");
        try {
            for (int i = 0; i < json.length(); i++) {
                material = new Material("", "", "", "", "");
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                material.setName(jsonObject.optString("name"));
                material.setDate(jsonObject.optString("purchaseDate"));
                material.setQuantity(jsonObject.optString("quantity"));
                material.setMeasure(jsonObject.optString("measure"));
                material.setCoste(jsonObject.optString("coste"));

                listProdcut.add(material);

            }

            progress.hide();
            MaterialAdapter productAdapter = new MaterialAdapter(listProdcut);
            recyclerView.setAdapter(productAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.insusessfull), Toast.LENGTH_LONG).show();
            progress.hide();
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getContext(), getString(R.string.insusessfull) + error, Toast.LENGTH_SHORT).show();

    }



    private void changeFragment(ManagerFragmentRoomStock state) {
        this.state = ManagerFragmentRoomStock.setState(state);
        this.state.execute((MainContainerRoomStock) getContext());
    }


}
