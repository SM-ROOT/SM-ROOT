package com.example.appmo.Recipe;

import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appmo.R;


public class MainContainerRecipe extends AppCompatActivity {
    public  static ManagerFragmentRecipe state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container_recipe);
        changeFragment(ManagerFragmentRecipe.RECIPEINDEX);

    }

    private void  changeFragment(ManagerFragmentRecipe state){
        this.state = ManagerFragmentRecipe.setState(state);
        this.state.execute(this);
    }
}
