package com.android.gank_demo.model.annotation;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * activity 传递参数
 */
@StringDef({IntentParameter.INTENT_PARMS_WELFARE_DATA,IntentParameter.INTENT_PARMS_WEB_DATA})
@Retention(RetentionPolicy.SOURCE)
public @interface IntentParameter {

    String INTENT_PARMS_WELFARE_DATA = "intent_parms_data";

    String INTENT_PARMS_WEB_DATA = "intent_parms_web_data";
}
