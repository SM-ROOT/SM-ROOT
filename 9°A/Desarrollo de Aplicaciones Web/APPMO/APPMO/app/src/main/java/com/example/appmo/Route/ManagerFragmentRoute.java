package com.example.appmo.Route;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum  ManagerFragmentRoute {

    ROUTE_INDEX {
        @Override
        public Fragment execute(MainContainerRoute activity) {
            return setFragment(activity, R.id.FragmentContainerRoute, RouteIndex.class);
        }
    },

    ROUTE_ADD {
        @Override
        public Fragment execute(MainContainerRoute activity) {
            return setFragment(activity, R.id.FragmentContainerRoute, RouteAdd.class);
        }
    }
    ;


    public abstract Fragment execute(MainContainerRoute activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerRoute activity, int id, Class<? extends Fragment> fragmentclass) {

        Fragment newFragment = null;
        try {
            newFragment = fragmentclass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, newFragment, fragmentclass.getCanonicalName());
        transaction.commit();
        return newFragment;
    }

    public static ManagerFragmentRoute setState(ManagerFragmentRoute newState) {
        ManagerFragmentRoute state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentRoute setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }
}
