package com.iqyi.paopao.demo.listitem;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.iqyi.paopao.demo.R;

import java.util.ArrayList;

/**
 * Created by liuhonghai on 2017/9/8.
 */

public class ListDemoFragment extends Fragment {
    ListView listView;
    ArrayList<Data> dataArrayList = new ArrayList<>();
    MyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.list_demo_fragment,container,false);
        initView(root);
        initData();
        return root;
    }

    private void initView(View root) {
        listView=root.findViewById(R.id.list_view);
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }

    private void initData(){
        for (int i=0;i<60;i++){
            Data data=new Data();
            data.setNum(i);
            data.setName(i+"Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_Hello_"+i);
            data.setIcon("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=3d2175db3cd3d539d530078052ee8325/b7003af33a87e950c1e1a6491a385343fbf2b425.jpg");
            data.setLevel("http://pic36.photophoto.cn/20150710/0005018387816674_b.jpg");
            data.setContribution(100-i);
            dataArrayList.add(data);
        }
        adapter.setData(dataArrayList);
        adapter.notifyDataSetChanged();
    }


    private class MyAdapter extends BaseAdapter{
        ArrayList<Data> data=new ArrayList<>();

        public void setData(ArrayList<Data> list){
            data=list;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //ViewHolder holder;
            //ListItemView itemView;
            ListItemLuaView itemLuaView;
            if (view ==null){
                //holder=new ViewHolder();
                //itemView=new ListItemView(viewGroup.getContext());
                itemLuaView=new ListItemLuaView(viewGroup.getContext());
                view = itemLuaView.getView();
                //holder.num=itemView.getNum();
                /*holder.icon=itemView.getIcon();
                holder.name=itemView.getName();
                holder.level=itemView.getLevel();
                holder.contribution=itemView.getContribution();*/
                view.setTag(itemLuaView);

                Log.i("Test","position="+i+"---"+itemLuaView.toString());
            }else {
                //holder= (ViewHolder) view.getTag();
                itemLuaView= (ListItemLuaView) view.getTag();
                Log.i("Test","复用 position="+i);
            }
            itemLuaView.setData(data.get(i));
            //Data d=data.get(i);
            //holder.num.setText(d.getNum()+"");
           /* holder.icon.setImageResource(R.mipmap.ic_launcher);
            holder.name.setText(d.getName());
            holder.level.setImageResource(R.mipmap.ic_launcher);
            holder.contribution.setText("贡献"+d.getContribution()+"影响力");*/
            return view;
        }
    }

    public static class ViewHolder{
        TextView num;
        ImageView icon;
        TextView name;
        ImageView level;
        TextView contribution;
    }

    private class Data{
        int num;
        String icon;
        String name;
        String level;
        int contribution;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getContribution() {
            return contribution;
        }

        public void setContribution(int contribution) {
            this.contribution = contribution;
        }
    }
}
