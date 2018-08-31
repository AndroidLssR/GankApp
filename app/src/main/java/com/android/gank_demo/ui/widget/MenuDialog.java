package com.android.gank_demo.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.gank_demo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuDialog extends BottomSheetDialog {

    private Context mContext;

    private List<String> mDataList;

    private MenuAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public MenuDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        setContentView(LayoutInflater.from(getContext()).inflate(
                R.layout.dialog_menu, null, false));
        ButterKnife.bind(this);

        initializationRecyclerView();
    }

    public MenuDialog setDataList(List<String> dataList) {
        this.mDataList = dataList;
        mAdapter.notifyDataSetChanged();
        return this;
    }

    private void initializationRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MenuAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_menu_list, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            String s = mDataList.get(position);
            viewHolder.mTvItemName.setText(s);
        }

        @Override
        public int getItemCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_item_name)
            TextView mTvItemName;

            ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
