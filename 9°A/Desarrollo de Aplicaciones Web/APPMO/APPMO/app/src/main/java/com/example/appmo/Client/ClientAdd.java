package com.example.appmo.Client;


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
import android.widget.Button;
import android.widget.EditText;
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
public class ClientAdd extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    /**
     *Clase que tiene la función de agregar un nuevo cliente
     * **/

    public static ManagerFragmentClient state;
    //help, help no se programar, estoy practicando...
    View view;
    String ip = new infoIP().getIp();
    EditText txtName, txtPhone, txtAddres, txtNumberAddres, txtCp, txtColony, txtCity, txtState;
    Button btnSave;
    ProgressDialog progress;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public ClientAdd() {
        // Required empty public constructor
    }


    /**
     *Sobreecritura de la clase
     * **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_client, container, false);
        addClient();
        showToolBar();
        backButton();
        return view;
    }

    /**
     *Información del Toolbar
     * **/
    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.addClient));
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

    /**
     *Instacia de los elementos para agregar
     * **/
    private void addClient() {
        txtName = view.findViewById(R.id.txtName);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtAddres = view.findViewById(R.id.txtAddres);
        txtNumberAddres = view.findViewById(R.id.txtNumberAddres);
        txtCp = view.findViewById(R.id.txtCp);
        txtColony = view.findViewById(R.id.txtColony);
        txtCity = view.findViewById(R.id.txtCity);
        txtState = view.findViewById(R.id.txtState);

        request = Volley.newRequestQueue(getContext());

        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationText();
            }
        });


    }

    /**
     *Validaciones de textos vacios
     * **/
    private void validationText() {
        if (txtName.getText().length() <= 0) {
            txtName.setError(getString(R.string.errorDate));
        } else if (txtPhone.getText().length() <= 0) {
            txtPhone.setError(getString(R.string.errorDate));
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
        } else if (txtState.getText().length() <= 0) {
            txtState.setError(getString(R.string.errorDate));
        } else {
            saveClientAler();
        }
    }

    /**
     *Alerta de confirmación
     * **/
    private void saveClientAler() {
        AlertDialog.Builder alertSaveRoute = new AlertDialog.Builder(getContext());
        alertSaveRoute.setTitle(getString(R.string.cliT));
        alertSaveRoute.setMessage(getString(R.string.cliM));
        alertSaveRoute.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadWebService();
                changeFragment(ManagerFragmentClient.CLIENTINDEX);
            }
        });
        alertSaveRoute.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertSaveRoute.create().show();
    }

    /**
     *Alerta para cancelar el agregado
     * **/
    private void alertCancel() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.proT));
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(ManagerFragmentClient.CLIENTINDEX);
            }
        });
        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create().show();
    }

    /**
     *Funcion de la conexión
     * **/
    private void loadWebService() {
        progress = new ProgressDialog(getContext());
        progress.setMessage(getString(R.string.load));
        progress.show();

        String url = "http://" + ip + "/appmo/addClient.php?name=" + txtName.getText().toString() +
                "&phone=" + txtPhone.getText().toString() +
                "&addres=" + txtAddres.getText().toString() +
                "&numberaddres=" + txtNumberAddres.getText().toString() +
                "&cp=" + txtCp.getText().toString() +
                "&colony=" + txtColony.getText().toString() +
                "&city=" + txtCity.getText().toString() +
                "&state=" + txtState.getText().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);


    }

    /**
     *Conexion exitosa
     * **/
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), getString(R.string.susessfull), Toast.LENGTH_SHORT).show();
    }

    /**
     *Conexión rota
     * **/
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), getString(R.string.insusessfull) + error, Toast.LENGTH_SHORT).show();

    }

    /**
     *Intacia de fragemt PRE-CARGADOS
     * **/
    private void changeFragment(ManagerFragmentClient state) {
        this.state = ManagerFragmentClient.setState(state);
        this.state.execute((MainContainerClient) getContext());
    }
}
