package com.example.appmo.Recipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.appmo.R;

public enum ManagerFragmentRecipe {

    RECIPEINDEX {
        @Override
        public Fragment execute(MainContainerRecipe activity) {
            return setFragment(activity, R.id.FragmentContainerRecipe, RecipeIndex.class);
        }
    },
    RECIPEADD {
        @Override
        public Fragment execute(MainContainerRecipe activity) {
            return setFragment(activity, R.id.FragmentContainerRecipe, RecipeAdd.class);
        }
    },
    RECIPECONSULT {
        @Override
        public Fragment execute(MainContainerRecipe activity) {
            return setFragment(activity, R.id.FragmentContainerRecipe, SearchRecipe.class);
        }
    };


    public abstract Fragment execute(MainContainerRecipe activity);

    //Variables
    public Bundle bundle;

    public Fragment setFragment(MainContainerRecipe activity, int id, Class<? extends Fragment> fragmentclass) {

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

    public static ManagerFragmentRecipe setState(ManagerFragmentRecipe newState) {
        ManagerFragmentRecipe state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ManagerFragmentRecipe setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}