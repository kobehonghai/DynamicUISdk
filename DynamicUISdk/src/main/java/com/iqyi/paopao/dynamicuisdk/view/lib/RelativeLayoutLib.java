package com.iqyi.paopao.dynamicuisdk.view.lib;

import android.content.Context;
import android.widget.RelativeLayout;

import com.iqyi.paopao.dynamicuisdk.view.lib.base.BaseFunctionLib;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
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
        luaTable.set("newLayout", new RelativeLayoutLib.NewLayout());
        luaTable.set("newLayoutParams", new RelativeLayoutLib.NewLayoutParams());
    }

    private class NewLayout extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            LRelativeLayout layout = new LRelativeLayout(mContext);
            return CoerceJavaToLua.coerce(layout);
        }
    }

    private class NewLayoutParams extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue w,LuaValue h) {
            LLayoutParams layoutParams = new LLayoutParams((int)CoerceLuaToJava.coerce(w,Integer.class),(int)CoerceLuaToJava.coerce(h,Integer.class));
            return CoerceJavaToLua.coerce(layoutParams);
        }
    }


    public class LRelativeLayout extends RelativeLayout{

        public static final int MATCH_PARENT = RelativeLayout.LayoutParams.MATCH_PARENT;
        public static final int WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT;

        public static final int BELOW = RelativeLayout.BELOW;
        public static final int RIGHT_OF = RelativeLayout.RIGHT_OF;

        public static final int ALIGN_PARENT_RIGHT = RelativeLayout.ALIGN_PARENT_RIGHT;

        public LRelativeLayout(Context context) {
            super(context);
        }
    }

    public class LLayoutParams extends RelativeLayout.LayoutParams {

        public LLayoutParams(int w, int h) {
            super(w, h);
        }
    }
}
