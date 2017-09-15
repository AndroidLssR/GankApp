package me.lsran.gankapp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.lsran.gankapp.R;
import me.lsran.gankapp.ui.base.BaseActivity;

/**
 * @author lssRan
 */

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
