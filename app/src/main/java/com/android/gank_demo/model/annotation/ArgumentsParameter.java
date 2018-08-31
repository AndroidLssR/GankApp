package com.android.gank_demo.model.annotation;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ftragment 传递参数
 */
@StringDef({ArgumentsParameter.ARGS_TAB_TITLE})
@Retention(RetentionPolicy.SOURCE)
public @interface ArgumentsParameter {

    String ARGS_TAB_TITLE = "args_tab_title";
}
