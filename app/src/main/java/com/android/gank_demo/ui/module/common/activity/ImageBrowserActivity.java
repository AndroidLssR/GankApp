package com.android.gank_demo.ui.module.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.gank_demo.R;
import com.android.gank_demo.frame.FrameActivity;
import com.android.gank_demo.model.entity.GankDataModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.gank_demo.model.annotation.IntentParameter.INTENT_PARMS_WELFARE_DATA;

public class ImageBrowserActivity extends FrameActivity {

    public static Intent getCallToImageBrowserIntent(@NonNull Context context, @NonNull GankDataModel gankDataModel) {
        Intent callingIntent = new Intent(context, ImageBrowserActivity.class);
        callingIntent.putExtra(INTENT_PARMS_WELFARE_DATA, gankDataModel);
        return callingIntent;
    }

    @BindView(R.id.image_view)
    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browser);
        ButterKnife.bind(this);
        GankDataModel model = getIntent().getParcelableExtra(INTENT_PARMS_WELFARE_DATA);
        if (model == null) return;
        setTitle(model.getDesc());
        initializeData(model.getUrl());
    }

    private void initializeData(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .into(mImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start:
                showToast("功能");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
