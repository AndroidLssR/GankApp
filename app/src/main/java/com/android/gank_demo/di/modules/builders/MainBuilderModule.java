package com.android.gank_demo.di.modules.builders;

import com.android.gank_demo.di.ActivityScope;
import com.android.gank_demo.ui.module.main.activity.MainActivity;
import com.android.gank_demo.ui.module.main.fragment.GankFragment;
import com.android.gank_demo.ui.module.welfare.activity.WelfareActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 主页相关
 */
@Module
public abstract class MainBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivityInject();

    @ActivityScope
    @ContributesAndroidInjector
    abstract WelfareActivity welfareActivityInject();

    @ActivityScope
    @ContributesAndroidInjector
    abstract GankFragment mainFragment();

}
