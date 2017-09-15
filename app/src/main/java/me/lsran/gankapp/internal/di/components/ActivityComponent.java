package me.lsran.gankapp.internal.di.components;

import android.app.Activity;

import dagger.Component;
import me.lsran.gankapp.internal.di.PerActivity;
import me.lsran.gankapp.internal.di.modules.ActivityModule;
import me.lsran.gankapp.ui.splash.SplashActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();

    void inject(SplashActivity splashActivity);
}
