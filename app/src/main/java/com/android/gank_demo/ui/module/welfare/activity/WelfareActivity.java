package com.android.gank_demo.ui.module.welfare.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.gank_demo.R;
import com.android.gank_demo.frame.FrameActivity;
import com.android.gank_demo.frame.Presenter;
import com.android.gank_demo.model.entity.GankDataModel;
import com.android.gank_demo.ui.module.common.activity.ImageBrowserActivity;
import com.android.gank_demo.ui.module.welfare.adapter.WelfareAdapter;
import com.android.gank_demo.ui.module.welfare.presenter.WelfareContract;
import com.android.gank_demo.ui.module.welfare.presenter.WelfarePresenter;
import com.android.gank_demo.ui.widget.MenuDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelfareActivity extends FrameActivity implements WelfareContract.View {

    @Presenter
    @Inject
    WelfarePresenter mPresenter;

    @Inject
    WelfareAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fab_refresh)
    FloatingActionButton mFabRefresh;

    private boolean refreshOrloadmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        ButterKnife.bind(this);
        initializeRecyclerView();
        initializeRefreshLayout();
    }

    private void initializeRecyclerView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.getClickSubject().subscribe(model -> startActivity(
                ImageBrowserActivity.getCallToImageBrowserIntent(WelfareActivity.this, model)));
        mAdapter.getLongClickSubject().subscribe(model -> {
            showMenuDialog();
//            mPresenter.saveImage(model);
        });
    }

//    @Override
//    public void call(Boolean aBoolean) {
//        if (aBoolean) {
//            //当所有权限都允许之后，返回true
//            mPresenter.saveImage(model);
//        } else {
//            //只要有一个权限禁止，返回false，
//            //下一次申请只申请没通过申请的权限
//            showToast("您拒绝了存储权限");
//        }
//    }

    private void initializeRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            refreshOrloadmore = true;
            mPresenter.refreshData();
        });

        mRefreshLayout.setOnLoadmoreListener(refreshlayout -> {
            refreshOrloadmore = false;
            mPresenter.loadmoreData();
        });
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void showData(List<GankDataModel> list) {
        closeRefreshLayout();
        if (list.isEmpty()) return;

        if (refreshOrloadmore) {
            mAdapter.setData(list);
        } else {
            mAdapter.addData(list);
        }
    }

    @Override
    public void closeRefreshLayout() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @OnClick(R.id.fab_refresh)
    void onClickRefresh(){
        mRecyclerView.scrollToPosition(0);
        mRefreshLayout.autoRefresh();
    }

    private void showMenuDialog() {
        new MenuDialog(this)
                .setDataList(Arrays.asList("删除","分享", "下载"))
                .show();
    }
}
