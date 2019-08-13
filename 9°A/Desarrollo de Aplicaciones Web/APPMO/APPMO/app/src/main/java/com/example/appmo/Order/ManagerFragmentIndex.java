package com.example.appmo.Order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum ManagerFragmentIndex {

    NO_START {
        @Override
        public Fragment execute(MainContainerIndex activity) {
            return setFragment(activity, R.id.FragmentContainerIndex, OrderIndex.class);
        }
    },
    ADD_ORDER {
        @Override
        public Fragment execute(MainContainerIndex activity) {
            return setFragment(activity, R.id.FragmentContainerIndexAdd, OrderAdd.class);
        }
    };


    public abstract Fragment execute(MainContainerIndex activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerIndex activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentIndex setState(ManagerFragmentIndex newState) {
        ManagerFragmentIndex state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentIndex setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}