package com.iqyi.paopao.lua.lib;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.iqyi.paopao.lua.lib.base.BaseFunctionLib;
import com.iqyi.paopao.lua.utils.DimenUtil;
import com.iqyi.paopao.lua.utils.ViewUtil;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhonghai on 2017/9/11.
 */

public class LinearLayoutLib extends BaseFunctionLib {

    private Context mContext;


    public LinearLayoutLib(Context context) {
        super("LLinearLayout");
        mContext = context;
    }

    @Override
    public LuaValue createLuaTable(LuaValue env, LuaValue metaTable) {
        metaTable.set("newLayoutParams", new LinearLayoutLib.NewLayoutParams());
        metaTable.set("newLayout", new LinearLayoutLib.NewLayout());
        return metaTable;
    }

    private class NewLayout extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            LLinearLayout layout = new LLinearLayout(mContext);
            return CoerceJavaToLua.coerce(layout);
        }
    }

    private class NewLayoutParams extends ThreeArgFunction {
        @Override
        public LuaValue call(LuaValue w,LuaValue h,LuaValue weight) {
            LLayoutParams layoutParams;
            if (weight.isnil()){
                layoutParams= new LLayoutParams((int) CoerceLuaToJava.coerce(w, Integer.class),(int)CoerceLuaToJava.coerce(h,Integer.class));
            }else {
                layoutParams= new LLayoutParams((int) CoerceLuaToJava.coerce(w, Integer.class),(int)CoerceLuaToJava.coerce(h,Integer.class),(float) CoerceLuaToJava.coerce(weight,Float.class));
            }
            return CoerceJavaToLua.coerce(layoutParams);
        }
    }

    public class LLinearLayout extends LinearLayout{

        public static final int VERTICAL = LinearLayout.VERTICAL;
        public static final int HORIZONTAL = LinearLayout.HORIZONTAL;

        public static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
        public static final int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;

        private Map<String,View> viewMap = new HashMap<>();

        public LLinearLayout(Context context) {
            super(context);
            ViewUtil.setId(this);
        }

        public void addLView(View child, LinearLayout.LayoutParams params, String key){
            this.addView(child,params);
            this.viewMap.put(key,child);
        }

        public View getLChildView(String key){
            return this.viewMap.get(key);
        }
    }


    public class LLayoutParams extends LinearLayout.LayoutParams{

        public LLayoutParams(int width, int height) {
            super(DimenUtil.dpiToPx(width), DimenUtil.dpiToPx(height));
        }

        public LLayoutParams(int width, int height, float weight) {
            super(DimenUtil.dpiToPx(width), DimenUtil.dpiToPx(height), weight);
        }
    }
}
