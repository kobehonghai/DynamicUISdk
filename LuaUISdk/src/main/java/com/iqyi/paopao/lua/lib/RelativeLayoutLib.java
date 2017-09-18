package com.iqyi.paopao.lua.lib;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.iqyi.paopao.lua.lib.base.BaseFunctionLib;
import com.iqyi.paopao.lua.utils.DimenUtil;
import com.iqyi.paopao.lua.utils.ViewUtil;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import java.util.HashMap;
import java.util.Map;

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
    public LuaValue createLuaTable(LuaValue env, LuaValue metaTable) {
        metaTable.set("newLayout", new RelativeLayoutLib.NewLayout());
        metaTable.set("newLayoutParams", new RelativeLayoutLib.NewLayoutParams());
        return metaTable;
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
        public static final int LEFT_OF = RelativeLayout.LEFT_OF;

        public static final int ALIGN_PARENT_RIGHT = RelativeLayout.ALIGN_PARENT_RIGHT;

        public static final int CENTER_VERTICAL = RelativeLayout.CENTER_VERTICAL;

        private Map<String,View> viewMap = new HashMap<>();

        public LRelativeLayout(Context context) {
            super(context);
            ViewUtil.setId(this);
        }

        public void addLView(View child, RelativeLayout.LayoutParams params, String key){
            this.addView(child,params);
            this.viewMap.put(key,child);
        }

        public View getLChildView(String key){
            return this.viewMap.get(key);
        }
    }

    public class LLayoutParams extends RelativeLayout.LayoutParams {

        public LLayoutParams(int w, int h) {
            super(DimenUtil.dpiToPx(w), DimenUtil.dpiToPx(h));

        }

    }
}
