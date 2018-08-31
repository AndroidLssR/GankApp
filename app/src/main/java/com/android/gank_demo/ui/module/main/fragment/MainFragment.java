package com.android.gank_demo.ui.module.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gank_demo.R;
import com.android.gank_demo.frame.FrameFragment;
import com.android.gank_demo.ui.module.common.widget.CustomizeTabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainFragment extends FrameFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<String> mTitleList;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitleList = Arrays.asList(getResources().getStringArray(R.array.main_tab_array));
        initializeViewPager();
    }

    private void initializeViewPager() {
        if (mTitleList == null || mTitleList.isEmpty()) return;
        List<Fragment> fragments = new ArrayList<>();
        for (String title : mTitleList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
            fragments.add(GankFragment.newInstance(title));
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);

            }
        };
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 关联TabLayout与ViewPager，且适配器必须重写getPageTitle()方法
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
