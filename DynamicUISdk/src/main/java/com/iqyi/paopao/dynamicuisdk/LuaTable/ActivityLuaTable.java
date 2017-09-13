package com.iqyi.paopao.dynamicuisdk.LuaTable;

import android.content.Intent;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class ActivityLuaTable {

    private Globals mGlobals;
    private LuaValue mMetaTable;

    public ActivityLuaTable(Globals globals, LuaValue metaTable) {
        this.mGlobals = globals;
        this.mMetaTable = metaTable;
        init();
    }

    private void init() {
        mMetaTable.set("skip", new skip());
    }

    private class skip extends OneArgFunction {

        @Override
        public LuaValue call(LuaValue arg) {
            String route = null;
            try {
                route = arg.checkjstring();
                Intent intent = new Intent(mGlobals.mCurContainer.getContext(),
                        Class.forName(route));
                mGlobals.mCurContainer.getContext().startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return LuaValue.valueOf(route);
        }
    }

    public static LuaValue valueOf(Globals globals, LuaValue metaTable) {
        return new ActivityLuaTable(globals, metaTable).mMetaTable;
    }


}
