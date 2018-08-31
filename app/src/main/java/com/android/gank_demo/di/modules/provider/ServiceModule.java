package com.android.gank_demo.di.modules.provider;


import com.android.gank_demo.data.api.GankIoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    public GankIoService provideCommonService(Retrofit retrofit) {
        return retrofit.create(GankIoService.class);
    }

}
