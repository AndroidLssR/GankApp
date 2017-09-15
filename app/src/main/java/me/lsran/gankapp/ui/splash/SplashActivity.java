package me.lsran.gankapp.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lsran.gankapp.R;
import me.lsran.gankapp.internal.di.components.DaggerActivityComponent;
import me.lsran.gankapp.model.GankApiModel;
import me.lsran.gankapp.model.GankDataModel;
import me.lsran.gankapp.presenters.SplashPresenter;
import me.lsran.gankapp.ui.base.BaseActivity;
import me.lsran.gankapp.ui.view.SplashView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author lssRan
 */

public class SplashActivity extends BaseActivity implements SplashView {

    @BindView(R.id.image_splash)
    ImageView mImageSplash;

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_splash);
        ButterKnife.bind(this);
        initializeInjector();
        initializeSplashImage();
    }

    private void initializeSplashImage() {
        splashPresenter.setView(this);
        splashPresenter.getData();
    }

    @Override
    public void setSplashImageInView(GankApiModel imageData) {
        checkNotNull(imageData);
        for (GankDataModel gankDataModel : imageData.getResults()) {
            Glide.with(this).load(gankDataModel.getUrl()).into(mImageSplash);
        }
    }

    /**
     * 初始化Activity依赖注入
     */
    private void initializeInjector() {
        DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    public Context context() {
        return this;
    }
}
