package com.example.appmo.Recipe;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchRecipe extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    String ip = new infoIP().getIp();
    View view;
    EditText txtName;
    TextView txtType;
    Button btnConsultRecipe;
    ProgressDialog progress;

    RequestQueue request;
    JsonObjectRequest jsonObjectReques;

    public SearchRecipe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_consult_recipe, container, false);
        consultRecipe();
        return view;
    }

    private void consultRecipe() {
        txtName = view.findViewById(R.id.txtName);
        txtType = view.findViewById(R.id.txtType);
        btnConsultRecipe = view.findViewById(R.id.btnConsultRecipe);

        request= Volley.newRequestQueue(getContext());

        btnConsultRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWebService();
            }
        });

    }

    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

         String url = "http://"+ ip+ "/appmo/SearchRecipe.php?name=" +
                 txtName.getText().toString();

         jsonObjectReques = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
         request.add(jsonObjectReques);
    }

    @Override
    public void onResponse(JSONObject response) {
        progress.hide();
        Recipe myRecipe = new Recipe(null, null, "", "", "");

        JSONArray json = response.optJSONArray("consultRecipe");
        JSONObject jsonObject= null;

        try {
            jsonObject = json.getJSONObject(0);
            myRecipe.setType(jsonObject.optString("type"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtType.setText(myRecipe.getType());

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getContext(), getString(R.string.insusessfull) + error, Toast.LENGTH_SHORT).show();

    }


}
