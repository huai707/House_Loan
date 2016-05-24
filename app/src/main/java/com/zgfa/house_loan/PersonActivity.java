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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 信息Activity
 *
 * @author zgfa
 * @since 2014年12月6日 22:28:09
 */
public class PersonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("这是信息！");
        setContentView(R.layout.lay_gjj);
        //绑定贷款期限
        Spinner spinner = (Spinner) findViewById(R.id.spinner_years_gjj);
        binding_years(spinner);
        binding_rate();
        //绑定计算按钮
        binding_count();
    }

    public void binding_years(Spinner spinner) {
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.spinner_years);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        spinner.setAdapter(_Adapter);
        spinner.setSelection(20, true);

    }

    //绑定公积金利率
    protected void binding_rate() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_gjj_rate);
        final EditText editText = (EditText) findViewById(R.id.editText_gjj_rate);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.spinner_gjj_rate);
        final String[] gjj_rate = getResources().getStringArray(R.array.string_gjj_array);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        spinner.setAdapter(_Adapter);
        spinner.setSelection(1, true);
        editText.setText(gjj_rate[1]);
        //// 绑定利率点击事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String str = parent.getItemAtPosition(position).toString();
//                Toast.makeText(HomeActivity.this, "你点击的是:" + str, Toast.LENGTH_LONG).show();

                int ll_rate = (int) spinner.getItemIdAtPosition(position);
                editText.setText(gjj_rate[ll_rate]);

            }
        });
    }


    //计算按钮事件
    private void binding_count() {
        Button button = (Button) findViewById(R.id.button_gjj_count);
        final EditText editText_money = (EditText) findViewById(R.id.editText_gjj_money);
        final Spinner spinner_years = (Spinner) findViewById(R.id.spinner_years_gjj);
        final EditText editText_rate = (EditText) findViewById(R.id.editText_gjj_rate);
        final RadioGroup radioGroup_hk = (RadioGroup) findViewById(R.id.radioGroup_gjj);
        final RadioButton radioButton_bx = (RadioButton) findViewById(R.id.radioButton_gjj_bx);
        final RadioButton radioButton_bj = (RadioButton) findViewById(R.id.radioButton_gjj_bj);
        final EditText editText_lx = (EditText) findViewById(R.id.editText_gjjlx_show);
        final EditText editText_all = (EditText) findViewById(R.id.editText_gjjlj_show);
        final ListView listView = (ListView) findViewById(R.id.listView_gjj_show);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double ld_money;
                try {
                    ld_money = Double.valueOf(editText_money.getText().toString());
                    ld_money = ld_money * 10000;
                } catch (Exception e) {
                    Toast.makeText(PersonActivity.this, "贷款金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                double ld_rate;
                try {
                    ld_rate = Double.valueOf(editText_rate.getText().toString());
                    ld_rate = ld_rate / 100;
                } catch (Exception e) {
                    Toast.makeText(PersonActivity.this, "贷款利率不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                int ll_years;
                int ll_months;
                try {
                    ll_years = (int) spinner_years.getSelectedItemId();
                    if (ll_years == 0) {
                        ll_months = 6;
                    } else {
                        ll_months = ll_years * 12;
                    }

                } catch (Exception e) {
                    Toast.makeText(PersonActivity.this, "年份选择错误", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (radioGroup_hk.getCheckedRadioButtonId() == radioButton_bx.getId()) {
//                    计算公式：
//                    计划月还款额=〔贷款本金×月利率×（1+月利率）^还款月数〕÷〔（1+月利率）^还款月数－1〕
//                    还款月数=贷款年限×12
//                    月利率=年利率/12
//                    还款月数=贷款年限×12
//                    每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
//                    每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
//                    每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
//                    总利息=还款月数×每月月供额-贷款本金

                    double[] ld_month_money = new double[ll_months];  //计划月还款额
                    double[] ld_month_bj = new double[ll_months]; // 每月应还本金
                    double[] ld_month_lx = new double[ll_months]; // 每月应还利息
                    double[] ld_month_sybj = new double[ll_months + 1];   //每月剩余本金
                    double ld_all_lx_money;//总利息

                    ld_all_lx_money = 0;
                    ld_rate = ld_rate / 12;
                    ld_month_sybj[0] = ld_money;
                    for (int i = 1; i <= ll_months; i++) {
                        ld_month_money[i - 1] = ld_money * ld_rate * Math.pow((1 + ld_rate), ll_months) / (Math.pow((1 + ld_rate), ll_months) - 1);
                        ld_month_bj[i - 1] = ld_money * ld_rate * Math.pow((1 + ld_rate), i - 1) / (Math.pow((1 + ld_rate), ll_months) - 1);
                        ld_month_lx[i - 1] = ld_money * ld_rate * (Math.pow((1 + ld_rate), ll_months) - Math.pow((1 + ld_rate), i - 1)) / (Math.pow((1 + ld_rate), ll_months) - 1);
                        ld_month_sybj[i - 1] = ld_month_sybj[i - 1] - ld_month_bj[i - 1];
                        ld_month_sybj[i] = ld_month_sybj[i - 1];

                    }
                    ld_all_lx_money = ll_months * ld_month_money[0] - ld_money;
                    editText_lx.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money)));
                    editText_all.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money + ld_money)));

                    List_View_Binding.list_show(PersonActivity.this, listView, ll_months, ld_month_money, ld_month_lx, ld_month_bj, ld_month_sybj);

                }
                if (radioGroup_hk.getCheckedRadioButtonId() == radioButton_bj.getId()) {
//                    计算公式：
//                    计划月还款额=(贷款本金÷还款月数)+(贷款本金-累计已还本金)×月利率
//                    累计已还本金=已经归还贷款的月数×贷款本金/还款月数
                    // 每月应还本金=贷款本金÷还款月数
                    //每月应还利息=剩余本金×月利率=(贷款本金-已归还本金累计额)×月利率
                    //每月月供递减额=每月应还本金×月利率=贷款本金÷还款月数×月利率
                    //总利息=〔(总贷款额÷还款月数+总贷款额×月利率)+总贷款额÷还款月数×(1+月利率)〕÷2×还款月数-总贷款额
                    double[] ld_sum_have = new double[ll_months + 1]; //计划月还款额
                    double[] ld_month_money = new double[ll_months];  //累计已还本金
                    double[] ld_month_bj = new double[ll_months]; // 每月应还本金
                    double[] ld_month_lx = new double[ll_months]; // 每月应还利息
                    double[] ld_month_sybj = new double[ll_months];   //每月剩余本金
                    double ld_month_money_dj;//每月月供递减额
                    double ld_all_lx_money;//总利息
                    ld_rate = ld_rate / 12;
                    ld_month_money_dj = ld_money / ll_months * ld_rate;

                    for (int i = 1; i <= ll_months; i++) {
                        ld_month_money[i - 1] = (ld_money / ll_months) + (ld_money - ld_sum_have[i - 1]) * ld_rate;
                        ld_sum_have[i] = i * ld_money / ll_months;
                        ld_month_bj[i - 1] = ld_money / ll_months;
                        ld_month_lx[i - 1] = (ld_money - ld_sum_have[i - 1]) * ld_rate;
                        ld_month_sybj[i - 1] = ld_money - ld_sum_have[i];

                    }
                    ld_all_lx_money = (ld_money / ll_months + ld_money * ld_rate + ld_money / ll_months * (1 + ld_rate)) / 2 * ll_months - ld_money;
                    editText_lx.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money)));
                    editText_all.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money + ld_money)));

                    List_View_Binding.list_show(PersonActivity.this, listView, ll_months, ld_month_money, ld_month_lx, ld_month_bj, ld_month_sybj);

                }


            }


        });


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
