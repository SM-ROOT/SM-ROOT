package com.example.appmo.Client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.appmo.R;

public class ClientEdit extends AppCompatActivity {
    EditText txtName, txtPhone, txtAddres, txtNumberAddres, txtCp, txtColony, txtCity, txtState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
        String name = "";
        String phone = "";
        String addres = "";
        String numberAddres = "";
        String cp = "";
        String colony = "";
        String city = "";
        String state = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("txtName");
            phone = extras.getString("txtPhone");
            addres = extras.getString("txtAddres");
            numberAddres = extras.getString("txtNumberAddres");
            cp = extras.getString("txtCp");
            colony = extras.getString("txtColony");
            city = extras.getString("txtCity");
            state = extras.getString("txtState");
        }

        /**
         *Bundle para poder editar la informacón de un cliente
         * **/
        txtName = findViewById(R.id.txtName);
        txtName.setText(name);

        txtPhone = findViewById(R.id.txtPhone);
        txtPhone.setText(phone);

        txtAddres = findViewById(R.id.txtAddres);
        txtAddres.setText(addres);

        txtNumberAddres = findViewById(R.id.txtNumberAddres);
        txtNumberAddres.setText(numberAddres);

        txtCp = findViewById(R.id.txtCp);
        txtCp.setText(cp);

        txtColony = findViewById(R.id.txtColony);
        txtColony.setText(colony);

        txtCity = findViewById(R.id.txtCity);
        txtCity.setText(city);

        txtState = findViewById(R.id.txtState);
        txtState.setText(state);


    }
}
