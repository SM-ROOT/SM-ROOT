package com.example.appmo.Purchases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmo.R;

public class MaterialConsult extends AppCompatActivity {
    TextView  tvName, tvQuantity, tvMeasure, tvCoste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_purchase);
        String name = "";
        String quantity = "";
        String type = "";
        String coste = "";
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            name =extras.getString("txtName");
            quantity =extras.getString("txtQuantity");
            type =extras.getString("txtType");
            coste =extras.getString("txtCoste");
        }


        tvName = findViewById(R.id.txtName);
        tvName.setText(name);

        tvQuantity = findViewById(R.id.txtNumberAddres);
        tvQuantity.setText(quantity);

        tvMeasure = findViewById(R.id.txtMeasure);
        tvMeasure.setText(type);

        tvCoste = findViewById(R.id.txtCp);
        tvCoste.setText(coste);
    }
}
