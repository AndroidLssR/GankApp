package me.lsran.gankapp.internal.di.components;

import android.app.Activity;

import dagger.Component;
import me.lsran.gankapp.internal.di.PerActivity;
import me.lsran.gankapp.internal.di.modules.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
