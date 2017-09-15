package me.lsran.gankapp.widget.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import me.lsran.gankapp.R;

/**
 * 下拉刷新组件
 * 如果修改为其他第三方直接继承第三方layout并修改相关方法
 */

public class RefreshLayout extends SmartRefreshLayout {
    private OnPullListener mPullListener;
    private boolean isRefreshing;
    private boolean isLoadingMore;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RefreshLayout);
        boolean enableRefresh = array.getBoolean(R.styleable.RefreshLayout_enable_refresh, true);
        boolean enableLoadMore = array.getBoolean(R.styleable.RefreshLayout_enable_load_more, true);
        array.recycle();
        enableRefresh(enableRefresh);
        enableLoadMore(enableLoadMore);
        setOnRefreshListener(refreshlayout -> {
            isRefreshing = true;
            if (mPullListener != null) {
                mPullListener.onRefresh(RefreshLayout.this);
            }
        });
        setOnLoadmoreListener(refreshlayout -> {
            isLoadingMore = true;
            if (mPullListener != null) {
                mPullListener.onLoadMore(RefreshLayout.this);
            }
        });

    }

    public void enableRefresh(boolean refreshable) {
        setEnableRefresh(refreshable);
    }

    public void enableLoadMore(boolean loadMoreAble) {
        setEnableRefresh(loadMoreAble);
    }

    public void setCustomerRefreshHeader(BaseRefreshHeader header) {
        setRefreshHeader(header);
    }

    public void setCustomerLoadMoreFooter(BaseFooter footer) {
        setRefreshFooter(footer);
    }

    public void setPullListener(OnPullListener pullListener) {
        this.mPullListener = pullListener;
    }

    public boolean autoRefresh() {
        return super.autoRefresh();
    }

    public void refreshComplete(boolean noMoreData) {
        if (isRefreshing) {
            isRefreshing = false;
            finishRefresh();
        }
        if (isLoadingMore) {
            isLoadingMore = false;
            finishLoadmore();
        }
        if (noMoreData) {
            setLoadmoreFinished(noMoreData);
        }
    }

    public void refreshComplete() {
        if (isRefreshing) {
            isRefreshing = false;
            finishRefresh();
        }
        if (isLoadingMore) {
            isLoadingMore = false;
            finishLoadmore();
        }
        setLoadmoreFinished(false);
    }

}
