package me.lsran.gankapp.widget.refresh;

/**
 * 下拉刷新头
 * 隔离第三方控件的接口
 */

public interface OnPullListener {
    void onRefresh(RefreshLayout refreshLayout);
    void onLoadMore(RefreshLayout refreshLayout);
}
