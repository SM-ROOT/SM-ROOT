package com.example.appmo.Supply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmo.R;

public class SupplyConsult extends AppCompatActivity {

    TextView tvRfc, tvNumber, tvMail, tvAdrres, tvNumberAddres, tvCp, tvColony, tvCity, tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_supply);
        String rfc = "";
        String number = "";
        String mail = "";
        String addres = "";
        String numberAddres = "";
        String cp = "";
        String colony = "";
        String city = "";
        String state = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            rfc =extras.getString("txtRfc");
            number =extras.getString("txtPhone");
            mail =extras.getString("txtMail");
            addres =extras.getString("txtAddres");
            numberAddres =extras.getString("txtNumberAddres");
            cp =extras.getString("txtCp");
            colony =extras.getString("txtColony");
            city =extras.getString("txtCity");
            state =extras.getString("spnState");
        }

        tvRfc = findViewById(R.id.txtName);
        tvRfc.setText(rfc);

        tvNumber = findViewById(R.id.txtPhone);
        tvNumber.setText(number);

        tvMail = findViewById(R.id.txtMeasure);
        tvMail.setText(mail);

        tvAdrres = findViewById(R.id.txtAddres);
        tvAdrres.setText(addres);

        tvNumberAddres = findViewById(R.id.txtNumberAddres);
        tvNumberAddres.setText(numberAddres);

        tvCp = findViewById(R.id.txtCp);
        tvCp.setText(cp);

        tvColony = findViewById(R.id.txtColony);
        tvColony.setText(colony);

        tvCity = findViewById(R.id.txtCity);
        tvCity.setText(city);

        tvState = findViewById(R.id.txtState);
        tvState.setText(state);
    }
}
