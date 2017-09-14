package com.iqyi.paopao.demo.listitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.iqyi.paopao.demo.R;

/**
 * Created by liuhonghai on 2017/9/8.
 */

public class ListItemView extends FrameLayout{
    TextView num;
    ImageView icon;
    TextView name;
    ImageView level;
    TextView contribution;


    public ListItemView(Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.list_demo_item,this);
        num=findViewById(R.id.num);
        icon=findViewById(R.id.icon);
        name=findViewById(R.id.name);
        level=findViewById(R.id.level);
        contribution=findViewById(R.id.contribution);
    }

    public TextView getNum() {
        return num;
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getName() {
        return name;
    }

    public ImageView getLevel() {
        return level;
    }

    public TextView getContribution() {
        return contribution;
    }

}
