package com.iqyi.paopao.lua.lib;

import android.content.Context;
import android.net.Uri;

import com.iqyi.paopao.lua.globals.DynamicUIManager;
import com.iqyi.paopao.lua.imagloader.ImageLoader;
import com.iqyi.paopao.lua.lib.base.BaseFunctionLib;
import com.iqyi.paopao.lua.utils.ViewUtil;
import com.iqyi.paopao.lua.view.image.RoundedImageView;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;


/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ImageLib extends BaseFunctionLib {

    private Context mContext;

    public ImageLib(Context context) {
        super("LImage");
        mContext = context;
    }

    @Override
    public LuaValue createLuaTable(LuaValue env, LuaValue metaTable) {
        metaTable.set("newImageView", new NewImageView());
        return metaTable;
    }

    private class NewImageView extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            ImageLib.LImageView imageView = new ImageLib.LImageView(mContext);
            return CoerceJavaToLua.coerce(imageView);
        }
    }

    public class LImageView extends RoundedImageView {

        public LImageView(Context context) {
            super(context);
            ViewUtil.setId(this);
            setScaleType(ScaleType.CENTER_CROP);
            setOval(true);
        }

        public void setNetUrl(String url) {
            final ImageLoader imageLoader = DynamicUIManager.getInstance().getImageLoader();
            if (imageLoader != null) {
                imageLoader.loadImage(this, Uri.parse(url), null);
            }
        }


    }


}
