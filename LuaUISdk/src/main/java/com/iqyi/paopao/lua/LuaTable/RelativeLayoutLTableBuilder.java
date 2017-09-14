package com.iqyi.paopao.lua.LuaTable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.iqyi.paopao.lua.utils.LuaUtil;
import com.iqyi.paopao.lua.utils.ViewUtil;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class RelativeLayoutLTableBuilder extends BaseViewLTableBuilder<RelativeLayout> {


    public RelativeLayoutLTableBuilder(Globals globals, LuaValue metaTable) {
        super(globals, metaTable);
        init();
    }

    private void init() {
        mMetaTable.set("addView", new addView());
    }

    private class addView extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue arg1, LuaValue arg2) {
            View childView = (View) CoerceLuaToJava.coerce(arg1, View.class);
            if (LuaUtil.isString(arg2)) {
                childView.setTag(arg2.checkstring());
            }
            view.addView(childView);
            return null;
        }
    }


    @NonNull
    @Override
    public RelativeLayout getView(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        ViewUtil.setId(relativeLayout);
        return relativeLayout;
    }

    public static LuaValue valueOf(Globals globals, LuaValue metaTable) {
        return new RelativeLayoutLTableBuilder(globals, metaTable).mMetaTable;
    }

}
