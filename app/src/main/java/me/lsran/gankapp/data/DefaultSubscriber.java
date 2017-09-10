package me.lsran.gankapp.data;


import me.lsran.gankapp.data.exception.ErrorMessageFactory;

/**
 * 默认API结果处理器
 *
 * @param <T> the model
 */
public class DefaultSubscriber<T> extends rx.Subscriber<T> {
    @Override
    public void onCompleted() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        ErrorMessageFactory.create((Exception) e);
    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }
}
