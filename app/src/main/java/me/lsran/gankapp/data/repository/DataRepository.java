package me.lsran.gankapp.data.repository;

import com.google.common.base.Optional;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 数据仓库虚基类，处理网络线程调度等
 */
public abstract class DataRepository {

    /**
     * 转换操作，执行线程调度
     *
     * @param observable {@link Observable}
     * @param <T>        数据实体
     * @return {@link Observable}
     */
    protected <T> Observable<Optional<T>> transform(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(t -> Observable.just(Optional.of(t)));
    }
}
