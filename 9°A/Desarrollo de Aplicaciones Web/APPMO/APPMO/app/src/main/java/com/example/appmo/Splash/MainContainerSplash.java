package com.example.appmo.Splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.appmo.R;


public class MainContainerSplash extends AppCompatActivity {
    public static ManagerFragmentSplash states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        changeFragment(ManagerFragmentSplash.LOGIN);
    }

    private void changeFragment(ManagerFragmentSplash states) {
        this.states = ManagerFragmentSplash.setState(states);
        this.states.execute(this);
    }
}
