package com.iqyi.paopao.lua.LuaTable;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.iqyi.paopao.lua.utils.LuaUtil;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ButtonLuaTable {

    private Button btn;
    private LuaValue mOnClick;
    private Globals mGlobals;
    private LuaValue mMetaTable;

    public ButtonLuaTable(Globals globals, LuaValue metaTable) {
        this.mGlobals = globals;
        this.mMetaTable = metaTable;
        init();
    }

    private void init() {
        mMetaTable.set("createButton",new createButton());
        mMetaTable.set("setText", new setText());
        mMetaTable.set("setTextColor", new setTextColor());
        mMetaTable.set("onClick", new onClick());
    }

    private class createButton extends ZeroArgFunction{
        @Override
        public LuaValue call() {
            btn = new Button(mGlobals.mCurContainer.getContext());
            mGlobals.mCurContainer.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (LuaUtil.isValid(mOnClick)) {
                        LuaUtil.callFunction(mOnClick);
                    }
                }
            });
            return LuaValue.NIL;
        }
    }

    private class setText extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            btn.setText(arg.checkjstring());
            return LuaValue.valueOf(arg.checkjstring());
        }
    }

    private class setTextColor extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            btn.setTextColor(Color.parseColor(arg.checkjstring()));
            return LuaValue.valueOf(arg.checkjstring());
        }
    }

    private class onClick extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            mOnClick = arg.isfunction() ? arg : LuaUtil.getFunction(arg, "onClick");
            return mOnClick;
        }
    }


    public static LuaValue valueOf(Globals globals, LuaValue metaTable) {
        return new ButtonLuaTable(globals, metaTable).mMetaTable;
    }

}
