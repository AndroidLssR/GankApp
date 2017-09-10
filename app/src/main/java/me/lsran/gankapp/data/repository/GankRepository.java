package me.lsran.gankapp.data.repository;

import com.google.common.base.Optional;

import java.util.List;

import javax.inject.Inject;

import me.lsran.gankapp.data.api.GankService;
import me.lsran.gankapp.model.GankDataModel;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * author lssRan
 */

public class GankRepository extends DataRepository {

    private static final int PAGE_SIZE = 10;

    /**
     * gank data网络请求服务
     */
    private GankService mGankService;

    @Inject
    GankRepository(Retrofit serviceGenerator) {
        mGankService = serviceGenerator.create(GankService.class);
    }

    public Observable<Optional<GankDataModel>> getGankData(int pageNum) {
        return transform(mGankService.getGankData(PAGE_SIZE,pageNum));
    }
}
