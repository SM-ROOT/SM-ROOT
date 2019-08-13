package com.example.appmo.Purchases;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum ManagerFragmentRoomStock {

    ROOMSTOCKINDEX {
        @Override
        public Fragment execute(MainContainerRoomStock activity) {
            return setFragment(activity, R.id.FragmentContainerRoomStock, RoomStockIndex.class);
        }
    },

    PRODUCTADD {
        @Override
        public Fragment execute(MainContainerRoomStock activity) {
            return setFragment(activity, R.id.FragmentContainerRoomStock, AddPurchanseMaterial.class);
        }
    },
    ADDPURCHASE {
        @Override
        public Fragment execute(MainContainerRoomStock activity) {
            return setFragment(activity, R.id.FragmentContainerRoomStock, AddPurchase.class);
        }
    }
    ;


    public abstract Fragment execute(MainContainerRoomStock activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerRoomStock activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentRoomStock setState(ManagerFragmentRoomStock newState) {
        ManagerFragmentRoomStock state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentRoomStock setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}