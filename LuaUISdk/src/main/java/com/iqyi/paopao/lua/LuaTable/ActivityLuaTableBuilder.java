package com.iqyi.paopao.lua.LuaTable;

import android.content.Intent;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ActivityLuaTableBuilder {

    private Globals mGlobals;
    private LuaValue mMetaTable;
    private Intent mIntent;

    private ActivityLuaTableBuilder(Globals globals, LuaValue metaTable) {
        this.mGlobals = globals;
        this.mMetaTable = metaTable;
        init();
    }

    private void init() {
        mMetaTable.set("skip", new skip());
        mMetaTable.set("setIntentKV", new setIntentKV());
    }

    private class skip extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue arg) {
            String className = arg.checkjstring();
            mIntent.setClassName(mGlobals.mCurContainer.getContext(), className);
            mGlobals.mCurContainer.getContext().startActivity(mIntent);
            return LuaValue.NIL;
        }
    }

    private class setIntentKV extends TwoArgFunction {
        @Override
        public LuaValue call(LuaValue arg1, LuaValue arg2) {
            if (mIntent == null) {
                mIntent = new Intent();
            }
            mIntent.putExtra(arg1.checkjstring(), arg2.checkjstring());
            return null;
        }
    }

    public static LuaValue valueOf(Globals globals, LuaValue metaTable) {
        return new ActivityLuaTableBuilder(globals, metaTable).mMetaTable;
    }


}
