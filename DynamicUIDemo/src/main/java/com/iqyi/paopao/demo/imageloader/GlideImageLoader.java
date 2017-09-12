package com.iqyi.paopao.demo.imageloader;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.iqyi.paopao.demo.R;
import com.iqyi.paopao.dynamicuisdk.globals.ImageLoader;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void loadImage(ImageView imageView, Uri uri, Callback callback) {
        Glide.with(imageView.getContext()).load(uri).placeholder(R.mipmap.ic_launcher).listener(
                new RequestListener<Uri, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Uri model, Target<GlideDrawable> target,
                            boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Uri model,
                            Target<GlideDrawable> target,
                            boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                }).into(imageView);
    }
}
