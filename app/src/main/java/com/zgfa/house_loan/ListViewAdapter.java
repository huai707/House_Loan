package com.zgfa.house_loan;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/25.
 */
public class ListViewAdapter extends SimpleAdapter{

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    private LayoutInflater mInflater;
    public ListViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.mInflater=LayoutInflater.from(context);
    }


    //重写adapter，隔行背景颜色不一样
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.item,null);
        }
        if (position%2 == 0)
        {
            convertView.setBackgroundColor(Color.parseColor("#a6bff2"));
        }else
        {
            convertView.setBackgroundColor(Color.parseColor("#ffffffff"));
        }
        return super.getView(position, convertView, parent);
    }
}
