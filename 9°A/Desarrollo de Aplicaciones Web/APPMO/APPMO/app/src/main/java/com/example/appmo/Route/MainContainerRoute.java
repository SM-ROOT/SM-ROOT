package com.example.appmo.Route;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainContainerRoute extends AppCompatActivity  implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentRoute state;

    String ip = new infoIP().getIp();
    RecyclerView recyclerView;
    ArrayList<Route> listRoute;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_route);
        changeFragment(ManagerFragmentRoute.ROUTE_INDEX);

    }

    private void changeFragment(ManagerFragmentRoute state) {
        this.state = ManagerFragmentRoute.setState(state);
        this.state.execute(this);
    }


    private void recicleViewOrder() {
        loadWebService();

    }

    private void loadWebService() {
        progress = new ProgressDialog(this);
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/listRoute.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                this, this);
        request.add(jsonObjectRequest);
    }

    public void onResponse(JSONObject response) {
        Route route = null;

        JSONArray json = response.optJSONArray("route");
        try {
            for (int i = 0; i < json.length(); i++) {
                route = new Route("", "");
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                route.setName(jsonObject.optString("name"));
                route.setCurator(jsonObject.optString("curator"));
                listRoute.add(route);

            }
            progress.hide();
            //RouteAdapter orderAdapter = new RouteAdapter(listRoute);
            //recyclerView.setAdapter(orderAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), getString(R.string.insusessfull), Toast.LENGTH_LONG).show();
            progress.hide();
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


    public void onClickConsult(View view) {
        listRoute = new ArrayList<>();



        request = Volley.newRequestQueue(this);
        recicleViewOrder();
        android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(getString(R.string.proT));
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create().show();
    }
}