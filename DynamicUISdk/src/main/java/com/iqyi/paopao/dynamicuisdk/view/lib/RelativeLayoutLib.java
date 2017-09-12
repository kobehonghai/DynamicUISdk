package com.iqyi.paopao.dynamicuisdk.view.lib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.iqyi.paopao.dynamicuisdk.view.lib.base.BaseFunctionLib;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

/**
 * Created by liuhonghai on 2017/9/11.
 */

public class RelativeLayoutLib extends BaseFunctionLib {

    private RelativeLayout relativeLayout;
    private Context mContext;


    public RelativeLayoutLib(Context context) {
        super("LRelativeLayout");
        mContext = context;
        relativeLayout = new RelativeLayout(mContext);
    }


    @Override
    public void setMethod(LuaTable luaTable) {
        luaTable.set("newLayoutParams", new RelativeLayoutLib.NewLayoutParams());
        luaTable.set("addView", new RelativeLayoutLib.AddView());
        luaTable.set("removeView", new RelativeLayoutLib.RemoveView());
        luaTable.set("getContent", new RelativeLayoutLib.GetContent());
    }

    private class NewLayoutParams extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            LayoutParamsLib layoutParamsLib = new LayoutParamsLib();
            return CoerceJavaToLua.coerce(layoutParamsLib);
        }
    }

    private class AddView extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue view, LuaValue layoutParam) {
            addView((View) CoerceLuaToJava.coerce(view, android.view.View.class), (RelativeLayout.LayoutParams) CoerceLuaToJava.coerce(layoutParam, RelativeLayout.LayoutParams.class));
            return CoerceJavaToLua.coerce(relativeLayout);
        }
    }

    private class RemoveView extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue view) {
            View v=(View) CoerceLuaToJava.coerce(view, android.view.View.class);
            if (relativeLayout!=null && v!=null){
                relativeLayout.removeView(v);
            }
            return CoerceJavaToLua.coerce(relativeLayout);
        }
    }

    private class GetContent extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            return CoerceJavaToLua.coerce(relativeLayout);
        }
    }

    private void addView(View view, RelativeLayout.LayoutParams viewParam) {
        relativeLayout.addView(view, viewParam);
    }


    public static class LayoutParamsLib {

        public LayoutParamsLib() {
        }

        public static final int MATCH_PARENT = RelativeLayout.LayoutParams.MATCH_PARENT;
        public static final int WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT;

        public static final int BELOW = RelativeLayout.BELOW;
        public static final int RIGHT_OF = RelativeLayout.RIGHT_OF;


        public static RelativeLayout.LayoutParams getParams(int w, int h) {
            return new RelativeLayout.LayoutParams(w, h);
        }

        public static RelativeLayout.LayoutParams getParams(int w, int h, int rule) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w, h);
            layoutParams.addRule(rule);
            return layoutParams;
        }

        public static RelativeLayout.LayoutParams getParams(int w, int h, int rule, int anchorId) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w, h);
            layoutParams.addRule(rule, anchorId);
            return layoutParams;
        }

        public static RelativeLayout.LayoutParams setMargin(RelativeLayout.LayoutParams params, int left, int top, int right, int bottom) {
            if (params != null) {
                params.setMargins(left, top, right, bottom);
            }
            return params;
        }


    }
}
