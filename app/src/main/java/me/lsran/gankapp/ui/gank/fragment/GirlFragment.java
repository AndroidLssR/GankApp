package me.lsran.gankapp.ui.gank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.lsran.gankapp.R;
import me.lsran.gankapp.internal.di.components.FragmentComponent;
import me.lsran.gankapp.model.GankApiModel;
import me.lsran.gankapp.presenters.GirlPresenter;
import me.lsran.gankapp.ui.base.BaseFragment;
import me.lsran.gankapp.ui.gank.adapter.GirlListAdapter;
import me.lsran.gankapp.ui.view.ListView;
import me.lsran.gankapp.widget.refresh.OnPullListener;
import me.lsran.gankapp.widget.refresh.RefreshLayout;

/**
 * author lssRan
 */

public class GirlFragment extends BaseFragment implements ListView {

    public static GirlFragment newInstance() {
        Bundle args = new Bundle();
        GirlFragment fragment = new GirlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    GirlPresenter girlPresenter;

    @Inject
    GirlListAdapter girlListAdapter;

    @BindView(R.id.refresh_view)
    RefreshLayout mRefreshView;

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

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
        girlPresenter.setView(this);
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
    private void initRefreshView() {
        mRefreshView.setPullListener(new OnPullListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                girlPresenter.refreshData();
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                girlPresenter.loadData();
            }
        });
    }

    /**
     * 设置RecycleView
     */
    private void setupRecyclerView() {
        mRvList.setAdapter(girlListAdapter);
        mRvList.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        girlListAdapter.getPositionClicks().subscribe(
                resultsBean -> getNavigator().navigateToImageBrower(getActivity(), resultsBean));
    }

    @Override
    public void setCollectionInView(GankApiModel gankList) {
        this.girlListAdapter.setGirlCollection(gankList.getResults());
    }

    @Override
    public void addCollectionInView(GankApiModel gankList) {
        this.girlListAdapter.addGirlCollection(gankList.getResults());
    }

    @Override
    public int getItemCountInView() {
        return girlListAdapter.getItemCount();
    }

    public void cancelRefreshView() {
        mRefreshView.refreshComplete();
    }

    @Override
    public Context context() {
        return getActivity();
    }
}
