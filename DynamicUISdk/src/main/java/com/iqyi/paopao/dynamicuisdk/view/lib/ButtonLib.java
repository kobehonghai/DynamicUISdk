package com.iqyi.paopao.dynamicuisdk.view.lib;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.iqyi.paopao.dynamicuisdk.view.lib.base.BaseFunctionLib;
import com.iqyi.paopao.dynamicuisdk.view.lib.base.ButtonClickListener;

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
    private ButtonClickListener buttonClickListener;

    public ButtonLib(ButtonClickListener listener) {
        super("LButton");
        buttonClickListener=listener;
    }

    @Override
    public void setMethod(LuaTable luaTable) {
        luaTable.set("setText", new setText());
        luaTable.set("setTextColor", new setTextColor());
        luaTable.set("setOnClickAction", new setOnClickAction());
    }

    @Override
    public LuaValue createCreator(LuaValue env, LuaValue metaTable) {
        Globals g = env.checkglobals();
        btn = new Button(g.mCurContainer.getContext());
        g.mCurContainer.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonClickListener!=null){
                    buttonClickListener.onClick();
                }
            }
        });
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

    private class setOnClickAction extends OneArgFunction{

        @Override
        public LuaValue call(LuaValue arg) {
            try {
                Intent intent=new Intent(btn.getContext(),Class.forName(arg.checkjstring()));
                btn.getContext().startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return LuaValue.valueOf(arg.checkjstring());
        }
    }
}
