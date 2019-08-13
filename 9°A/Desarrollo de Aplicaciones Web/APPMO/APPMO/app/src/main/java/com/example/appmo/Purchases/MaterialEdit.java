package com.example.appmo.Purchases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.appmo.R;

public class MaterialEdit extends AppCompatActivity {

    EditText txtName, txtQuantity, txtCoste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_material);
        String name = "";
        String quantity = "";
        String coste = "";
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            name = extras.getString("txtName");
            quantity = extras.getString("txtQuantity");
            coste = extras.getString("txtCoste");
        }

        txtName = findViewById(R.id.txtName);
        txtName.setText(name);

        txtQuantity = findViewById(R.id.txtNumberAddres);
        txtQuantity.setText(quantity);

        txtCoste = findViewById(R.id.txtCp);
        txtCoste.setText(coste);
    }
}
