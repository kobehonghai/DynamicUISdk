package com.iqyi.paopao.dynamicuisdk.view.lib;

import android.content.Context;
import android.util.AndroidException;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iqyi.paopao.dynamicuisdk.utils.ViewUtil;
import com.iqyi.paopao.dynamicuisdk.view.lib.base.BaseFunctionLib;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

/**
 * Created by liuhonghai on 2017/9/12.
 */

public class TextViewLib extends BaseFunctionLib {

    private Context mContext;

    public TextViewLib(Context context) {
        super("LTextView");
        mContext=context;
    }

    @Override
    public void setMethod(LuaTable luaTable) {
        luaTable.set("newTextView", new TextViewLib.NewTextView());
    }

    private class NewTextView extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            LTextView textView=new LTextView(mContext);
            return CoerceJavaToLua.coerce(textView);
        }
    }

    public class LTextView extends android.support.v7.widget.AppCompatTextView{

        public LTextView(Context context) {
            super(context);
            ViewUtil.setId(this);
        }
    }
}
