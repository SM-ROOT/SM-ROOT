package com.example.appmo.Sell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmo.R;


public class DetailsConsult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_consult);

        String txtName = "";
        String txtDate = "";
        String txtType = "";
        String txtDestination = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            txtName = extras.getString("txtName");
            txtDate = extras.getString("txtDate");
            txtType = extras.getString("txtType");
            txtDestination = extras.getString("txtDestination");
        }

        TextView tvName = findViewById(R.id.showName);
        tvName.setText(txtName);

        TextView tvDate = findViewById(R.id.txtDate);
        tvDate.setText(txtDate);

        TextView tvType = findViewById(R.id.txtType);
        tvType.setText(txtType);

        TextView tvDestination = findViewById(R.id.txtPhone);
        tvDestination.setText(txtDestination);
    }
}
