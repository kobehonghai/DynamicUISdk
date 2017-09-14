package com.iqyi.paopao.lua.lib.base;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class BaseFunctionLib extends TwoArgFunction {

    private String luaName;

    public BaseFunctionLib(String luaName) {
        this.luaName = luaName;
    }

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable luaTable = tableOf();
        env.set(luaName, createLuaTable(env, luaTable));
        env.get("package").get("loaded").set(luaName, luaTable);
        return luaTable;
    }

    public String getLuaName() {
        return luaName;
    }

    public LuaValue createLuaTable(LuaValue env, LuaValue metaTable) {
        return metaTable;
    }

}
