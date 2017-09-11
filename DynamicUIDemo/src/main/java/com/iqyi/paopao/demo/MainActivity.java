package com.iqyi.paopao.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.iqyi.paopao.dynamicuisdk.globals.DynamicUIManager;
import com.iqyi.paopao.dynamicuisdk.view.lib.ButtonLib;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rootView = findViewById(R.id.layout);
        DynamicUIManager.getInstance().setContainerView(rootView);


        DynamicUIManager.getInstance().loadCustomLib(new ButtonLib());
        DynamicUIManager.getInstance().loadLuaScript(this, "demo.lua");
    }

}
