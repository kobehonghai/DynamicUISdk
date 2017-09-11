package com.iqyi.paopao.dynamicuisdk.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ViewUtil {

    private ViewUtil() {
    }

    public static ViewGroup.LayoutParams getLayoutParams(View view) {
        if (view != null && view.getLayoutParams() != null) {
            return view.getLayoutParams();
        }
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
