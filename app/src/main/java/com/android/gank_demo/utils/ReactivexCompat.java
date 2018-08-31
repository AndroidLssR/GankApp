package com.android.gank_demo.utils;

import android.accounts.NetworkErrorException;
import android.support.v4.util.ObjectsCompat;

import com.android.gank_demo.data.exception.AccessTokenException;
import com.android.gank_demo.data.exception.BadRequestException;
import com.android.gank_demo.data.exception.BusinessException;
import com.android.gank_demo.data.exception.ResourceNotFoundException;
import com.android.gank_demo.data.exception.ResultFailException;
import com.android.gank_demo.data.exception.ServiceHintException;
import com.android.gank_demo.data.exception.UnknownException;
import com.android.gank_demo.model.entity.ApiResult;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.android.gank_demo.model.annotation.ResultCode.BAD_REQUEST;
import static com.android.gank_demo.model.annotation.ResultCode.FORBIDDEN;
import static com.android.gank_demo.model.annotation.ResultCode.INTERNAL_SERVER_ERROR;
import static com.android.gank_demo.model.annotation.ResultCode.NOT_FOUND;
import static com.android.gank_demo.model.annotation.ResultCode.SERVER_HINT;
import static com.android.gank_demo.model.annotation.ResultCode.SUCCESS;


public class ReactivexCompat {

    /**
     * Single网络api转换并包含线程调度
     */
    public static <T> SingleTransformer<ApiResult<T>, T> singleTransform() {
        return upstream -> upstream.compose(singleThreadSchedule())
                .filter(t -> !ObjectsCompat.equals(t, null))
                .switchIfEmpty(observer -> observer.onError(new NetworkErrorException()))
                .flatMap(apiResult -> {
                    if (!apiResult.isError()) {
                        if (apiResult.getData() == null) {
                            apiResult.setData((T) new Object());
                        }
                        return Maybe.just(apiResult.getData());
                    } else {
                        return processResultError(apiResult);
                    }
                })
                .toSingle();
    }

    /**
     * Single网络api转换
     */
    public static <T> SingleTransformer<ApiResult<T>, T> singleTransformNoThreadSchedule() {
        return upstream -> upstream
                .filter(t -> !ObjectsCompat.equals(t, null))
                .switchIfEmpty(observer -> observer.onError(new NetworkErrorException()))
                .flatMap(apiResult -> {
                    if (!apiResult.isError()) {
                        if (apiResult.getData() == null) {
                            apiResult.setData((T) new Object());
                        }
                        return Maybe.just(apiResult.getData());
                    } else {
                        return processResultError(apiResult);
                    }
                })
                .toSingle();
    }

    /**
     * 处理网络异常
     */
    private static Maybe processResultError(ApiResult apiResult) {
        boolean error = apiResult.isError();
        if (error) {
            return Maybe.error(new BusinessException("数据拉取失败", error));
        }
        return Maybe.error(new UnknownException());
    }

    /**
     * Maybe线程调度
     */
    public static <T> MaybeTransformer<T, T> maybeThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *
     */
    public static <T> SingleTransformer<T, T> singleThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Observable线程调度
     */
    public static <T> ObservableTransformer<T, T> observableThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * flowable线程调度
     */
    public static <T> FlowableTransformer<T, T> flowableThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * completable线程调度
     */
    public static CompletableTransformer completableThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
