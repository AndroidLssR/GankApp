package com.android.gank_demo.ui.module.welfare.adapter;

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

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.ViewHolder> {

    private List<GankDataModel> list = new ArrayList();

    private PublishSubject<GankDataModel> onClickSubject = PublishSubject.create();
    private PublishSubject<GankDataModel> onLongClickSubject = PublishSubject.create();

    @Inject
    public WelfareAdapter() {
    }

    public void addData(List<GankDataModel> list) {
        if (list == null) return;
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setData(List<GankDataModel> list) {
        this.list = list;
    }

    /**
     * 获取item点击事件
     */
    public Observable<GankDataModel> getClickSubject() {
        return onClickSubject;
    }

    /**
     * 获取item长按事件
     */
    public Observable<GankDataModel> getLongClickSubject(){
        return onLongClickSubject;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welfare_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GankDataModel model = list.get(position);

        Glide.with(viewHolder.mImageView.getContext())
                .load(model.getUrl())
                .into(viewHolder.mImageView);

        viewHolder.mTvName.setText(model.getDesc());

        viewHolder.mImageView.setOnClickListener(view -> onClickSubject.onNext(model));
        viewHolder.mImageView.setOnLongClickListener(view -> {
            onLongClickSubject.onNext(model);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView mImageView;

        @BindView(R.id.tv_name)
        TextView mTvName;

        View mItemView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
