package com.iqyi.paopao.lua.globals;

import android.content.Context;

import com.iqyi.paopao.lua.utils.AndroidUtil;


/**
 * Created by liuhonghai on 2017/9/13.
 */

public class Constants {

    public static float sScale;

    public static void init(Context context) {
        sScale = AndroidUtil.getDensity(context);
    }
}
