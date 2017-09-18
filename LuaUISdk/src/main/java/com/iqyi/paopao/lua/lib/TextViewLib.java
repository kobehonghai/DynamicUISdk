package com.iqyi.paopao.lua.lib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;


import com.iqyi.paopao.lua.lib.base.BaseFunctionLib;
import com.iqyi.paopao.lua.utils.DimenUtil;
import com.iqyi.paopao.lua.utils.ViewUtil;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

/**
 * Created by liuhonghai on 2017/9/12.
 */

public class TextViewLib extends BaseFunctionLib {

    private Context mContext;

    public TextViewLib(Context context) {
        super("LTextView");
        mContext=context;
    }

    @Override
    public LuaValue createLuaTable(LuaValue env, LuaValue metaTable) {
        metaTable.set("newTextView", new TextViewLib.NewTextView());
        return metaTable;
    }

    private class NewTextView extends ZeroArgFunction {
        @Override
        public LuaValue call() {
            LTextView textView=new LTextView(mContext);
            return CoerceJavaToLua.coerce(textView);
        }
    }

    public class LTextView extends android.support.v7.widget.AppCompatTextView{
        public static final int CENTER = Gravity.CENTER;
        public static final int CENTER_HORIZONTAL = Gravity.CENTER_HORIZONTAL;
        public static final int CENTER_VERTICAL = Gravity.CENTER_VERTICAL;

        public static final int LEFT = Gravity.LEFT;

        public LTextView(Context context) {
            super(context);
            ViewUtil.setId(this);
        }

        public void setLSingleLine(){
            this.setMaxLines(1);
            this.setEllipsize(TextUtils.TruncateAt.END);
        }

        public void setLTextBold(){
            this.setTypeface(Typeface.DEFAULT_BOLD);
        }

        public void setLTextColor(String colorString){
            this.setTextColor(Color.parseColor(colorString));
        }

        public void setLTextSize(float size){
            this.setTextSize(DimenUtil.spToPx(size));
        }



    }
}
