package com.android.gank_demo.ui.module.main.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.gank_demo.R;
import com.android.gank_demo.frame.FrameActivity;
import com.android.gank_demo.ui.module.main.fragment.MainFragment;
import com.android.gank_demo.ui.module.main.fragment.TodayFragment;
import com.android.gank_demo.ui.module.main.fragment.UserCenterFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FrameActivity {

    private final static String TAG = "MainActivity";

    // 当前page id
    private static int CURRENT_PAGE = -1;

    /**
     * 当前tab栏个数
     */
    private static final int MAX_TAB_SIZE = 3;

    private static final int PAGE_SELECTED_SQUARE = 0;
    private static final int PAGE_SELECTED_MESSAGE = 1;
    private static final int PAGE_SELECTED_SERVICE = 2;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showHomeAsUp(false);
        onSelectedPage(PAGE_SELECTED_SQUARE);
        initializeView();
    }

    private void initializeView() {
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.icon_home, "gank"))
                .addItem(new BottomNavigationItem(R.drawable.icon_tag, "分类"))
                .addItem(new BottomNavigationItem(R.drawable.icon_user, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            // 这里也可以使用SimpleOnTabSelectedListener
            @Override
            public void onTabSelected(int position) {//未选中 -> 选中
                onSelectedPage(position);
            }

            @Override
            public void onTabUnselected(int position) {//选中 -> 未选中
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    /**
     * 点击选择tab栏
     *
     * @param pageID tab栏编号
     */
    private synchronized void onSelectedPage(int pageID) {
        CURRENT_PAGE = pageID;

        // 加载fragment
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG + String.valueOf(pageID));
        if (fragment == null) {
            fragment = newFragment(pageID);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, TAG + String.valueOf(pageID))
                .addToBackStack(null)
                .commit();
    }

    /**
     * 创建Fragment实例
     *
     * @param pageID id
     * @return the fragment
     */
    private Fragment newFragment(int pageID) {
        if (pageID >= MAX_TAB_SIZE) {
            throw new IllegalArgumentException("The page id must less than " + MAX_TAB_SIZE);
        }
        switch (pageID) {
            case PAGE_SELECTED_SQUARE:
                return TodayFragment.newInstance();
            case PAGE_SELECTED_MESSAGE:
                return MainFragment.newInstance();
            case PAGE_SELECTED_SERVICE:
                return UserCenterFragment.newInstance();
        }
        return null;
    }
}
