package me.lsran.gankapp.data.repository;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.lsran.gankapp.App;
import me.lsran.gankapp.BuildConfig;


@Singleton
public class FileRepository extends DataRepository {

    /**
     * default cache size: 50mb
     */
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;

    private DiskLruCache mDiskLruCache;

    @Inject
    public FileRepository() {
        // TODO: initialize disk lru cache
        try {
            mDiskLruCache = DiskLruCache.open(getDiskCacheDir(App.getInstance(), "foobars"),
                    BuildConfig.VERSION_CODE, 1, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存文件夹路径
     *
     * @param context    the Context
     * @param uniqueName 目录类型
     * @return the file
     */
    public File getDiskCacheDir(@NonNull Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

}
