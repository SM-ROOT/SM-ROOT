package com.example.appmo.Employee;

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


public class UserAdd extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static ManagerFragmentUser state;
    String ip = new  infoIP().getIp();
    View view;
    EditText txtName, txtSubNamePather, txtSubNameMother, txtPhone,
            txtAddres, txtNumberAddres, txtCp,txtColony, txtCity, txtState;
    Spinner spType;
    Button btnSave;

    ProgressDialog progreso;



    /**
     * Para establecer la conexion directa con el Web Service
     */
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public UserAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_user, container, false);
        saveUser();
        showToolBar();
        backButton();
        return view;
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.addUser));
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

    private void alertBack() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.proMM));
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(ManagerFragmentUser.USERINDEX);
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

    private void saveUser() {
        txtName = view.findViewById(R.id.txtName);
        txtSubNamePather = view.findViewById(R.id.txtNamePather);
        txtSubNameMother = view.findViewById(R.id.txtNameMother);
        txtPhone = view.findViewById(R.id.txtPhone);
        spType = view.findViewById(R.id.spType);
        txtAddres = view.findViewById(R.id.txtAddres);
        txtNumberAddres = view.findViewById(R.id.txtNumberAddres);
        txtCp = view.findViewById(R.id.txtCp);
        txtColony = view.findViewById(R.id.txtColony);
        txtCity = view.findViewById(R.id.txtCity);
        txtState = view.findViewById(R.id.txtState);

        request = Volley.newRequestQueue(getContext());

        btnSave = view.findViewById(R.id.btnSaveUser);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });



    }

    private void cargarWebService() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();


        String url = "http://" + ip + "/appmo/addUser.php?name=" + txtName.getText().toString() +
                "&subnamepather=" + txtSubNamePather.getText().toString() +
                "&subnamemother=" + txtSubNameMother.getText().toString() +
                "&phone=" + txtPhone.getText().toString() +
                "&type=" + spType.getSelectedItem().toString() +
                "&addres=" + txtAddres.getText().toString() +
                "&numberaddres=" + txtNumberAddres.getText().toString() +
                "&cp=" + txtCp.getText().toString() +
                "&colony=" + txtColony.getText().toString() +
                "&city=" + txtCity.getText().toString() +
                "&state=" + txtState.getText().toString() ;

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

    }

    private void alertCancel() {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                getContext());
        alertDialogBuilder.setTitle(getString(R.string.alertExit));
        alertDialogBuilder.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                changeFragment(ManagerFragmentUser.USERINDEX);
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

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), getString(R.string.susessfull), Toast.LENGTH_SHORT).show();
        progreso.hide();
        txtName.setText("");
        txtSubNamePather.setText("");
        txtSubNameMother.setText("");
        txtPhone.setText("");
        txtAddres.setText("");
        txtNumberAddres.setText("");
        txtCp.setText("");
        txtColony.setText("");
        txtCity.setText("");
        txtState.setText("");
        changeFragment(ManagerFragmentUser.USERINDEX);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(), getString(R.string.insusessfull) + error, Toast.LENGTH_SHORT).show();

    }

    private void changeFragment (ManagerFragmentUser state){
        this.state = ManagerFragmentUser.setState(state);
        this.state.execute((MainContainerUser) getContext());
    }


}
