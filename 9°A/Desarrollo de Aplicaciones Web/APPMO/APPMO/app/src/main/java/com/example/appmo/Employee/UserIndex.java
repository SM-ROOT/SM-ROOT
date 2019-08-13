package com.example.appmo.Employee;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
public class UserIndex extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentUser state;
    String ip = new infoIP().getIp();
    View view;
    RecyclerView recyclerView;
    ArrayList<User> listUser;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public UserIndex() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index_user, container, false);
        showToolBar();
        listAdapter();
        loadWebService();
        setHasOptionsMenu(true);
        return view;
    }

    private void listAdapter() {
        listUser = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());
    }

    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/listUser.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.user));
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.toolbar_item, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAdd:
                changeFragment(ManagerFragmentUser.ADD_USER);
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

    private void changeFragment (ManagerFragmentUser state){
        this.state= ManagerFragmentUser.setState(state);
        this.state.execute((MainContainerUser) getContext());
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    public void onResponse(JSONObject response) {
        User user = null;

        JSONArray json = response.optJSONArray("user");
        try {
            for (int i = 0; i < json.length(); i++) {
                user = new User("", "", "", "", "", "", "",
                        "", "", "", "");
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                user.setName(jsonObject.optString("name"));
                user.setSubNamePather(jsonObject.optString("subnamepather"));
                user.setSubNameMother(jsonObject.optString("subnamemother"));
                user.setPhone(jsonObject.optString("phone"));
                user.setType(jsonObject.optString("type"));
                user.setAddres(jsonObject.optString("addres"));
                user.setAddresNumber(jsonObject.optString("numberaddres"));
                user.setCp(jsonObject.optString("cp"));
                user.setColony(jsonObject.optString("colony"));
                user.setCity(jsonObject.optString("city"));
                user.setState(jsonObject.optString("state"));



                listUser.add(user);

            }

            progress.hide();
            UserAdapter userAdapter = new UserAdapter(listUser);
            recyclerView.setAdapter(userAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.insusessfull), Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }

}
