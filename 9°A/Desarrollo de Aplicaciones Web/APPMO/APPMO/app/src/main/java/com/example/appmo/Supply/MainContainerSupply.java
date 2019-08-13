package com.example.appmo.Supply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appmo.R;

public class MainContainerSupply extends AppCompatActivity {
    public static ManagerFragmentSupply states;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_supply);
        changeFragment(ManagerFragmentSupply.SUPPLY);

    }

    public void changeFragment(ManagerFragmentSupply states) {
        this.states = ManagerFragmentSupply.setState(states);
        this.states.execute(this);
    }
}
