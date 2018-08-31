package com.android.gank_demo.ui.module.welfare.presenter;


import com.android.gank_demo.frame.BaseView;
import com.android.gank_demo.frame.IPresenter;
import com.android.gank_demo.model.entity.GankDataModel;

import java.util.List;

public interface WelfareContract {

    interface View extends BaseView{
        void showData(List<GankDataModel> list);

        void closeRefreshLayout();
    }

    interface Presenter extends IPresenter<View>{
        void refreshData();

        void loadmoreData();

        void saveImage(GankDataModel model);
    }
}
