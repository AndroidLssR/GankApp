package me.lsran.gankapp.ui.gank.view;

import me.lsran.gankapp.model.GankDataModel;
import me.lsran.gankapp.ui.BaseView;


public interface GankListView extends BaseView {
    // 显示加载进度条
    void showViewLoading();

    // 隐藏加载进度条
    void hideViewLoading();

    // 显示房源列表
    void showHouseCollectionInView(GankDataModel gankList);

    // 刷新完成
    void cancelRefreshView();
}
