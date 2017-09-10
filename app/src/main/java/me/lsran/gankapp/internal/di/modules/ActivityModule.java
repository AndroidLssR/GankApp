package me.lsran.gankapp.internal.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import me.lsran.gankapp.internal.di.PerActivity;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    Activity activity() {
        return activity;
    }

}
