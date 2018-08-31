package com.android.gank_demo.ui.module.welfare.presenter;


import android.annotation.SuppressLint;

import com.android.gank_demo.BuildConfig;
import com.android.gank_demo.data.manager.ImageManager;
import com.android.gank_demo.data.repository.GankIoRepository;
import com.android.gank_demo.di.ActivityScope;
import com.android.gank_demo.frame.BasePresenter;
import com.android.gank_demo.model.entity.GankDataModel;
import com.android.gank_demo.reactivex.DefaultDisposableSingleObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.android.gank_demo.model.annotation.Category.CATEGORY_WELFARE;

@ActivityScope
public class WelfarePresenter extends BasePresenter<WelfareContract.View> implements WelfareContract.Presenter {

    @Inject
    GankIoRepository mGankIoRepository;

    ImageManager mImageManger;

    private int pageNum = 1;

    @Inject
    public WelfarePresenter(GankIoRepository gankIoRepository, ImageManager imageManager) {
        this.mGankIoRepository = gankIoRepository;
        this.mImageManger = imageManager;
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void refreshData() {
        pageNum = 1;
        loadData();
    }

    @Override
    public void loadmoreData() {
        loadData();
    }

    public void loadData() {
        this.mGankIoRepository.getGankData(CATEGORY_WELFARE,pageNum, BuildConfig.PAGE_SIZE).
                compose(getView().getLifecycleProvider().bindToLifecycle())
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

    @SuppressLint("CheckResult")
    @Override
    public void saveImage(GankDataModel model) {
        if (model == null) return;
        mImageManger.saveTheImage(model.getUrl())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> getView().showToast(uri.toString()),
                        error ->  getView().showToast(error.getMessage()));
    }
}
