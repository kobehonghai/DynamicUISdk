package com.iqyi.paopao.dynamicuisdk.globals;

import android.content.Context;

import com.iqyi.paopao.dynamicuisdk.utils.AndroidUtil;

/**
 * Created by liuhonghai on 2017/9/13.
 */

public class Constants {

    public static float sScale;

    public static void init(Context context) {
        sScale = AndroidUtil.getDensity(context);
    }
}
