package com.example.appmo.Sell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum ManagerFragmentSell {

    SELL_INDEX {
        @Override
        public Fragment execute(MainContainerSell activity) {
            return setFragment(activity, R.id.FragmentContainerSell, SellIndex.class);
        }
    } ;


    public abstract Fragment execute(MainContainerSell activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerSell activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentSell setState(ManagerFragmentSell newState) {
        ManagerFragmentSell state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentSell setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }
}
