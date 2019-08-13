package com.example.appmo.Employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmo.R;

public class ConsultUser extends AppCompatActivity {

    TextView tvName, tvSubNamePather, tvSubNameMother, tvPhone, tvType, tvAddres, tvNumberAddres, tvCp, tvColony, tvCity, tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_user);
        String name = "";
        String subNamePather = "";
        String subNameMother ="";
        String phone = "";
        String type = "";
        String addres = "";
        String numberAddres = "";
        String cp = "";
        String colony = "";
        String city = "";
        String state = "";
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            name = extras.getString("txtName");
            subNamePather = extras.getString("txtSubNamePather");
            subNameMother = extras.getString("txtSubNameMother");
            phone  = extras.getString("txtPhone");
            type  = extras.getString("txtType");
            addres = extras.getString("txtAddres");
            numberAddres = extras.getString("txtNumberAddres");
            cp =extras.getString("txtCp");
            colony = extras.getString("txtColony");
            city = extras.getString("txtCity");
            state = extras.getString("txtState");

            tvName = findViewById(R.id.txtName);
            tvName.setText(name);

            tvSubNamePather = findViewById(R.id.txtSubNamePather);
            tvSubNamePather.setText(subNamePather);

            tvSubNameMother = findViewById(R.id.txtSubNameMother);
            tvSubNameMother.setText(subNameMother);

            tvPhone = findViewById(R.id.txtPhone);
            tvPhone.setText(phone);

            tvType = findViewById(R.id.txtType);
            tvType.setText(type);

            tvAddres = findViewById(R.id.txtAddres);
            tvAddres.setText(addres);

            tvNumberAddres = findViewById(R.id.txtNumberAddres);
            tvNumberAddres.setText(numberAddres);

            tvCp = findViewById(R.id.txtCp);
            tvCp.setText(cp);

            tvColony = findViewById(R.id.txtColony);
            tvColony.setText(colony);

            tvCity= findViewById(R.id.txtCity);
            tvCity.setText(city);

            tvState = findViewById(R.id.txtState);
            tvState.setText(state);
        }

    }
}
