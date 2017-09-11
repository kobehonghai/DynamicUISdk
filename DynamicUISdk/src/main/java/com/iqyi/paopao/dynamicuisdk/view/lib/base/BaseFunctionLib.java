package com.iqyi.paopao.dynamicuisdk.view.lib.base;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * Created by LiYong on 2017/9/11.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public abstract class BaseFunctionLib extends TwoArgFunction {

    protected String luaName;

    public BaseFunctionLib(String luaName) {
        this.luaName = luaName;
    }

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable luaTable = tableOf();
        setMethod(luaTable);
        env.set(luaName,createCreator(env,luaTable));
        env.get("package").get("loaded").set(luaName, luaTable);
        return luaTable;
    }

    public abstract void setMethod(LuaTable luaTable);

    public LuaValue createCreator(LuaValue env, LuaValue metaTable) {
        return metaTable;
    }
}
