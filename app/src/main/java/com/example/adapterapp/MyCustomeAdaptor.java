package com.example.adapterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Layer;

public class MyCustomeAdaptor extends BaseAdapter {

    private Context context;
    private String[] items;

    public MyCustomeAdaptor(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            // convertView: is a recycled View that you can reuse to
            //              improve the performance of your list
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.my_list_item,parent,false);
        }else {
            // Reusing the View (that's recycled)
            holder = (ViewHolder) convertView.getTag();
        }
        return null;
    }
    static class ViewHolder{
        // Holds references to the views within an item layout
        TextView textView;
    }

}
