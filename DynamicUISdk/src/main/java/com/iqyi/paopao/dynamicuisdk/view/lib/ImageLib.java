package com.iqyi.paopao.dynamicuisdk.view.lib;

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

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ImageLib extends BaseFunctionLib {

    private ImageView imageView;

    public ImageLib() {
        super("LImage");
    }

    @Override
    public void setMethod(LuaTable luaTable) {
        luaTable.set("setWH", new setWH());
        luaTable.set("setUrl", new setUrl());
    }

    @Override
    public LuaValue createCreator(LuaValue env, LuaValue metaTable) {
        Globals g = env.checkglobals();
        imageView = new ImageView(g.mCurContainer.getContext());
        g.mCurContainer.addView(imageView);
        return super.createCreator(env, metaTable);
    }

    private class setUrl extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            final String url = arg.checkjstring();
            final ImageLoader imageLoader = DynamicUIManager.getInstance().getImageLoader();
            if (imageLoader != null) {
                imageLoader.loadImage(imageView, Uri.parse(url), null);
            }
            return LuaValue.valueOf(url);
        }
    }

    private class setWH extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue arg1, LuaValue arg2) {
            ViewGroup.LayoutParams params = ViewUtil.getLayoutParams(imageView);
            params.width = arg1.checkint();
            params.height = arg2.checkint();
            imageView.setLayoutParams(params);

            return LuaValue.NIL;
        }
    }


}
