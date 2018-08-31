package com.android.gank_demo.ui.module.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.gank_demo.R;
import com.android.gank_demo.model.entity.GankDataModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<GankDataModel> list;

    private PublishSubject<GankDataModel> onClickSubject = PublishSubject.create();

    @Inject
    public MainAdapter() {
    }

    public void addData(List<GankDataModel> list) {
        if (list == null) return;
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setData(List<GankDataModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 获取item点击事件
     */
    public Observable<GankDataModel> getClickSubject() {
        return onClickSubject;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_main_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GankDataModel model = list.get(position);

        if (model.getImages() != null && model.getImages().length > 0) {
            viewHolder.mImageView.setVisibility(View.VISIBLE);
            Glide.with(viewHolder.mImageView.getContext())
                    .load(model.getImages()[0])
                    .into(viewHolder.mImageView);
        } else {
            viewHolder.mImageView.setVisibility(View.GONE);
        }


        viewHolder.mTvTitle.setText(model.getDesc());
        viewHolder.mTvSource.setText(model.getWho());
        viewHolder.mTvTime.setText(model.getPublishedAt());

        viewHolder.mItemView.setOnClickListener(view -> onClickSubject.onNext(model));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView mImageView;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_source)
        TextView mTvSource;
        @BindView(R.id.tv_time)
        TextView mTvTime;

        View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

}
