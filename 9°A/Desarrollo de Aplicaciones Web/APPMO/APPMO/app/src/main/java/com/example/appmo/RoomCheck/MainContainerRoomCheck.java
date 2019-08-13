package com.example.appmo.RoomCheck;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.appmo.R;

public class MainContainerRoomCheck extends AppCompatActivity {

    public static ManagerFragmentRoomCheck states;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.roomCheck:
                    changeFragment(ManagerFragmentRoomCheck.ROOM_CHECK);
                    return true;
                case R.id.bread:
                    changeFragment(ManagerFragmentRoomCheck.BREAD);
                    return true;


            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_room_check);
        changeFragment(ManagerFragmentRoomCheck.ROOM_CHECK);

        BottomNavigationView navigation = findViewById(R.id.navRoomCheck);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void changeFragment(ManagerFragmentRoomCheck states){
        this.states= ManagerFragmentRoomCheck.setState(states);
        this.states.execute(this);
    }


}
