/**
 *
 */
package com.zgfa.house_loan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        ListView listView = (ListView) findViewById(R.id.listView_about);
        listView.setAdapter(new ArrayAdapter<String>(this,        //此例中用的是ArrayAdapter
                android.R.layout.simple_list_item_1, getData()));//使用系统自带的布局文件

    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        data.add("      房贷计算器，为购房者提供2014最新房贷计算器。" +
                "包括商业房贷计算器，公积金贷款计算器，组合贷款计算器。");
        data.add("v1.0（2014-12-04） \n" +
                "       --第一版更新");
        data.add("v1.0（2014-12-06） \n" +
                "       --第二版更新，完善商业，跟公积金贷款。组合贷款开发中，更多功能请等待开放~~~");
        data.add("v1.0（2014-12-08） \n" +
                "       --第三版更新，所有功能基本完善，更多功能请等待开放~~~");
        data.add("v1.1（2016-05-26） \n" +
                "       --更新最新基本利率，修改每期金额样式~~~");

        return data;
    }

    //完美解决点击空白处，隐藏软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
