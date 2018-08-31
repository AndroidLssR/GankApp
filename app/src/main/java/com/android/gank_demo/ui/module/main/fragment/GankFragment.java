package com.android.gank_demo.ui.module.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gank_demo.R;
import com.android.gank_demo.frame.FrameFragment;
import com.android.gank_demo.frame.Presenter;
import com.android.gank_demo.model.entity.GankDataModel;
import com.android.gank_demo.ui.module.common.activity.WebActivity;
import com.android.gank_demo.ui.module.main.adapter.MainAdapter;
import com.android.gank_demo.ui.module.main.presenter.MainContract;
import com.android.gank_demo.ui.module.main.presenter.MainPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.android.gank_demo.model.annotation.ArgumentsParameter.ARGS_TAB_TITLE;

public class GankFragment extends FrameFragment implements MainContract.View {

    public static GankFragment newInstance(String title) {
        Bundle args = new Bundle();
        GankFragment fragment = new GankFragment();
        args.putString(ARGS_TAB_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    @Presenter
    MainPresenter mPresenter;

    @Inject
    MainAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private boolean refreshOrloadmore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gank, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeRecyclerView();
        initializeRefreshLayout();
    }

    private void initializeRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.getClickSubject().subscribe(model -> startActivity(WebActivity.getCallToWebBrowserIntent(getActivity(), model)));
    }


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
}
