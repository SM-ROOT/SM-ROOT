package com.example.appmo.RoomCheck.BreadController;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmo.R;
import com.example.appmo.RoomCheck.MainContainerRoomCheck;
import com.example.appmo.RoomCheck.ManagerFragmentRoomCheck;
import com.example.appmo.infoIP;

import org.json.JSONObject;


public class BreadAdd extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    private static ManagerFragmentRoomCheck state;
    String ip = new infoIP().getIp();
    View view;
    EditText txtName, txtQuantity;
    private Spinner spnType;
    Button btnSaveProduct;
    ProgressDialog progress;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public BreadAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_bread, container, false);
        saveProduct();
        showToolBar();
        backButton();
        return view;
    }

    private void saveProduct() {
        txtName = view.findViewById(R.id.txtName);
        spnType = view.findViewById(R.id.spnType);
        txtQuantity = view.findViewById(R.id.txtNumberAddres);
        request = Volley.newRequestQueue(getContext());
        btnSaveProduct = view.findViewById(R.id.btnFinish);
        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationText();
            }
        });
    }

    private void saveRecipeAler() {
        AlertDialog.Builder alertSaveRoute = new AlertDialog.Builder(getContext());
        alertSaveRoute.setTitle(getString(R.string.recT));
        alertSaveRoute.setMessage(getString(R.string.recM));
        alertSaveRoute.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadWebService();
                changeFragment(ManagerFragmentRoomCheck.BREAD);
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

        String url = "http://" + ip + "/appmo/addProductRoom.php?" +
                "name=" + txtName.getText().toString() +
                "&type=" + spnType.getSelectedItem().toString()+
                "&quantity=" + txtQuantity.getText().toString() ;

                url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void validationText() {
        if (txtQuantity.getText().length() <= 0) {
            txtQuantity.setError(getString(R.string.errorDate));
        } else {
            saveRecipeAler();
        }
    }

    private void showToolBar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.addProduct));
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

    private void alertCancel() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.proT)) ;
        alertDialog.setMessage(getString(R.string.proMM));
        alertDialog.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(ManagerFragmentRoomCheck.BREAD);
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
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResponse(JSONObject response) {
        progress.hide();

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private void changeFragment(ManagerFragmentRoomCheck state) {
        this.state = ManagerFragmentRoomCheck.setState(state);
        this.state.execute((MainContainerRoomCheck) getContext());
    }
}
