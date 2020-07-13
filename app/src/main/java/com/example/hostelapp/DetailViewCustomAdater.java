/*
package com.example.hostelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailViewCustomAdater  extends BaseAdapter {


    private ArrayList<DetailViewModelClass> dataSet = new ArrayList<DetailViewModelClass>();
    LayoutInflater layoutInflater = null;

    Context context;

    public DetailViewCustomAdater(ArrayList<DetailViewModelClass> dataSet, Context context) {
        this.dataSet = dataSet;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    // View lookup cache
    private static class ViewHolder {
        TextView txttitle;
        TextView txtid;
        TextView txtname;
    }

    ViewHolder viewHolder = null;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        final  int pos = i;

        if( v == null ){
            viewHolder = new ViewHolder();
            v = layoutInflater.inflate(R.layout.detail_view_meal_model, null);
            
            viewHolder.txttitle = (TextView) v.findViewById(R.id.textViewTitle);
            viewHolder.txtid = (TextView) v.findViewById(R.id.textViewUserID);
            viewHolder.txtname = (TextView) v.findViewById(R.id.textViewUserName);
            
            v.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) v.getTag();
        }
        
        viewHolder.txttitle.setText(dataSet.get(pos).title);
        return null;
    }


}*/
