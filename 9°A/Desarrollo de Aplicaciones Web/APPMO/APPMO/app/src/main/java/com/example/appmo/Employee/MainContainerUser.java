package com.example.appmo.Employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appmo.R;

public class MainContainerUser extends AppCompatActivity {

    public static ManagerFragmentUser state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_user);
        changeFragment(ManagerFragmentUser.USERINDEX);
    }

    private void changeFragment(ManagerFragmentUser state) {
        this.state= ManagerFragmentUser.setState(state);
        this.state.execute(this);
    }
}
