package com.android.gank_demo.data.repository;

import com.android.gank_demo.data.api.GankIoService;
import com.android.gank_demo.model.entity.ApiResult;
import com.android.gank_demo.model.entity.GankApiResult;
import com.android.gank_demo.model.entity.GankDataModel;
import com.android.gank_demo.utils.ReactivexCompat;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GankIoRepository {

    private GankIoService mService;

    @Inject
    public GankIoRepository(GankIoService commonService) {

        this.mService = commonService;
    }
    public Single<List<GankDataModel>> getGankData(String category,int pageNum, int pageSize) {
        return this.mService.getGankData(category,pageNum, pageSize).compose(ReactivexCompat.singleTransform());
    }

}
