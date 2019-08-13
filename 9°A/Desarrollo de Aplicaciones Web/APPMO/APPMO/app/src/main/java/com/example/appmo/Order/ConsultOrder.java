package com.example.appmo.Order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmo.R;

public class ConsultOrder extends AppCompatActivity {

    TextView tvName, tvDestination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_order);
        Bundle extra = getIntent().getExtras();
        String name = "";
        String destination = "";

        if (extra!=null){
        name = extra.getString("txtName");
        destination = extra.getString("txtDestination");

        tvName = findViewById(R.id.txtName);
        tvName.setText(name);

        tvDestination = findViewById(R.id.txtPhone);
        tvDestination.setText(destination);
        }
    }
}
