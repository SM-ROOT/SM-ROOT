package com.example.appmo.Client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmo.R;

public class ClientConsult extends AppCompatActivity {
    /**
     * Consulta de un unico cliente
     **/
    TextView tvName, tvNumber, tvAdrres, tvNumberAddres, tvCp, tvColony, tvCity, tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_client);
        String name = "";
        String number = "";
        String addres = "";
        String numberAddres = "";
        String cp = "";
        String colony = "";
        String city = "";
        String state = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("txtName");
            number = extras.getString("txtPhone");
            addres = extras.getString("txtAddres");
            numberAddres = extras.getString("txtNumberAddres");
            cp = extras.getString("txtCp");
            colony = extras.getString("txtColony");
            city = extras.getString("txtCity");
            state = extras.getString("txtState");
        }

        /**
         *Bundle del modelo para consultar
         * **/
        tvName = findViewById(R.id.txtName);
        tvName.setText(name);

        tvNumber = findViewById(R.id.txtPhone);
        tvNumber.setText(number);

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
