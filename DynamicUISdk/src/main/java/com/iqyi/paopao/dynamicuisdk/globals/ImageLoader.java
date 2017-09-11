package com.iqyi.paopao.dynamicuisdk.globals;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 * 图片加载适配
 */

public interface ImageLoader {

    void loadImage(ImageView imageView,Uri uri, Callback callback);

    interface Callback {

        void onProgress(int progress);

        void onFail(Exception error);
    }
}
