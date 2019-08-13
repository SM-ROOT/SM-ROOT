package com.example.appmo.RoomCheck.BreadController;


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
import com.example.appmo.RoomCheck.Bread;
import com.example.appmo.RoomCheck.BreadAdapter;
import com.example.appmo.RoomCheck.MainContainerRoomCheck;
import com.example.appmo.RoomCheck.ManagerFragmentRoomCheck;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreadIndex extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentRoomCheck state;
    String ip = new infoIP().getIp();
    View view;
    RecyclerView recyclerView;
    ArrayList<Bread> listBread;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public BreadIndex() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index_bread, container, false);
        showToolBar();
        setHasOptionsMenu(true);
        backButton();
        listAdapter();
        loadWebService();
        return  view;
    }
    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.bread));
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
                changeFragment(ManagerFragmentRoomCheck.BREAD_ADD);
                break;

            case R.id.itemPdf:
                break;

            case R.id.itemSearch:
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void listAdapter() {
        listBread = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewBread);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());
    }

    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/listBread.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    public void onResponse(JSONObject response) {
        Bread bread = null;

        JSONArray json = response.optJSONArray("bread");
        try {
            for (int i = 0; i < json.length(); i++) {
                bread = new Bread("", "", "");
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                bread.setName(jsonObject.optString("name"));
                bread.setType(jsonObject.optString("type"));
                bread.setQuantity(jsonObject.optString("quantity"));

                listBread.add(bread);

            }

            progress.hide();
            BreadAdapter breadAdapter = new BreadAdapter(listBread);
            recyclerView.setAdapter(breadAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.insusessfull), Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private void changeFragment(ManagerFragmentRoomCheck state) {
        this.state= ManagerFragmentRoomCheck.setState(state);
        this.state.execute((MainContainerRoomCheck) getContext());
    }
}
