package com.example.appmo.Splash;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appmo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splash extends Fragment {
    View view;
    public static ManagerFragmentSplash states;


    public Splash() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_splash, container, false);
        connectionServer();
        return view;
    }

    private void connectionServer() {
        if (isOnline(view.getContext())) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeFragment(ManagerFragmentSplash.LOGIN);
                }
            }, 1000);
        } else {
            Toast.makeText(getContext(), getString(R.string.lookConnection), Toast.LENGTH_SHORT).show();

        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public void changeFragment(ManagerFragmentSplash states) {
        this.states = ManagerFragmentSplash.setState(states);
        this.states.execute((MainContainerSplash) getContext());
    }
}
