package com.iqyi.paopao.dynamicuisdk.utils;

import android.content.Context;

/**
 * Created by liuhonghai on 2017/9/13.
 */

public class AndroidUtil {

    /**
     * get density of screen
     *
     * @param context Context
     * @return
     */
    public static float getDensity(Context context) {
        return context.getApplicationContext().getResources().getDisplayMetrics().density;
    }
}
