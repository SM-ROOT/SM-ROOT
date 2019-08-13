package com.example.appmo.Order;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appmo.Client.MainContainerClient;
import com.example.appmo.R;
import com.example.appmo.Recipe.MainContainerRecipe;
import com.example.appmo.RoomCheck.MainContainerRoomCheck;
import com.example.appmo.Purchases.MainContainerRoomStock;
import com.example.appmo.Route.MainContainerRoute;
import com.example.appmo.Supply.MainContainerSupply;
import com.example.appmo.Employee.MainContainerUser;

public class MainContainerIndex extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static ManagerFragmentIndex states;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_drawer);
        showToolBar();
        changeFragment(ManagerFragmentIndex.NO_START);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void showToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        final ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.order));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_drawer);
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainContainerIndex.this, getString(R.string.toastNavi), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_index_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemLogOut:
                openAlert();
                break;

            case R.id.itemAdd:
                changeFragment(ManagerFragmentIndex.ADD_ORDER);
                break;

            case R.id.itemPdf:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.itemSearch:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     *Evento que controla el elemento que contiene todos el index de todos los modulos
     * **/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle nav_index view item clicks here.
        int id = item.getItemId();

        if (id == R.id.roomStock) {
            Intent intentRoomStock = new Intent(MainContainerIndex.this, MainContainerRoomStock.class);
            startActivity(intentRoomStock);

        } else if (id == R.id.getResourse) {
            Intent intentReport = new Intent(MainContainerIndex.this, MainContainerSupply.class);
            startActivity(intentReport);

        } else if (id == R.id.client) {
            Intent intentClient = new Intent(MainContainerIndex.this, MainContainerClient.class);
            startActivity(intentClient);

        } else if (id == R.id.recipe) {
            Intent intentRecipe = new Intent(MainContainerIndex.this, MainContainerRecipe.class);
            startActivity(intentRecipe);

        } else if (id == R.id.roomCheck) {
            Intent intentRoomCheck = new Intent(MainContainerIndex.this, MainContainerRoomCheck.class);
            startActivity(intentRoomCheck);

        } else if (id == R.id.user) {
            Intent intentUser = new Intent(MainContainerIndex.this, MainContainerUser.class);
            startActivity(intentUser);

        } else if (id == R.id.sell) {
            Intent intentSell = new Intent(MainContainerIndex.this, com.example.appmo.Sell.MainContainerSell.class);
            startActivity(intentSell);

        } else if (id == R.id.route) {
            Intent intentRoute = new Intent(MainContainerIndex.this, MainContainerRoute.class);
            startActivity(intentRoute);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void openAlert() {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(getString(R.string.messageAlertTitle));
        alertDialogBuilder.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.exit(0);
            }
        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    public void changeFragment(ManagerFragmentIndex states) {
        this.states = ManagerFragmentIndex.setState(states);
        this.states.execute(this);
    }


}
