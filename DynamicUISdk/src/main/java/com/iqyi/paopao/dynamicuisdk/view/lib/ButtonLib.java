package com.iqyi.paopao.dynamicuisdk.view.lib;

import android.graphics.Color;
import android.widget.Button;

import com.iqyi.paopao.dynamicuisdk.view.lib.base.BaseFunctionLib;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ButtonLib extends BaseFunctionLib {

    private Button btn;

    public ButtonLib() {
        super("LButton");
    }

    @Override
    public void setMethod(LuaTable luaTable) {
        luaTable.set("setText", new setText());
        luaTable.set("setTextColor", new setTextColor());
    }

    @Override
    public LuaValue createCreator(LuaValue env, LuaValue metaTable) {
        Globals g = env.checkglobals();
        btn = new Button(g.mCurContainer.getContext());
        g.mCurContainer.addView(btn);
        return super.createCreator(env, metaTable);
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
}
