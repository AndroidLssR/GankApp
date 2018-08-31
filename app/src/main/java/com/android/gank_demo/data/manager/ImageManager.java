package com.android.gank_demo.data.manager;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.android.gank_demo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.schedulers.Schedulers;

public class ImageManager {

    protected Application mContext;
    private ObservableCreate<Bitmap> bitmapObservableCreate;

    @Inject
    public ImageManager(Application application) {
        this.mContext = application;
    }

    public Observable<String> saveTheImage(String imageUrl) {
        return Observable.create((ObservableOnSubscribe<Bitmap>) emitter -> {
            Bitmap bitmap = null;
            try {
                FutureTarget<Bitmap> futureTarget =
                        Glide.with(mContext)
                                .asBitmap()
                                .load(imageUrl)
                                .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

                 bitmap = futureTarget.get();

                // Do something with the Bitmap and then when you're done with it:
                Glide.with(mContext).clear(futureTarget);
            } catch (Exception e) {
                emitter.onError(e);
            }

            if (bitmap == null) {
                emitter.onError(new Exception("图片保存失败"));
            }
            emitter.onNext(bitmap);
            emitter.onComplete();
        }).flatMap(bitmap -> {
            File appDir = new File(Environment.getExternalStorageDirectory(),
                    mContext.getString(R.string.app_name) + "/images");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                assert bitmap != null;
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Uri uri = Uri.fromFile(file);
            // 通知图库更新
            Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            mContext.sendBroadcast(scannerIntent);
            return Observable.just("图片保存成功");
        }).subscribeOn(Schedulers.io());
    }
}
