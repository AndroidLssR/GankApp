package me.lsran.gankapp.presenters;

import android.support.annotation.NonNull;

import com.google.common.base.Optional;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import me.lsran.gankapp.data.DefaultSubscriber;
import me.lsran.gankapp.data.repository.GankRepository;
import me.lsran.gankapp.internal.di.PerActivity;
import me.lsran.gankapp.model.GankApiModel;
import me.lsran.gankapp.ui.view.SplashView;

import static com.google.common.base.Preconditions.checkNotNull;
import static me.lsran.gankapp.utils.Logger.LOGE;
import static me.lsran.gankapp.utils.Logger.makeLogTag;

/**
 * @author lssRan
 */
@PerActivity
public class SplashPresenter {

    private static final String TAG = makeLogTag(SplashPresenter.class);

    private GankRepository repository;

    private SplashView splashView;

    private int pageNum = 1;

    private int pageSize = 1;

    @Inject
    public SplashPresenter(GankRepository repository) {
        this.repository = repository;
    }

    public void setView(@NonNull SplashView view) {
        this.splashView = checkNotNull(view);
    }

    /**
     * 获取启动页图片
     */
    public void getData() {
        repository.getGankData(pageSize,pageNum)
                .compose(((RxAppCompatActivity) splashView.context()).bindToLifecycle())
                .subscribe(new GetSplashImageSubscriber());
    }

    /**
     * API获取房源列表订阅者
     */
    private final class GetSplashImageSubscriber extends DefaultSubscriber<Optional<GankApiModel>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LOGE(TAG, e.getMessage());
        }

        @Override
        public void onNext(Optional<GankApiModel> listOptional) {
            super.onNext(listOptional);
            if (listOptional.isPresent()) {
                splashView.setSplashImageInView(listOptional.get());
            }
        }
    }
}