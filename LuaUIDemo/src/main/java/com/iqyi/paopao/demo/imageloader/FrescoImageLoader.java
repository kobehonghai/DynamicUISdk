package com.iqyi.paopao.demo.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.DefaultExecutorSupplier;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.iqyi.paopao.lua.imagloader.ImageLoader;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class FrescoImageLoader implements ImageLoader {

    private final DefaultExecutorSupplier mExecutorSupplier;
    private Context mContext;

    private FrescoImageLoader(Context appContext) {
        mContext = appContext;
        mExecutorSupplier = new DefaultExecutorSupplier(Runtime.getRuntime().availableProcessors());
    }

    public static FrescoImageLoader with(Context appContext) {
        return with(appContext, null, null);
    }

    public static FrescoImageLoader with(Context appContext,
            ImagePipelineConfig imagePipelineConfig) {
        return with(appContext, imagePipelineConfig, null);
    }

    public static FrescoImageLoader with(Context appContext,
            ImagePipelineConfig imagePipelineConfig, DraweeConfig draweeConfig) {
        Fresco.initialize(appContext, imagePipelineConfig, draweeConfig);
        return new FrescoImageLoader(appContext);
    }

    @Override
    public void loadImage(final ImageView imageView, Uri uri, final Callback callback) {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setResizeOptions(ResizeOptions.forDimensions(512, 512))
                .setRotationOptions(RotationOptions.autoRotate())
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, mContext);

        dataSource.subscribe(new BaseBitmapDataSubscriber() {

            @Override
            public void onNewResultImpl(@Nullable final Bitmap bitmap) {
                if (dataSource.isFinished() && bitmap != null) {
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bmp = Bitmap.createBitmap(bitmap);
                            imageView.setImageBitmap(bmp);
                        }
                    });
                    dataSource.close();
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
                if (dataSource != null) {
                    dataSource.close();
                }
                if (callback != null) {
                    callback.onFail(new RuntimeException("onFailureImpl"));
                }
            }

            @Override
            public void onProgressUpdate(
                    DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (callback != null) {
                    callback.onProgress((int) (dataSource.getProgress() * 100));
                }
            }
        }, mExecutorSupplier.forBackgroundTasks());
    }
}
