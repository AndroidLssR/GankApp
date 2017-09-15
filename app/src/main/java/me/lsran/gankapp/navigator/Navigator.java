package me.lsran.gankapp.navigator;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.lsran.gankapp.model.GankDataModel;
import me.lsran.gankapp.ui.gank.activity.ImageBrowserActivity;


/**
 * Application Router
 */
public class Navigator {

    @Inject
    public Navigator() {
    }

    /**
     * 跳转到图片浏览器
     */
    public void navigateToImageBrower(@NonNull Context context, @NonNull GankDataModel gankDataModel) {
        context.startActivity(ImageBrowserActivity.getCallingIntent(context, gankDataModel));
    }
}
