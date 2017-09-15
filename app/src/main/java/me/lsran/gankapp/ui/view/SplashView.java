package me.lsran.gankapp.ui.view;

import me.lsran.gankapp.model.GankApiModel;
import me.lsran.gankapp.ui.base.BaseView;

/**
 * @author lssRan
 */

public interface SplashView extends BaseView {

    // 设置启动页图片
    void setSplashImageInView(GankApiModel imageData);
}
