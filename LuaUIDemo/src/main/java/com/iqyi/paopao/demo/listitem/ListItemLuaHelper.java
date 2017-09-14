package com.iqyi.paopao.demo.listitem;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iqyi.paopao.lua.globals.DynamicUIManager;
import com.iqyi.paopao.lua.lib.ImageLib;
import com.iqyi.paopao.lua.lib.LinearLayoutLib;
import com.iqyi.paopao.lua.lib.RelativeLayoutLib;
import com.iqyi.paopao.lua.lib.TextViewLib;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ResourceFinder;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import java.io.InputStream;

/**
 * Created by liuhonghai on 2017/9/8.
 */

public class ListItemLuaHelper implements ResourceFinder {

    public final Globals globals;
    public View view;
    public TextView errorTip;
    private Context mContext;

    public ListItemLuaHelper(Context context) {
        mContext=context;
        DynamicUIManager.getInstance().loadCustomLib(new RelativeLayoutLib(mContext),new LinearLayoutLib(mContext),new TextViewLib(mContext),new ImageLib(mContext));//绑定所有需要的组件
        this.globals = DynamicUIManager.getInstance().getGlobals();
        globals.finder = this;
        globals.set("context", CoerceJavaToLua.coerce(mContext));
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
                Log.i("ListItemLuaHelper",view.toString());
                return view;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return errorTip;
    }

    public void setData(Object data,View view){
        LuaValue f = globals.get("setData");
        if (!f.isnil()) {
            try {
                f.invoke(CoerceJavaToLua.coerce(data),CoerceJavaToLua.coerce(view));
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
