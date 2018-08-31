package com.android.gank_demo.ui.module.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.gank_demo.R;
import com.android.gank_demo.utils.PhoneCompat;

import java.util.List;

public class CustomizeTabView extends RelativeLayout {

    private Context mContext;
    private LinearLayout mLinearText;
    private LinearLayout mLinearImage;

    private int mSelectedIndex;
    private List<String> mDataList;

    private View.OnClickListener mRigthOnClickListener;

    public CustomizeTabView(Context context) {
        super(context);
        initializationView(context,null);
    }

    public CustomizeTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializationView(context, attrs);

    }

    public CustomizeTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializationView(context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomizeTabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializationView(context, attrs);

    }

    private void initializationView(Context context, AttributeSet attrs){
        this.mContext = context;
        View view = View.inflate(context, R.layout.view_customize_tab, this);
        mLinearText = view.findViewById(R.id.linear_text);
        mLinearImage = view.findViewById(R.id.linear_image);
    }

    private void drawTabLayout(int selectedItem) {
        mLinearText.removeAllViews();
        mLinearImage.removeAllViews();
        for (int i = 0; i < mDataList.size(); i++) {
            TextView textView = new TextView(mContext);
            ImageView imageView = new ImageView(mContext);
            textView.setTextColor(Color.WHITE);
            textView.setText(mDataList.get(i));
            if (selectedItem == i) {
                textView.setTextSize(PhoneCompat.sp2px(mContext, 13));
                imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_tab_selected));
            } else {
                textView.setTextSize(PhoneCompat.sp2px(mContext, 5));
                imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_tab_unselect));
            }
            textView.setPadding(0, 0, PhoneCompat.dp2px(mContext, 4), 0);
            imageView.setPadding(0, 0, PhoneCompat.dp2px(mContext, 6), 0);
            mLinearText.addView(textView);
            mLinearImage.addView(imageView);
        }
    }


    /**
     * 代码时序问题 必须先设置tab数据才能设置selectedIndex
     * @param selectedIndex
     */
    public void setSelectedIndex(int selectedIndex) {
        this.mSelectedIndex = selectedIndex;
        drawTabLayout(mSelectedIndex);
    }

    public void setDataList(List<String> dataList){
       this. mDataList = dataList;
    }

}
