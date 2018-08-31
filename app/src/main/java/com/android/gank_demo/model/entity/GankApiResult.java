package com.android.gank_demo.model.entity;

import java.util.List;


public class GankApiResult {

    private List<GankDataModel> gankInfo;

    public List<GankDataModel> getGankInfo() {
        return gankInfo;
    }

    public void setGankInfo(List<GankDataModel> gankInfo) {
        this.gankInfo = gankInfo;
    }
}
