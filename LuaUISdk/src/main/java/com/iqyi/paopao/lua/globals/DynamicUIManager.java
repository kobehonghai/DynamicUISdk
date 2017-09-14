package com.iqyi.paopao.lua.globals;

import android.content.Context;
import android.view.ViewGroup;

import com.iqyi.paopao.lua.imagloader.ImageLoader;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class DynamicUIManager {

    private Globals mGlobals;

    private static DynamicUIManager manger;

    private ImageLoader mImageLoader;

    private DynamicUIManager() {
        mGlobals = JsePlatform.standardGlobals();
    }

    public static DynamicUIManager getInstance() {
        synchronized (DynamicUIManager.class) {
            if (manger == null) {
                synchronized (DynamicUIManager.class) {
                    manger = new DynamicUIManager();
                }
            }
        }
        return manger;
    }

    public Globals getGlobals() {
        return mGlobals;
    }

    public void loadCustomLib(LuaValue... binders) {
        for (LuaValue binder : binders) {
            getGlobals().load(binder);
        }
        LoadState.install(getGlobals());
    }

    public void setContainerView(ViewGroup view) {
        getGlobals().mCurContainer = view;
    }

    public LuaValue loadLuaScript(Context context, String name) {
        try {
            LuaValue chunk = getGlobals().load(
                    new InputStreamReader(context.getResources().getAssets().open(name)),
                    "chunkname");
            chunk.call();
            return chunk;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LuaValue.NIL;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }
}
