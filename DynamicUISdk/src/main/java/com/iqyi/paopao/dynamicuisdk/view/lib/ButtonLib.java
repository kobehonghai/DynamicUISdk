package com.iqyi.paopao.dynamicuisdk.view.lib;

import com.iqyi.paopao.dynamicuisdk.LuaTable.ButtonLuaTable;
import com.iqyi.paopao.dynamicuisdk.view.lib.base.BaseFunctionLib;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ButtonLib extends BaseFunctionLib {


    public ButtonLib() {
        super("LButton");
    }

    @Override
    public LuaValue createLuaTable(LuaValue env, LuaValue metaTable) {
        Globals g = env.checkglobals();
        return ButtonLuaTable.valueOf(g,metaTable);
    }
}
