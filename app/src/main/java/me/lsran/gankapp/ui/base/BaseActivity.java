package me.lsran.gankapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import me.lsran.gankapp.App;
import me.lsran.gankapp.internal.di.components.ApplicationComponent;
import me.lsran.gankapp.internal.di.modules.ActivityModule;
import me.lsran.gankapp.navigator.Navigator;

/**
 * All Activities show extends this.
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    @Inject
    protected Navigator navigator;  // router

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * 添加Fragment
     *
     * @param containerViewId 承载Fragment容器id
     * @param fragment        fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}

