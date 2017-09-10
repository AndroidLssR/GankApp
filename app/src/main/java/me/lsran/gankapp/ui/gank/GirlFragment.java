package me.lsran.gankapp.ui.gank;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.lsran.gankapp.R;
import me.lsran.gankapp.internal.di.components.FragmentComponent;
import me.lsran.gankapp.model.GankDataModel;
import me.lsran.gankapp.presenters.GirlPresenter;
import me.lsran.gankapp.ui.BaseFragment;
import me.lsran.gankapp.ui.gank.adapter.GirlListAdapter;
import me.lsran.gankapp.ui.gank.view.GankListView;

/**
 * author lssRan
 */

public class GirlFragment extends BaseFragment implements GankListView {

    public static GirlFragment newInstance() {
        Bundle args = new Bundle();
        GirlFragment fragment = new GirlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    GirlPresenter housePresenter;

    @Inject
    GirlListAdapter houseListAdapter;

    @BindView(R.id.refresh_view)
    RefreshLayout mRefreshView;

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @BindView(R.id.rl_progress)
    FrameLayout mProgressBar;

    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(FragmentComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_gank_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        initRefreshView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        housePresenter.setView(this);
        mRefreshView.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 初始化刷新控件
     */
    private void initRefreshView(){
        mRefreshView.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                housePresenter.loadData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                housePresenter.refreshData();
            }
        });
    }

    /**
     * 设置RecycleView
     */
    private void setupRecyclerView() {
        mRvList.setAdapter(houseListAdapter);
        mRvList.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        houseListAdapter.getPositionClicks().doOnNext(
                gankDataModel -> Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void showViewLoading() {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideViewLoading() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showHouseCollectionInView(GankDataModel houseList) {
        this.houseListAdapter.setGirlCollection(houseList.getResults());
    }

    public void cancelRefreshView(){

        if (mRefreshView.isRefreshing()) {
            mRefreshView.finishRefresh();
        }

        if (mRefreshView.isLoading()) {
            mRefreshView.finishLoadmore();
        }

    }

    @Override
    public Context context() {
        return getActivity();
    }
}
