package me.lsran.gankapp.presenters;

import android.support.annotation.NonNull;

import com.google.common.base.Optional;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import me.lsran.gankapp.data.DefaultSubscriber;
import me.lsran.gankapp.data.repository.GankRepository;
import me.lsran.gankapp.internal.di.PerActivity;
import me.lsran.gankapp.model.GankDataModel;
import me.lsran.gankapp.ui.gank.view.GankListView;

import static com.google.common.base.Preconditions.checkNotNull;
import static me.lsran.gankapp.utils.Logger.LOGE;
import static me.lsran.gankapp.utils.Logger.makeLogTag;

/**
 * author lssRan
 */

@PerActivity
public class GirlPresenter {

    private static final String TAG = makeLogTag(GirlPresenter.class);

    private GankRepository repository;

    private GankListView viewGankListView;

    private int pageNum = 1;

    @Inject
    public GirlPresenter(GankRepository repository) {
        this.repository = repository;
    }

    public void setView(@NonNull GankListView view) {
        this.viewGankListView = checkNotNull(view);
    }

    /**
     * 获取房源列表
     */
    private void getData() {
        repository.getGankData(pageNum)
                .compose(((RxAppCompatActivity) viewGankListView.context()).bindToLifecycle())
                .subscribe(new GetGankListSubscriber());
    }

    /**
     * 加载
     */
    public void loadData(){
        pageNum++;
        getData();
    }

    /**
     * 刷新
     */
    public void refreshData(){
        pageNum = 1;
        getData();
    }

    /**
     * API获取房源列表订阅者
     */
    private final class GetGankListSubscriber extends DefaultSubscriber<Optional<GankDataModel>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
            viewGankListView.cancelRefreshView();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LOGE(TAG, e.getMessage());
            viewGankListView.cancelRefreshView();
        }

        @Override
        public void onNext(Optional<GankDataModel> listOptional) {
            super.onNext(listOptional);
            if (listOptional.isPresent()) {
                viewGankListView.showHouseCollectionInView(listOptional.get());
            }
        }
    }
}