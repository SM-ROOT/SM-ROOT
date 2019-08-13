package com.example.appmo.Client;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum ManagerFragmentClient {

    /**
     *Modelo de Fragment
     **/
    CLIENTINDEX {
        @Override
        public Fragment execute(MainContainerClient activity) {
            return setFragment(activity, R.id.FragmentContainerClient, ClientIndex.class);
        }
    },
    CLIENTADD {
        @Override
        public Fragment execute(MainContainerClient activity) {
            return setFragment(activity, R.id.FragmentContainerClient, ClientAdd.class);
        }
    };


    public abstract Fragment execute(MainContainerClient activity);

    //Variables
    public Bundle bundle;

    /**
     *Controlador para poder asignar la vista de un fragment a contenedor de Fragmen
     * **/
    public Fragment setFragment(MainContainerClient activity, int id, Class<? extends Fragment> fragmentclass) {

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

    /**
     *Paso de estado de un fragement para poder acomodar un fragment
     * **/
    public static ManagerFragmentClient setState(ManagerFragmentClient newState) {
        ManagerFragmentClient state = newState;
        return state;
    }

    /**
     *Uso de paramentros
     * **/
    public Bundle getBundle() {
        return bundle;
    }

    /**
     *Nombre del evento para pasar Fragment
     * **/
    public ManagerFragmentClient setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}