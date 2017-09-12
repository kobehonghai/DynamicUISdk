package com.iqyi.paopao.dynamicuisdk.view.lib;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iqyi.paopao.dynamicuisdk.globals.DynamicUIManager;
import com.iqyi.paopao.dynamicuisdk.globals.ImageLoader;
import com.iqyi.paopao.dynamicuisdk.utils.ViewUtil;
import com.iqyi.paopao.dynamicuisdk.view.lib.base.BaseFunctionLib;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
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
        mContext=context;
    }

    @Override
    public void setMethod(LuaTable luaTable) {
        luaTable.set("newImageView", new NewImageView());
    }

    private class NewImageView extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            ImageLib.LImageView imageView=new ImageLib.LImageView(mContext);
            return CoerceJavaToLua.coerce(imageView);
        }
    }

    public class LImageView extends android.support.v7.widget.AppCompatImageView{

        public LImageView(Context context) {
            super(context);
            ViewUtil.setId(this);
        }

        public void setNetUrl(String url){
            final ImageLoader imageLoader = DynamicUIManager.getInstance().getImageLoader();
            if (imageLoader != null) {
                imageLoader.loadImage(this, Uri.parse(url), null);
            }
        }
    }




}
