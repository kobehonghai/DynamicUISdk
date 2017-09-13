package com.iqyi.paopao.dynamicuisdk.utils;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

/**
 * Created by LiYong on 2017/9/13.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 */

public class LuaUtil {


    public static CharSequence getText(final Varargs varargs, int... poslist) {
        LuaValue value = getValue(varargs, poslist);
        return toText(value);
    }

    public static CharSequence toText(LuaValue luaValue) {
        if (LuaUtil.isString(luaValue)) {
            return luaValue.optjstring(null);
        }
        return null;
    }

    public static LuaFunction getFunction(final LuaValue valueList, String... keylist) {
        return (LuaFunction) getValueFromTable(LuaValue.TFUNCTION, valueList, keylist);
    }

    private static Object getValueFromTable(final int type, final Varargs valueList, String... keylist) {
        return getValueFromTable(type, valueList, null, keylist);
    }

    private static Object getValueFromTable(final int type, final Varargs varargs, Object defaultValue, String... keylist) {
        Object result = null;
        if (varargs instanceof LuaTable) {
            LuaTable varlist = ((LuaTable) varargs);
            if (keylist != null && keylist.length > 0) {
                for (int i = 0; i < keylist.length; i++) {
                    result = parseValue(type, varlist.get(keylist[i]));
                    if (result != null) {
                        break;
                    }
                }
            }
        }
        return result != null ? result : defaultValue;
    }


    public static LuaValue getValue(final Varargs varargs, int... poslist) {
        return (LuaValue) getValue(LuaValue.TVALUE, varargs, poslist);
    }
    private static Object getValue(final int type, final Varargs varargs, int... poslist) {
        return getValue(type, varargs, null, poslist);
    }

    private static Object getValue(final int type, final Varargs varargs, Object defaultValue, int... poslist) {
        Object result = null;
        if (varargs != null) {
            if (poslist != null && poslist.length > 0) {
                LuaValue value = null;
                for (int i = 0; i < poslist.length; i++) {
                    if (varargs.narg() >= poslist[i]) {
                        value = varargs.arg(poslist[i]);
                        result = parseValue(type, value);
                    }
                    if (result != null) {
                        break;
                    }
                }
            }
        }
        return result != null ? result : defaultValue;
    }



    private static Object parseValue(int type, LuaValue value) {
        switch (type) {
            case LuaValue.TBOOLEAN:
                if (isBoolean(value)) return value.checkboolean();
                break;
            case LuaValue.TNUMBER:
                if (isNumber(value)) return value.checknumber();
                break;
            case LuaValue.TSTRING:
                if (isString(value)) return value.checkjstring();
                break;
            case LuaValue.TTABLE:
                if (isTable(value)) return value.checktable();
                break;
            case LuaValue.TFUNCTION:
                if (isFunction(value)) return value.checkfunction();
                break;
            case LuaValue.TUSERDATA:
                if (isUserdata(value)) return value.checkuserdata();
                break;
            case LuaValue.TVALUE:
                return value;
        }
        return null;
    }

    public static boolean isString(final LuaValue target) {
        return target != null && target.type() == LuaValue.TSTRING;
    }

    public static boolean isNumber(final LuaValue target) {
        return target != null && target.type() == LuaValue.TNUMBER;
    }

    public static boolean isBoolean(final LuaValue target) {
        return target != null && target.type() == LuaValue.TBOOLEAN;
    }

    public static boolean isFunction(final LuaValue target) {
        return target != null && target.type() == LuaValue.TFUNCTION;
    }

    public static boolean isTable(final LuaValue target) {
        return target != null && target.type() == LuaValue.TTABLE;
    }

    public static boolean isUserdata(final LuaValue target) {
        return target != null && target.type() == LuaValue.TUSERDATA;
    }

    public static boolean isValid(final LuaValue target) {
        return target != null && target.type() != LuaValue.TNIL;
    }

    public static LuaValue callFunction(LuaValue target) {
        try {
            return (target != null && target.isfunction()) ? target.call() : LuaValue.NIL;
        } catch (Exception e) {
            e.printStackTrace();
            return LuaValue.NIL;
        }
    }


}
