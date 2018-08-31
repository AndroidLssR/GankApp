package com.android.gank_demo.data.api;


import com.android.gank_demo.model.entity.ApiResult;
import com.android.gank_demo.model.entity.GankApiResult;
import com.android.gank_demo.model.entity.GankDataModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankIoService {

    @GET("data/{category}/{pageSize}/{pageNum}")
    Single<ApiResult<List<GankDataModel>>> getGankData(@Path("category") String category,
                                                       @Path("pageNum") int pageNum,
                                                       @Path("pageSize") int pageSize);

}
