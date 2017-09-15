package me.lsran.gankapp.internal.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.lsran.gankapp.data.repository.GankRepository;
import me.lsran.gankapp.internal.di.modules.ApplicationModule;
import me.lsran.gankapp.internal.di.modules.ConfigModule;
import me.lsran.gankapp.internal.di.modules.ServiceModule;
import me.lsran.gankapp.ui.base.BaseActivity;

@Singleton
@Component(modules = {ApplicationModule.class, ConfigModule.class, ServiceModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    // Exposed to sub-graphs.
    Context context();

    GankRepository gankRepository();
}
