package com.android.gank_demo.model.annotation;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({Category.CATEGORY_WELFARE})
@Retention(RetentionPolicy.SOURCE)
public @interface Category {

    String CATEGORY_WELFARE = "福利";
}
