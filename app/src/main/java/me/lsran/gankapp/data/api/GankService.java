package me.lsran.gankapp.data.api;

import java.util.List;

import me.lsran.gankapp.model.GankApiModel;
import me.lsran.gankapp.model.GankDataModel;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * author lssRan
 */

public interface GankService {

    @GET("data/福利/{pageSize}/{pageNum}")
    Observable<GankApiModel> getGankData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);
}
