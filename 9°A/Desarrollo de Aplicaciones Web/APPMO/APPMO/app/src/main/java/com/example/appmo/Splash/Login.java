package com.example.appmo.Splash;


import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
public class Login extends Fragment {
    /**
     * Acceso a la apliacion mediante la información de la base de datos
     **/
    String ip = new infoIP().getIp();
    JSONArray userJSON, conJSON;
    View view;
    Button btnEntryIndex;
    EditText txtUser, txtPassword;
    ProgressDialog progressDialog;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        openIndex();
        return view;
    }

    /**
     * Validación de texto
     **/
    private void validation() {
        if (txtUser.getText().length() <= 0) {
            txtUser.setError(getString(R.string.error));
        } else if (txtPassword.getText().length() <= 0) {
            txtPassword.setError(getString(R.string.error));
        } else {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getString(R.string.load));
            progressDialog.show();
            ConsultaPass("http://" + ip + "/appmo/conUser.php?user=" +
                    txtUser.getText().toString());
        }
    }

    private void openIndex() {
        btnEntryIndex = view.findViewById(R.id.btnEntryIndex);
        txtUser = view.findViewById(R.id.txtType);
        txtPassword = view.findViewById(R.id.txtImputPassword);

        btnEntryIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });
    }

    /**
     * Consulta de información del usuario para realizar la conexion
     **/
    private void ConsultaPass(String URL) {

        Log.i("url", "" + URL);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
                try {
                    userJSON = new JSONArray(response);
                    conJSON = new JSONArray(response);
                    String user = userJSON.getString(0);
                    String contra = conJSON.getString(1);
                    if (user.equals(txtUser.getText().toString())) {
                        progressDialog.hide();
                        if (contra.equals(txtPassword.getText().toString())) {
                            openIntent();
                        } else {
                            progressDialog.hide();
                            Toast.makeText(getContext(), getString(R.string.verificationCont), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        progressDialog.hide();
                        Toast.makeText(getContext(), getString(R.string.verificationUser), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    private void openIntent() {
        Intent intent = new Intent(getContext(), MainContainerIndex.class);
        startActivity(intent);
    }

}