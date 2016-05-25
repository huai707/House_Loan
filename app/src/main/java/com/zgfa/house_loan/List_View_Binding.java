package com.zgfa.house_loan;

import android.content.Context;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zgfa on 2014/12/8.
 */

//绑定ListView数据
public class List_View_Binding {

    public static void list_show(Context cc, ListView lv, int ll_months, double[] ld_month_money, double[] ld_month_lx, double[] ld_month_bj, double[] ld_month_sybj) {

        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 1; i <= ll_months; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("item_txv_qc", i);   //期次
            item.put("item_txv_erery", number_dec.getDecimal(ld_month_money[i - 1]));     //每月偿还
            item.put("item_txv_lx", number_dec.getDecimal(ld_month_lx[i - 1])); //偿还利息
            item.put("item_txv_bj", number_dec.getDecimal(ld_month_bj[i - 1]));          //偿还本金
            item.put("item_txv_sybj", number_dec.getDecimal(ld_month_sybj[i - 1]));     //剩余本金
            data.add(item);
        }
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        System.out.println(" ListViewAdapter");//调试语句

        ListViewAdapter adapter;
        adapter = new ListViewAdapter(cc, data, R.layout.item,
                new String[]{"item_txv_qc", "item_txv_erery", "item_txv_lx", "item_txv_bj", "item_txv_sybj"},
                new int[]{R.id.item_txv_qc, R.id.item_txv_erery, R.id.item_txv_lx, R.id.item_txv_bj, R.id.item_txv_sybj});
        //实现列表的显示
        lv.setAdapter(adapter);

    }

}
