package com.example.appmo.Splash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;


public enum ManagerFragmentSplash {
    SPLASH {
        @Override
        public Fragment execute(MainContainerSplash activity) {
            return setFragment(activity, R.id.FragmentContainerSplash, Splash.class);
        }
    },
    NEWPASSWORD {
        @Override
        public Fragment execute(MainContainerSplash activity) {
            return setFragment(activity, R.id.FragmentContainerSplash, NewPassword.class);
        }
    },
    LOGIN {
        @Override
        public Fragment execute(MainContainerSplash activity) {
            return setFragment(activity, R.id.FragmentContainerSplash, Login.class);
        }
    };


    public abstract Fragment execute(MainContainerSplash activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerSplash activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentSplash setState(ManagerFragmentSplash newState) {
        ManagerFragmentSplash state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentSplash setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}