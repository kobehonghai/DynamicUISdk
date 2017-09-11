package com.iqyi.paopao.demo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ResourceFinder;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.InputStream;

/**
 * Created by liuhonghai on 2017/9/8.
 */

public class ListItemLuaView implements ResourceFinder {

    public final Globals globals;
    public View view;
    public TextView errorTip;
    private Context mContext;

    public ListItemLuaView(Context context) {
        mContext=context;
        this.globals = JsePlatform.standardGlobals();
        globals.finder = this;
        globals.set("context", CoerceJavaToLua.coerce(mContext));
        globals.set("relativeLayoutParams", CoerceJavaToLua.coerce(new RelativeLayout.LayoutParams(100, 100)));
        globals.loadfile("listitem.lua").call();
        errorTip=new TextView(context);
        errorTip.setText("error");
    }

    public View getView(){
        LuaValue f = globals.get("getView");
        if (!f.isnil()) {
            try {
                LuaValue luaValue= (LuaValue) f.invoke(CoerceJavaToLua.coerce("lua文本"));
                view= (View) CoerceLuaToJava.coerce(luaValue, android.view.View.class);
                Log.i("ListItemLuaView",view.toString());
                return view;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return errorTip;
    }

    public void setData(Object data){
        LuaValue f = globals.get("setData");
        if (!f.isnil()) {
            try {
                f.invoke(CoerceJavaToLua.coerce(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public InputStream findResource(String filename) {
        try {
            return mContext.getResources().getAssets().open(filename);
        } catch (java.io.IOException ioe) {
            return null;
        }
    }
}
