package com.iqyi.paopao.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.iqyi.paopao.dynamicuisdk.globals.DynamicUIManager;
import com.iqyi.paopao.dynamicuisdk.view.lib.ButtonLib;
import com.iqyi.paopao.dynamicuisdk.view.lib.base.ButtonClickListener;

import org.luaj.vm2.LuaValue;


public class MainActivity extends Activity implements ButtonClickListener{

    LuaValue chunk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rootView = findViewById(R.id.layout);
        DynamicUIManager.getInstance().setContainerView(rootView);

        DynamicUIManager.getInstance().loadCustomLib(new ButtonLib(this));//绑定所有需要的组件

        chunk=DynamicUIManager.getInstance().loadLuaScript(this, "demo.lua");//加载对应业务的脚本
    }


    @Override
    public void onClick() {
        if (chunk!=LuaValue.NIL){
            DynamicUIManager.getInstance().getGlobals().get("buttonClick").invoke();
        }
    }
}
