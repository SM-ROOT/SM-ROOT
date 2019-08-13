package com.example.appmo.Purchases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appmo.R;

public class MainContainerRoomStock extends AppCompatActivity {

    public static ManagerFragmentRoomStock states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_room_stock);
        changerFragment(ManagerFragmentRoomStock.ROOMSTOCKINDEX);
    }

    public void changerFragment(ManagerFragmentRoomStock states){
        this.states= ManagerFragmentRoomStock.setState(states);
        this.states.execute(this);
    }
}
