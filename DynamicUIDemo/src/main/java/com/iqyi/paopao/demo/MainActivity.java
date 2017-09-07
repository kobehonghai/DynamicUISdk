package com.iqyi.paopao.demo;

import android.app.Activity;
import android.os.Bundle;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LuajView view = new LuajView(this);
        setContentView(view);
        try {
            LuaValue activity = CoerceJavaToLua.coerce(this);
            LuaValue viewobj = CoerceJavaToLua.coerce(view);
            view.globals.loadfile("activity.lua").call(activity, viewobj);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


}
