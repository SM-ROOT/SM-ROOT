package com.example.appmo.Supply;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.appmo.R;
import com.example.appmo.infoIP;

import java.util.HashMap;

public class SupplyEdit extends AppCompatActivity {

    EditText txtRfc, txtPhone, txtMail, txtAddres, txtNumberAddres, txtCp,
            txtColony, txtCity;
    String ip= new infoIP().getIp();
    String HttpURL = "https://"+ip+"/Student/UpdateStudent.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    //HttpParse httpParse = new HttpParse();
    EditText StudentName, StudentPhoneNumber, StudentClass;
    Button UpdateStudent;
    String IdHolder, StudentNameHolder, StudentPhoneHolder, StudentClassHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_supply);
        String rfc = "";
        String number = "";
        String mail = "";
        String addres = "";
        String numberAddres = "";
        String cp = "";
        String colony = "";
        String city = "";
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
        }

        txtRfc = findViewById(R.id.txtName);
        txtRfc.setText(rfc);

        txtPhone = findViewById(R.id.txtPhone);
        txtPhone.setText(number);

        txtMail = findViewById(R.id.txtMeasure);
        txtMail.setText(mail);

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

    }
}
