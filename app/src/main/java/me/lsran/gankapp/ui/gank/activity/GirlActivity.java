package me.lsran.gankapp.ui.gank.activity;

import android.os.Bundle;

import me.lsran.gankapp.R;
import me.lsran.gankapp.internal.di.HasComponent;
import me.lsran.gankapp.internal.di.components.DaggerFragmentComponent;
import me.lsran.gankapp.internal.di.components.FragmentComponent;
import me.lsran.gankapp.ui.base.BaseActivity;
import me.lsran.gankapp.ui.gank.fragment.GirlFragment;


public class GirlActivity extends BaseActivity implements HasComponent<FragmentComponent> {

    private FragmentComponent girlComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeActivity();
        initializeInjector();
    }

    /**
     * 获取用户数据并显示Fragment
     */
    private void initializeActivity() {
            addFragment(R.id.fragment_container, GirlFragment.newInstance());
    }

    /**
     * 初始化Activity依赖注入
     */
    private void initializeInjector() {
        girlComponent = DaggerFragmentComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public FragmentComponent getComponent() {
        return girlComponent;
    }
}
