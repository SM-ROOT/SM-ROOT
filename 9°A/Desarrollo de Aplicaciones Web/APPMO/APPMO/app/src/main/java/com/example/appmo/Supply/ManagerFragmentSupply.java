package com.example.appmo.Supply;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum ManagerFragmentSupply {
    SUPPLY {
        @Override
        public Fragment execute(MainContainerSupply activity) {
            return setFragment(activity, R.id.FragmentContainerSupply, SupplyIndex.class);
        }
    },
    ADDSUPPLY {
        @Override
        public Fragment execute(MainContainerSupply activity) {
            return setFragment(activity, R.id.FragmentContainerSupply, SupplyAdd.class);
        }
    };


    public abstract Fragment execute(MainContainerSupply activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerSupply activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentSupply setState(ManagerFragmentSupply newState) {
        ManagerFragmentSupply state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentSupply setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}