package me.lsran.gankapp.ui.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lsran.gankapp.R;
import me.lsran.gankapp.model.GankDataModel;
import me.lsran.gankapp.ui.base.BaseActivity;
import me.lsran.gankapp.utils.ImageUtil;
import me.lsran.gankapp.utils.ToastUtil;
import rx.android.schedulers.AndroidSchedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 图片浏览器
 *
 * @author lssRan
 */

public class ImageBrowserActivity extends BaseActivity {

    public static final String INTENT_EXTRA_PARAM_DATA = "intent_extra_param_data";

    public static Intent getCallingIntent(@NonNull Context context, @NonNull GankDataModel gankDataModel) {
        checkNotNull(gankDataModel);
        Intent callingIntent = new Intent(context, ImageBrowserActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_DATA, gankDataModel);
        return callingIntent;
    }

    @BindView(R.id.photo_view)
    PhotoView mPhotoView;

    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browser);
        ButterKnife.bind(this);
        GankDataModel imageData = getIntent().getParcelableExtra(INTENT_EXTRA_PARAM_DATA);
        imageUrl = imageData.getUrl();
        checkNotNull(imageData);
        initializeData();
    }

    private void initializeData() {
        Glide.with(this)
                .load(imageUrl)
                .into(mPhotoView);
    }

    @OnClick(R.id.photo_view)
    void onTheBackPageo(){
        onBackPressed();
    }

    @OnClick(R.id.image_download)
    void saveTheImage() {
        ImageUtil.saveTheImage(this, imageUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> ToastUtil.showToast(String.format(getString(R.string.photo_has_save_to))),
                        error -> ToastUtil.showToast(error.getMessage()));
    }
}
