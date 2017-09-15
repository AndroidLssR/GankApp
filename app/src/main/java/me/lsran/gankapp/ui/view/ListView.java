package me.lsran.gankapp.ui.view;

import me.lsran.gankapp.model.GankApiModel;
import me.lsran.gankapp.model.GankDataModel;
import me.lsran.gankapp.ui.base.BaseView;


public interface ListView extends BaseView {

    // 设置数据
    void setCollectionInView(GankApiModel gankList);

    // 添加数据
    void addCollectionInView(GankApiModel gankList);

    // 获取列表数据数量
    int getItemCountInView();

    // 刷新完成
    void cancelRefreshView();
}
