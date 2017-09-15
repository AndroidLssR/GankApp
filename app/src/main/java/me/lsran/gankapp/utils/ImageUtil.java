package me.lsran.gankapp.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import me.lsran.gankapp.R;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author lssRan
 */

public class ImageUtil {

    /**
     * 保存图片
     *
     * @return 成功与失败
     */
    public static Observable<Uri> saveTheImage(@NonNull Context context, String imageUrl) {
        return Observable.create((Observable.OnSubscribe<Bitmap>) subscriber -> {
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(context)
                        .load(imageUrl)
                        .asBitmap()
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                subscriber.onError(e);
            }
            if (bitmap == null) {
                subscriber.onError(new Exception("下载图片失败"));
            }
            subscriber.onNext(bitmap);
            subscriber.onCompleted();
        }).flatMap(bitmap -> {
            File appDir = new File(Environment.getExternalStorageDirectory(),
                    context.getString(R.string.app_name) + "/images");
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
            context.sendBroadcast(scannerIntent);
            return Observable.just(uri);
        }).subscribeOn(Schedulers.io());
    }
}