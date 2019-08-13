package com.example.appmo.Client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appmo.R;

public class MainContainerClient extends AppCompatActivity {
    public static  ManagerFragmentClient state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_client);
        changeFragment(ManagerFragmentClient.CLIENTINDEX);

    }

    /**
     *Instancia de Fragment
     * **/
    private void changeFragment(ManagerFragmentClient state){
        this.state = ManagerFragmentClient.setState(state);
        this.state.execute(this);
    }
}
