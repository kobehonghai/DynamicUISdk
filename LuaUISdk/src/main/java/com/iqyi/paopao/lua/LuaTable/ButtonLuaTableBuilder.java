package com.iqyi.paopao.lua.LuaTable;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.iqyi.paopao.lua.utils.ViewUtil;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ButtonLuaTableBuilder extends BaseViewLTableBuilder<Button> {


    public ButtonLuaTableBuilder(Globals globals, LuaValue metaTable) {
        super(globals, metaTable);
        init();
    }

    private void init() {
        mMetaTable.set("setText", new setText());
        mMetaTable.set("setTextColor", new setTextColor());
    }


    private class setText extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            view.setText(arg.checkjstring());
            return LuaValue.valueOf(arg.checkjstring());
        }
    }

    private class setTextColor extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            view.setTextColor(Color.parseColor(arg.checkjstring()));
            return LuaValue.valueOf(arg.checkjstring());
        }
    }


    @NonNull
    @Override
    public Button getView(Context context) {
        Button button = new Button(context);
        ViewUtil.setId(button);
        return button;
    }

    public static LuaValue valueOf(Globals globals, LuaValue metaTable) {
        return new ButtonLuaTableBuilder(globals, metaTable).mMetaTable;
    }

}
