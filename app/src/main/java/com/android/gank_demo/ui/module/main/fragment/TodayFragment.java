package com.android.gank_demo.ui.module.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gank_demo.R;
import com.android.gank_demo.frame.FrameFragment;

public class TodayFragment  extends FrameFragment{


    public static TodayFragment newInstance() {
        TodayFragment fragment = new TodayFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
