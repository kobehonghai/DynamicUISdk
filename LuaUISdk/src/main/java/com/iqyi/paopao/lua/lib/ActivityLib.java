package com.iqyi.paopao.lua.lib;


import com.iqyi.paopao.lua.LuaTable.ActivityLuaTable;
import com.iqyi.paopao.lua.lib.base.BaseFunctionLib;

import org.luaj.vm2.LuaValue;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ActivityLib extends BaseFunctionLib {

    public ActivityLib() {
        super("Activity");
    }

    @Override
    public LuaValue createLuaTable(LuaValue env, LuaValue metaTable) {
        return ActivityLuaTable.valueOf(env.checkglobals(), metaTable);
    }
}
