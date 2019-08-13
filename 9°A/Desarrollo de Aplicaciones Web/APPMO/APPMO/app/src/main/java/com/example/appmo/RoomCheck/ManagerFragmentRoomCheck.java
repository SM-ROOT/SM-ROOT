package com.example.appmo.RoomCheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;
import com.example.appmo.RoomCheck.BreadController.BreadAdd;
import com.example.appmo.RoomCheck.BreadController.BreadIndex;
import com.example.appmo.RoomCheck.OrderController.RoomCheckIndex;

public enum ManagerFragmentRoomCheck {
    ROOM_CHECK {
        @Override
        public Fragment execute(MainContainerRoomCheck activity) {
            return setFragment(activity, R.id.FragmentContainerRoomCheck, RoomCheckIndex.class);
        }
    },

    BREAD {
        @Override
        public Fragment execute(MainContainerRoomCheck activity) {
            return setFragment(activity, R.id.FragmentContainerRoomCheck, BreadIndex.class);
        }
    },

    BREAD_ADD {
        @Override
        public Fragment execute(MainContainerRoomCheck activity) {
            return setFragment(activity, R.id.FragmentContainerRoomCheck, BreadAdd.class);
        }
    };

    public abstract Fragment execute(MainContainerRoomCheck activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerRoomCheck activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentRoomCheck setState(ManagerFragmentRoomCheck newState) {
        ManagerFragmentRoomCheck state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentRoomCheck setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}