package com.example.appmo.Recipe;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeAdd extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    private static ManagerFragmentRecipe state;
    String ip = new infoIP().getIp();
    View view;
    EditText txtQuantity;
    private Spinner spnNameBread, spnIngredient, spnMeasure, spnType;
    Button btnSaveRecipe;
    ProgressDialog progress;
    ArrayList<String> ingredient;
    ArrayList<String> bread;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public RecipeAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        saveRecipe();
        showToolBar();
        backButton();
        structureIngredient();
        listIngredient();
        listNameBread();
        structureNameBread();
        return view;
    }

    private void saveRecipe() {
        txtQuantity = view.findViewById(R.id.txtNumberAddres);
        spnMeasure = view.findViewById(R.id.spMeasure);
        spnType = view.findViewById(R.id.spnType);
        request = Volley.newRequestQueue(getContext());
        btnSaveRecipe = view.findViewById(R.id.btnSaveRecipe);
        btnSaveRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationText();
            }
        });
    }

    private void validationText() {
        if  (txtQuantity.getText().length() <= 0) {
            txtQuantity.setError(getString(R.string.errorDate));
        } else {
            saveRecipeAler();
        }
    }

    private void saveRecipeAler() {
        AlertDialog.Builder alertSaveRoute = new AlertDialog.Builder(getContext());
        alertSaveRoute.setTitle(getString(R.string.recT));
        alertSaveRoute.setMessage(getString(R.string.recM));
        alertSaveRoute.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadWebService();
                changeFragment(ManagerFragmentRecipe.RECIPEINDEX);
            }
        });
        alertSaveRoute.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertSaveRoute.create().show();
    }

    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/addRecipe.php?" +
                "name=" + spnNameBread.getSelectedItem().toString() +
                "&ingredient=" + spnIngredient.getSelectedItem().toString() +
                "&quantity=" + txtQuantity.getText().toString() +
                "&measure=" + spnMeasure.getSelectedItem().toString() +
                "&type=" + spnType.getSelectedItem().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void alertCancel() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.proT)) ;
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(ManagerFragmentRecipe.RECIPEINDEX);
            }
        });
        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create().show();
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.addRecipe));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertCancel();
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

    private void structureIngredient() {
        ingredient = new ArrayList<>();
        spnIngredient = view.findViewById(R.id.spnIngredient);
        spnIngredient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fruta = spnIngredient.getItemAtPosition(spnIngredient.getSelectedItemPosition()).toString();
                //Toast.makeText(getContext(), fruta, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    public void listIngredient() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, infoIP.URL_WEB_SERVICES_INGREDIENT+ "list_ingredient.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("ingredient");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String nameU = jsonObject1.getString("name");
                                ingredient.add(nameU);
                            }
                            spnIngredient.setAdapter(new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_dropdown_item, ingredient
                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void structureNameBread() {
        bread = new ArrayList<>();
        spnNameBread = view.findViewById(R.id.spnNameBread);
        spnNameBread.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fruta = spnNameBread.getItemAtPosition(spnNameBread.getSelectedItemPosition()).toString();
                //Toast.makeText(getContext(), fruta, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    public void listNameBread() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, infoIP.URL_WEB_SERVICES_BREAD+ "list_bread.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("bread");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String nameU = jsonObject1.getString("name");
                                bread.add(nameU);
                            }
                            spnNameBread.setAdapter(new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_dropdown_item, bread));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        progress.hide();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private void changeFragment(ManagerFragmentRecipe state) {
        this.state = ManagerFragmentRecipe.setState(state);
        this.state.execute((MainContainerRecipe) getContext());
    }
}
