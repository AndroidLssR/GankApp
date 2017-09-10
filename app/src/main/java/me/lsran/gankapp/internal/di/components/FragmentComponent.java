package me.lsran.gankapp.internal.di.components;

import dagger.Component;
import me.lsran.gankapp.internal.di.PerActivity;
import me.lsran.gankapp.internal.di.modules.ActivityModule;
import me.lsran.gankapp.ui.gank.GirlFragment;


/**
 * 后期按照模块划分
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface FragmentComponent {

    void inject(GirlFragment fragment);
}
