package com.android.gank_demo.ui.module.main.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.text.TextUtils;

import com.android.gank_demo.BuildConfig;
import com.android.gank_demo.R;
import com.android.gank_demo.data.repository.GankIoRepository;
import com.android.gank_demo.frame.BasePresenter;
import com.android.gank_demo.model.annotation.Category;
import com.android.gank_demo.model.entity.GankDataModel;
import com.android.gank_demo.reactivex.DefaultDisposableSingleObserver;

import java.util.List;

import javax.inject.Inject;

import static com.android.gank_demo.model.annotation.ArgumentsParameter.ARGS_TAB_TITLE;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    GankIoRepository mGankIoRepository;

    private String mTitle;

    private int pageNum = 1;

    @Inject
    public MainPresenter(GankIoRepository gankIoRepository) {
        this.mGankIoRepository = gankIoRepository;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void initializeData() {
        mTitle = getArguments().getString(ARGS_TAB_TITLE);

    }

    @Override
    public void refreshData() {
        if (TextUtils.isEmpty(mTitle)) {
            getView().showToast("参数错误！");
            getView().closeRefreshLayout();
            return;
        }
        pageNum = 1;
        loadCategoryData();
    }

    @Override
    public void loadmoreData() {
        if (TextUtils.isEmpty(mTitle)) {
            getView().showToast("参数错误！");
            getView().closeRefreshLayout();
            return;
        }

        loadCategoryData();
    }

    void loadCategoryData() {
        mGankIoRepository.getGankData(mTitle, pageNum, BuildConfig.PAGE_SIZE)
                .compose(getView().getLifecycleProvider().bindToLifecycle())
                .subscribe(new DefaultDisposableSingleObserver<List<GankDataModel>>() {
                    @Override
                    public void onSuccess(List<GankDataModel> list) {
                        if (list != null && !list.isEmpty()) {
                            pageNum++;
                            getView().showData(list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().closeRefreshLayout();
                    }
                });
    }
}
