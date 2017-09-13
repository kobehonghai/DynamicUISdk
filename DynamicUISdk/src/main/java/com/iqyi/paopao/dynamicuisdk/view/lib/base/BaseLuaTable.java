package com.iqyi.paopao.dynamicuisdk.view.lib.base;

import android.content.Context;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

import java.io.Serializable;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class BaseLuaTable extends LuaTable implements Serializable {

    protected Globals mGlobals;
    private LuaValue mMetatable;
    public Varargs mVarargs;


    public BaseLuaTable(Globals globals, LuaValue metatable) {
        this(globals, metatable, NIL);
    }

    public BaseLuaTable(Globals globals, LuaValue metatable, Varargs varargs) {
        this.mGlobals = globals;
        this.mMetatable = metatable;
        this.mVarargs = varargs;
    }

    public Globals getGlobals() {
        return mGlobals;
    }

    public Context getContext() {
        return getGlobals() != null ? getGlobals().mCurContainer.getContext() : null;
    }
}
