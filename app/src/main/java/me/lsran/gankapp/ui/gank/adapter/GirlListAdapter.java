package me.lsran.gankapp.ui.gank.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lsran.gankapp.R;
import me.lsran.gankapp.model.GankDataModel;
import rx.Observable;
import rx.subjects.PublishSubject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 图片列表适配器
 */
public class GirlListAdapter extends RecyclerView.Adapter<GirlListAdapter.GankViewHolder> {

    private List<GankDataModel.ResultsBean> girlCollection;
    private LayoutInflater layoutInflater;

    private PublishSubject<GankDataModel.ResultsBean> onClickSubject = PublishSubject.create();

    @Inject
    public GirlListAdapter(Activity context) {
        layoutInflater = LayoutInflater.from(context);
        girlCollection = Collections.emptyList();
    }

    @Override
    public GirlListAdapter.GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.item_gank, parent, false);
        return new GankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GankViewHolder viewHolder, int position) {
       final GankDataModel.ResultsBean resultsBean = girlCollection.get(position);
        Glide.with(layoutInflater.getContext())
                .load(resultsBean.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.mImage);
        viewHolder.itemView.setOnClickListener(view -> onClickSubject.onNext(resultsBean));
    }

    @Override
    public int getItemCount() {
        return girlCollection != null ? girlCollection.size() : 0;
    }

    /**
     * 设置图片列表集合
     *
     * @param girlCollection 图片列表
     */
    public void setGirlCollection(Collection<GankDataModel.ResultsBean> girlCollection) {
        checkNotNull(girlCollection);
        this.girlCollection = (List<GankDataModel.ResultsBean>) girlCollection;
        notifyDataSetChanged();
    }

    /**
     * 获取item点击事件
     *
     * @return the observable
     */
    public Observable<GankDataModel.ResultsBean> getPositionClicks() {
        return onClickSubject.asObservable();
    }

    static final class GankViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView mImage;

        public GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
