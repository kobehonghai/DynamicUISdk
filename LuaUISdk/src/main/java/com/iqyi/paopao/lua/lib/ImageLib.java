package com.iqyi.paopao.lua.lib;

import android.content.Context;
import android.net.Uri;

import com.iqyi.paopao.lua.globals.DynamicUIManager;
import com.iqyi.paopao.lua.imagloader.ImageLoader;
import com.iqyi.paopao.lua.imagloader.image.RoundParam;
import com.iqyi.paopao.lua.lib.base.BaseFunctionLib;
import com.iqyi.paopao.lua.utils.ViewUtil;

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

    public class LImageView extends android.support.v7.widget.AppCompatImageView {

        private RoundParam roundParam;

        public LImageView(Context context) {
            super(context);
            ViewUtil.setId(this);
        }

        public void setRoundAsCircle(boolean roundAsCircle) {
            if (roundParam == null) {
                roundParam = new RoundParam();


            }
            roundParam.setRoundAsCircle(roundAsCircle);
        }

        public void setNetUrl(String url) {
            final ImageLoader imageLoader = DynamicUIManager.getInstance().getImageLoader();
            if (imageLoader != null) {
                imageLoader.loadImage(this, Uri.parse(url), null);
            }
        }


    }


}
