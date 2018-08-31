package com.android.gank_demo.di.components;


import com.android.gank_demo.App;
import com.android.gank_demo.di.modules.ApplicationModule;
import com.android.gank_demo.di.modules.builders.MainBuilderModule;
import com.android.gank_demo.di.modules.provider.ConfigModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ConfigModule.class,
        MainBuilderModule.class,
})
public interface ApplicationComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
        abstract Builder configModule(ConfigModule configModule);

        @Override
        public void seedInstance(App instance) {
            configModule(new ConfigModule(instance));
        }
    }

}
