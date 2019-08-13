package com.example.appmo.Sell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appmo.R;

public class MainContainerSell extends AppCompatActivity {
    public static ManagerFragmentSell state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_sell);
        changeFragment(ManagerFragmentSell.SELL_INDEX);
    }

    private void changeFragment(ManagerFragmentSell state) {
        this.state = ManagerFragmentSell.setState(state);
        this.state.execute(this);
    }
}
