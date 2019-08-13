package com.example.appmo.Employee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum ManagerFragmentUser {

    USERINDEX {
        @Override
        public Fragment execute(MainContainerUser activity) {
            return setFragment(activity, R.id.FragmentContainerUser, UserIndex.class);
        }
    },
    ADD_USER {
        @Override
        public Fragment execute(MainContainerUser activity) {
            return setFragment(activity, R.id.FragmentContainerUser, UserAdd.class);
        }
    };


    public abstract Fragment execute(MainContainerUser activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerUser activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentUser setState(ManagerFragmentUser newState) {
        ManagerFragmentUser state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentUser setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}