package com.iqyi.paopao.lua.LuaTable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.iqyi.paopao.lua.utils.DimenUtil;
import com.iqyi.paopao.lua.utils.LuaUtil;
import com.iqyi.paopao.lua.utils.ViewUtil;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

/**
 * Created by LiYong on 2017/9/14.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public abstract class BaseViewLTableBuilder<T extends View> {

    protected T view;
    protected LuaValue mOnClick;
    protected Globals mGlobals;
    protected LuaValue mMetaTable;


    public BaseViewLTableBuilder(Globals globals, LuaValue metaTable) {
        this.mGlobals = globals;
        this.mMetaTable = metaTable;
        mMetaTable.set("newView", new newView());
        mMetaTable.set("onClick", new onClick());
        mMetaTable.set("addRule", new addRule());
        mMetaTable.set("setMargin", new setMargin());
    }

    private class onClick extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            mOnClick = arg.isfunction() ? arg : LuaUtil.getFunction(arg, "onClick");
            return mOnClick;
        }
    }

    private class newView extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            if (view == null) {
                view = getView(mGlobals.getContext());
                mGlobals.mCurContainer.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (LuaUtil.isValid(mOnClick)) {
                            LuaUtil.callFunction(mOnClick);
                        }
                    }
                });
            }
            return CoerceJavaToLua.coerce(view);
        }
    }

    private class addRule extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue arg1, LuaValue arg2) {
            RelativeLayout.LayoutParams params = ViewUtil.getRLayoutParams(view);
            int verb = arg1.checkint();
            if (arg2 != null && arg2 != LuaValue.NIL) {
                View childView = (View) CoerceLuaToJava.coerce(arg2, View.class);
                params.addRule(verb, childView.getId());
            } else {
                params.addRule(verb);
            }
            view.setLayoutParams(params);
            return LuaValue.NIL;
        }
    }

    private class setMargin extends VarArgFunction {
        @Override
        public Varargs invoke(Varargs args) {
            int left = DimenUtil.dpiToPx(args.arg(1));
            int top = DimenUtil.dpiToPx(args.arg(2));
            int right = DimenUtil.dpiToPx(args.arg(3));
            int bottom = DimenUtil.dpiToPx(args.arg(4));
            RelativeLayout.LayoutParams params = ViewUtil.getRLayoutParams(view);
            params.leftMargin = left;
            params.topMargin = top;
            params.rightMargin = right;
            params.bottomMargin = bottom;
            view.setLayoutParams(params);
            return LuaValue.NIL;
        }
    }


    @NonNull
    public abstract T getView(Context context);
}
