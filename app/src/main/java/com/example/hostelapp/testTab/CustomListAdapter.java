package com.example.hostelapp.testTab;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hostelapp.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class CustomAdapter extends BaseAdapter {

    private static final String TAG = "CustomAdapter";

    public static final String BREAKFAST = "breakfast";
    public static final String LUNCH = "lunch";
    public static final String DINNER = "dinner";

    public List<String> dataSet;
    Context mContext;


    public CustomAdapter( LinkedHashMap<String, List<String>> data, Context context) {

        Log.d(TAG, "CustomAdapter: " + data.toString());

        this.dataSet = new ArrayList<>();
        this.mContext = context;



    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    // View lookup cache
    private static class ViewHolder {
//        TextView txtName;
        TextView txtID;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.detail_view_meal_model, parent, false);
            viewHolder.txtID = (TextView) convertView.findViewById(R.id.textViewUserID);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtID.setText(getItem(position));
        // Return the completed view to render on screen
        return convertView;
    }
}

