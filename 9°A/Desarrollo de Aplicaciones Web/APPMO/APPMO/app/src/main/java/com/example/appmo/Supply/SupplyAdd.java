package com.example.appmo.Supply;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.infoIP;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplyAdd extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentSupply state;
    String ip = new infoIP().getIp();
    View view;
    EditText txtRfc, txtPhone, txtMail, txtAddres, txtNumberAddres, txtCp, txtColony, txtCity;
    Spinner spnState;
    Button btnSaveSupply;
    ProgressDialog progress;
    private Typeface typeStyle;


    ProgressDialog progreso;

    /**
     * Para establecer la conexion directa con el Web Service
     */
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public SupplyAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_supply, container, false);
        backButton();
        saveSupply();
        showToolBar();
        return view;
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.addSupply));
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

    private void saveSupply() {
        txtRfc = view.findViewById(R.id.txtName);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtMail = view.findViewById(R.id.txtMeasure);
        txtAddres = view.findViewById(R.id.txtAddres);
        txtNumberAddres = view.findViewById(R.id.txtNumberAddres);
        txtCp = view.findViewById(R.id.txtCp);
        txtColony = view.findViewById(R.id.txtColony);
        txtCity = view.findViewById(R.id.txtCity);
        spnState = view.findViewById(R.id.txtState);

        request = Volley.newRequestQueue(getContext());

        btnSaveSupply = view.findViewById(R.id.btnSaveSupply);
        btnSaveSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });

    }

    private void validation() {
        if (txtRfc.getText().length() <= 0) {
            txtRfc.setError(getString(R.string.errorDate));
        } else if (txtPhone.getText().length() <= 0) {
            txtPhone.setError(getString(R.string.errorDate));
        } else if (txtMail.getText().length() <= 0) {
            txtMail.setText(getString(R.string.errorDate));
        } else if (txtAddres.getText().length() <= 0) {
            txtAddres.setError(getString(R.string.errorDate));
        } else if (txtNumberAddres.getText().length() <= 0) {
            txtNumberAddres.setError(getString(R.string.errorDate));
        } else if (txtCp.getText().length() <= 0) {
            txtCp.setError(getString(R.string.errorDate));
        } else if (txtColony.getText().length() <= 0) {
            txtColony.setError(getString(R.string.errorDate));
        } else if (txtCity.getText().length() <= 0) {
            txtCity.setError(getString(R.string.errorDate));
        } else {
            saveSupplyAler();

        }
    }

    private void saveSupplyAler() {
        AlertDialog.Builder alertSaveRoute = new AlertDialog.Builder(getContext());
        alertSaveRoute.setTitle(getString(R.string.supT));
        alertSaveRoute.setMessage(getString(R.string.supM));
        alertSaveRoute.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cargarWebService();
                changeFragment(ManagerFragmentSupply.SUPPLY);
            }
        });
        alertSaveRoute.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertSaveRoute.create().show();
    }

    private void cargarWebService() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        String url = "http://" + ip + "/appmo/addSupply.php?rfc=" + txtRfc.getText().toString() +
                "&number=" + txtPhone.getText().toString() +
                "&mail=" + txtMail.getText().toString() +
                "&addres=" + txtAddres.getText().toString() +
                "&numberaddres=" + txtNumberAddres.getText().toString() +
                "&cp=" + txtCp.getText().toString() +
                "&colony=" + txtColony.getText().toString() +
                "&state=" + spnState.getSelectedItem().toString() +
                "&city=" + txtCity.getText().toString();


        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(), getString(R.string.insusessfull) + error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
    }

    private void alertBack() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.proTM));
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(ManagerFragmentSupply.SUPPLY);
            }
        });
        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create().show();
    }

    private void backButton() {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    //validation();
                    return true;
                }
                return false;
            }
        });
    }

    private void openAlert() {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                getContext());
        alertDialogBuilder.setTitle(getString(R.string.alertSupply));
        alertDialogBuilder.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                changeFragment(ManagerFragmentSupply.SUPPLY);
            }
        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    private void changeFragment(ManagerFragmentSupply state) {
        this.state = ManagerFragmentSupply.setState(state);
        this.state.execute((MainContainerSupply) getContext());
    }


}
