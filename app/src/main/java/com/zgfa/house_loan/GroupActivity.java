/**
 *
 */
package com.zgfa.house_loan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * 我的资料Activity
 *
 * @author zgfa
 * @since 2014年12月6日 23:05:28
 */
public class GroupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_group);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_group_years);
        binding_years(spinner);
        Spinner spinner_gjj = (Spinner) findViewById(R.id.spinner_group_gjj_years);
        binding_years(spinner_gjj);
        binding_sy_rate();
        binding_gjj_rate();
        binding_count();
        binding_sale();
    }

    //年限绑定
    protected void binding_years(Spinner spinner) {
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.spinner_years);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        spinner.setAdapter(_Adapter);
        spinner.setSelection(20, true);
    }

    //绑定商业利率
    protected void binding_sy_rate() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_group_sy_rate);
        final EditText editText = (EditText) findViewById(R.id.editText_group_sy_rate);

        final Spinner spinner_sale = (Spinner) findViewById(R.id.spinner_sy_group_sale);  //打折下拉
        final String[] mItems_nub = getResources().getStringArray(R.array.sale_array_number); //实际打折数额

        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.spinner_sy_rate);
        //商业利率数组
        final String[] sy_rate = getResources().getStringArray(R.array.string_sy_array);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        spinner.setAdapter(_Adapter);
        spinner.setSelection(1, true);
        editText.setText(sy_rate[1]);

        //// 绑定利率点击事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String str = parent.getItemAtPosition(position).toString();
//                Toast.makeText(HomeActivity.this, "你点击的是:" + str, Toast.LENGTH_LONG).show();

//                int ll_rate = (int) spinner.getItemIdAtPosition(position);
//                editText.setText(sy_rate[ ll_rate]);
                int ll_rate = (int) spinner.getItemIdAtPosition(position);
                int li_sale = spinner_sale.getSelectedItemPosition();
                try {
                    if (ll_rate == 0) {
                        editText.setText("");
                    } else {
                        editText.setText(String.valueOf(number_dec.getDecimal(Double.parseDouble(sy_rate[ll_rate]) * Double.parseDouble(mItems_nub[li_sale]))));
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    //商业打折绑定
    protected void binding_sale() {
        final Spinner spinner_sale = (Spinner) findViewById(R.id.spinner_sy_group_sale);  //打折下拉
        final Spinner spinner_sy_lv = (Spinner) findViewById(R.id.spinner_group_sy_rate);  //商业利率下拉
        final EditText editText = (EditText) findViewById(R.id.editText_group_sy_rate);
        // 建立数据源
        final String[] mItems = getResources().getStringArray(R.array.sale_array);      //商业打折显示数据源

        final String[] mItems_nub = getResources().getStringArray(R.array.sale_array_number); //实际打折数额
        final String[] mItem_rate = getResources().getStringArray(R.array.string_sy_array);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        spinner_sale.setAdapter(_Adapter);
        spinner_sale.setSelection(0, true);

        //// 绑定利率点击事件
        spinner_sale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int li_rate_point = (int) spinner_sy_lv.getSelectedItemId(); //当前商业利率
                int li_sale_point = (int) spinner_sale.getItemIdAtPosition(position);  //当前打折
                try {
                    if (li_rate_point == 0) {
                        editText.setText("");
                    } else {
                        double lde_rate = Double.parseDouble(mItem_rate[li_rate_point]) * Double.parseDouble(mItems_nub[li_sale_point]);
                        editText.setText(String.valueOf(number_dec.getDecimal(lde_rate)));
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    //绑定公积金利率
    protected void binding_gjj_rate() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_group_gjj_rate);
        final EditText editText = (EditText) findViewById(R.id.editText_group_gjj_rate);
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
        Button button = (Button) findViewById(R.id.button_group_count);
        final EditText editText_sy_money = (EditText) findViewById(R.id.editText_group_sy_money);
        final EditText editText_gjj_money = (EditText) findViewById(R.id.editText_group_gjj_money);
        final Spinner spinner_years_sy = (Spinner) findViewById(R.id.spinner_group_years);
        final Spinner spinner_years_gjj = (Spinner) findViewById(R.id.spinner_group_gjj_years);

        final EditText editText_sy_rate = (EditText) findViewById(R.id.editText_group_sy_rate);
        final EditText editText_gjj_rate = (EditText) findViewById(R.id.editText_group_gjj_rate);

        final RadioGroup radioGroup_hk = (RadioGroup) findViewById(R.id.radioGroup_group);
        final RadioButton radioButton_bx = (RadioButton) findViewById(R.id.radioButton_group_bx);
        final RadioButton radioButton_bj = (RadioButton) findViewById(R.id.radioButton_group_bj);

        final EditText editText_all_lx = (EditText) findViewById(R.id.editText_group_alllx_show);    //所有利息累计
        final EditText editText_all_lj = (EditText) findViewById(R.id.editText_group_alllj_show);//所有金额累计
        final EditText editText_group_sylx_show = (EditText) findViewById(R.id.editText_group_sylx_show); //商业利息累计
        final EditText editText_group_gjjlx_show = (EditText) findViewById(R.id.editText_group_gjjlx_show); //公积金利息累计

        final ListView listView = (ListView) findViewById(R.id.listView_group_show);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double ld_sy_money, ld_gjj_money;
                try {
                    ld_sy_money = Double.valueOf(editText_sy_money.getText().toString());
                    ld_gjj_money = Double.valueOf(editText_gjj_money.getText().toString());
                    ld_sy_money = ld_sy_money * 10000;
                    ld_gjj_money = ld_gjj_money * 10000;
                } catch (Exception e) {
                    Toast.makeText(GroupActivity.this, "贷款金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                double ld_sy_rate, ld_gjj_rate;
                try {
                    ld_sy_rate = Double.valueOf(editText_sy_rate.getText().toString());
                    ld_gjj_rate = Double.valueOf(editText_gjj_rate.getText().toString());
                    ld_sy_rate = ld_sy_rate / 100;
                    ld_gjj_rate = ld_gjj_rate / 100;
                } catch (Exception e) {
                    Toast.makeText(GroupActivity.this, "贷款利率不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                int ll_years_sy;
                int ll_months_sy;
                int ll_years_gjj;
                int ll_months_gjj;
                try {
                    ll_years_sy = (int) spinner_years_sy.getSelectedItemId();
                    ll_years_gjj = (int) spinner_years_gjj.getSelectedItemId();
                    if (ll_years_sy == 0) {
                        ll_months_sy = 6;
                    } else {
                        ll_months_sy = ll_years_sy * 12;
                    }
                    if (ll_years_gjj == 0) {
                        ll_months_gjj = 6;
                    } else {
                        ll_months_gjj = ll_years_gjj * 12;
                    }

                } catch (Exception e) {
                    Toast.makeText(GroupActivity.this, "年份选择错误", Toast.LENGTH_SHORT).show();
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

                    double[] ld_month_money = new double[ll_months_sy];  //计划月还款额
                    double[] ld_month_bj = new double[ll_months_sy]; // 每月应还本金
                    double[] ld_month_lx = new double[ll_months_sy]; // 每月应还利息
                    double[] ld_month_sybj = new double[ll_months_sy + 1];   //每月剩余本金
                    double ld_all_lx_money;//总利息
                    ld_all_lx_money = 0;
                    ld_sy_rate = ld_sy_rate / 12;
                    ld_month_sybj[0] = ld_sy_money;

                    double[] ld_gjj_month_money = new double[ll_months_gjj];  //公积金计划月还款额
                    double[] ld_gjj_month_bj = new double[ll_months_gjj]; // 公积金每月应还本金
                    double[] ld_gjj_month_lx = new double[ll_months_gjj]; // 公积金每月应还利息
                    double[] ld_gjj_month_sybj = new double[ll_months_gjj + 1];   //公积金每月剩余本金
                    double ld_gjj_all_lx_money;//公积金总利息
                    ld_gjj_all_lx_money = 0;
                    ld_gjj_rate = ld_gjj_rate / 12;
                    ld_gjj_month_sybj[0] = ld_gjj_money;

                    //商业计算
                    for (int i = 1; i <= ll_months_sy; i++) {
                        ld_month_money[i - 1] = ld_sy_money * ld_sy_rate * Math.pow((1 + ld_sy_rate), ll_months_sy) / (Math.pow((1 + ld_sy_rate), ll_months_sy) - 1);
                        ld_month_bj[i - 1] = ld_sy_money * ld_sy_rate * Math.pow((1 + ld_sy_rate), i - 1) / (Math.pow((1 + ld_sy_rate), ll_months_sy) - 1);
                        ld_month_lx[i - 1] = ld_sy_money * ld_sy_rate * (Math.pow((1 + ld_sy_rate), ll_months_sy) - Math.pow((1 + ld_sy_rate), i - 1)) / (Math.pow((1 + ld_sy_rate), ll_months_sy) - 1);
                        ld_month_sybj[i - 1] = ld_month_sybj[i - 1] - ld_month_bj[i - 1];
                        ld_month_sybj[i] = ld_month_sybj[i - 1];

                    }
                    ld_all_lx_money = ll_months_sy * ld_month_money[0] - ld_sy_money;
                    //公积金计算
                    for (int i = 1; i <= ll_months_gjj; i++) {
                        ld_gjj_month_money[i - 1] = ld_gjj_money * ld_gjj_rate * Math.pow((1 + ld_gjj_rate), ll_months_gjj) / (Math.pow((1 + ld_gjj_rate), ll_months_gjj) - 1);
                        ld_gjj_month_bj[i - 1] = ld_gjj_money * ld_gjj_rate * Math.pow((1 + ld_gjj_rate), i - 1) / (Math.pow((1 + ld_gjj_rate), ll_months_gjj) - 1);
                        ld_gjj_month_lx[i - 1] = ld_gjj_money * ld_gjj_rate * (Math.pow((1 + ld_gjj_rate), ll_months_gjj) - Math.pow((1 + ld_gjj_rate), i - 1)) / (Math.pow((1 + ld_gjj_rate), ll_months_gjj) - 1);
                        ld_gjj_month_sybj[i - 1] = ld_gjj_month_sybj[i - 1] - ld_gjj_month_bj[i - 1];
                        ld_gjj_month_sybj[i] = ld_gjj_month_sybj[i - 1];

                    }
                    ld_gjj_all_lx_money = ll_months_gjj * ld_gjj_month_money[0] - ld_gjj_money;
                    //两项金额相加
                    final int ll_months;   //list表最大月份
                    double[] ld_month_money_all;
                    double[] ld_month_lx_all;
                    double[] ld_month_bj_all;
                    double[] ld_month_sybj_all;
                    if (ll_months_gjj > ll_months_sy) {
                        ll_months = ll_months_gjj;
                        ld_month_money_all = new double[ll_months];
                        ld_month_lx_all = new double[ll_months];
                        ld_month_bj_all = new double[ll_months];
                        ld_month_sybj_all = new double[ll_months];
                        for (int i = 0; i < ll_months; i++) {
                            try {
                                ld_month_money_all[i] = ld_month_money[i] + ld_gjj_month_money[i];
                                ld_month_lx_all[i] = ld_month_lx[i] + ld_gjj_month_lx[i];
                                ld_month_bj_all[i] = ld_month_bj[i] + ld_gjj_month_bj[i];
                                ld_month_sybj_all[i] = ld_month_sybj[i] + ld_gjj_month_sybj[i];
                            } catch (Exception e) {
                                ld_month_money_all[i] = ld_gjj_month_money[i];
                                ld_month_lx_all[i] = ld_gjj_month_lx[i];
                                ld_month_bj_all[i] = ld_gjj_month_bj[i];
                                ld_month_sybj_all[i] = ld_gjj_month_sybj[i];
                            }
                        }
                    } else {
                        ll_months = ll_months_sy;
                        ld_month_money_all = new double[ll_months];
                        ld_month_lx_all = new double[ll_months];
                        ld_month_bj_all = new double[ll_months];
                        ld_month_sybj_all = new double[ll_months];
                        for (int i = 0; i < ll_months; i++) {
                            try {
                                ld_month_money_all[i] = ld_month_money[i] + ld_gjj_month_money[i];
                                ld_month_lx_all[i] = ld_month_lx[i] + ld_gjj_month_lx[i];
                                ld_month_bj_all[i] = ld_month_bj[i] + ld_gjj_month_bj[i];
                                ld_month_sybj_all[i] = ld_month_sybj[i] + ld_gjj_month_sybj[i];
                            } catch (Exception e) {
                                ld_month_money_all[i] = ld_month_money[i];
                                ld_month_lx_all[i] = ld_month_lx[i];
                                ld_month_bj_all[i] = ld_month_bj[i];
                                ld_month_sybj_all[i] = ld_month_sybj[i];
                            }
                        }
                    }

                    editText_group_sylx_show.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money)));
                    editText_group_gjjlx_show.setText(String.valueOf(number_dec.getDecimal(ld_gjj_all_lx_money)));
                    editText_all_lx.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money + ld_gjj_all_lx_money)));
                    editText_all_lj.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money + ld_sy_money + ld_gjj_all_lx_money + ld_gjj_money)));
                    List_View_Binding.list_show(GroupActivity.this, listView, ll_months, ld_month_money_all, ld_month_lx_all, ld_month_bj_all, ld_month_sybj_all);

                }
                if (radioGroup_hk.getCheckedRadioButtonId() == radioButton_bj.getId()) {
//                    计算公式：
//                    计划月还款额=(贷款本金÷还款月数)+(贷款本金-累计已还本金)×月利率
//                    累计已还本金=已经归还贷款的月数×贷款本金/还款月数
                    // 每月应还本金=贷款本金÷还款月数
                    //每月应还利息=剩余本金×月利率=(贷款本金-已归还本金累计额)×月利率
                    //每月月供递减额=每月应还本金×月利率=贷款本金÷还款月数×月利率
                    //总利息=〔(总贷款额÷还款月数+总贷款额×月利率)+总贷款额÷还款月数×(1+月利率)〕÷2×还款月数-总贷款额
                    double[] ld_sum_have = new double[ll_months_sy + 1]; //计划月还款额
                    double[] ld_month_money = new double[ll_months_sy];  //累计已还本金
                    double[] ld_month_bj = new double[ll_months_sy]; // 每月应还本金
                    double[] ld_month_lx = new double[ll_months_sy]; // 每月应还利息
                    double[] ld_month_sybj = new double[ll_months_sy];   //每月剩余本金
                    double ld_month_money_dj;//每月月供递减额
                    double ld_all_lx_money;//总利息
                    ld_sy_rate = ld_sy_rate / 12;
                    ld_month_money_dj = ld_sy_money / ll_months_sy * ld_sy_rate;

                    double[] ld_gjj_sum_have = new double[ll_months_gjj + 1]; //计划月还款额
                    double[] ld_gjj_month_money = new double[ll_months_gjj];  //累计已还本金
                    double[] ld_gjj_month_bj = new double[ll_months_gjj]; // 每月应还本金
                    double[] ld_gjj_month_lx = new double[ll_months_gjj]; // 每月应还利息
                    double[] ld_gjj_month_sybj = new double[ll_months_gjj];   //每月剩余本金
                    double ld_gjj_month_money_dj;//每月月供递减额
                    double ld_gjj_all_lx_money;//总利息
                    ld_gjj_rate = ld_gjj_rate / 12;
                    ld_gjj_all_lx_money = ld_gjj_money / ll_months_gjj * ld_gjj_rate;
                    //商业计算
                    for (int i = 1; i <= ll_months_sy; i++) {
                        ld_month_money[i - 1] = (ld_sy_money / ll_months_sy) + (ld_sy_money - ld_sum_have[i - 1]) * ld_sy_rate;
                        ld_sum_have[i] = i * ld_sy_money / ll_months_sy;
                        ld_month_bj[i - 1] = ld_sy_money / ll_months_sy;
                        ld_month_lx[i - 1] = (ld_sy_money - ld_sum_have[i - 1]) * ld_sy_rate;
                        ld_month_sybj[i - 1] = ld_sy_money - ld_sum_have[i];

                    }
                    ld_all_lx_money = (ld_sy_money / ll_months_sy + ld_sy_money * ld_sy_rate + ld_sy_money / ll_months_sy * (1 + ld_sy_rate)) / 2 * ll_months_sy - ld_sy_money;
                    //公积金计算
                    for (int i = 1; i <= ll_months_gjj; i++) {
                        ld_gjj_month_money[i - 1] = (ld_gjj_money / ll_months_gjj) + (ld_gjj_money - ld_gjj_sum_have[i - 1]) * ld_gjj_rate;
                        ld_gjj_sum_have[i] = i * ld_gjj_money / ll_months_gjj;
                        ld_gjj_month_bj[i - 1] = ld_gjj_money / ll_months_gjj;
                        ld_gjj_month_lx[i - 1] = (ld_gjj_money - ld_gjj_sum_have[i - 1]) * ld_gjj_rate;
                        ld_gjj_month_sybj[i - 1] = ld_gjj_money - ld_gjj_sum_have[i];

                    }
                    ld_gjj_all_lx_money = (ld_gjj_money / ll_months_gjj + ld_gjj_money * ld_gjj_rate + ld_gjj_money / ll_months_gjj * (1 + ld_gjj_rate)) / 2 * ll_months_gjj - ld_gjj_money;
                    //两项金额相加


                    //两项金额相加
                    final int ll_months;   //list表最大月份
                    double[] ld_month_money_all;
                    double[] ld_month_lx_all;
                    double[] ld_month_bj_all;
                    double[] ld_month_sybj_all;
                    if (ll_months_gjj > ll_months_sy) {
                        ll_months = ll_months_gjj;
                        ld_month_money_all = new double[ll_months];
                        ld_month_lx_all = new double[ll_months];
                        ld_month_bj_all = new double[ll_months];
                        ld_month_sybj_all = new double[ll_months];
                        for (int i = 0; i < ll_months; i++) {
                            try {
                                ld_month_money_all[i] = ld_month_money[i] + ld_gjj_month_money[i];
                                ld_month_lx_all[i] = ld_month_lx[i] + ld_gjj_month_lx[i];
                                ld_month_bj_all[i] = ld_month_bj[i] + ld_gjj_month_bj[i];
                                ld_month_sybj_all[i] = ld_month_sybj[i] + ld_gjj_month_sybj[i];
                            } catch (Exception e) {
                                ld_month_money_all[i] = ld_gjj_month_money[i];
                                ld_month_lx_all[i] = ld_gjj_month_lx[i];
                                ld_month_bj_all[i] = ld_gjj_month_bj[i];
                                ld_month_sybj_all[i] = ld_gjj_month_sybj[i];
                            }
                        }
                    } else {
                        ll_months = ll_months_sy;
                        ld_month_money_all = new double[ll_months];
                        ld_month_lx_all = new double[ll_months];
                        ld_month_bj_all = new double[ll_months];
                        ld_month_sybj_all = new double[ll_months];
                        for (int i = 0; i < ll_months; i++) {
                            try {
                                ld_month_money_all[i] = ld_month_money[i] + ld_gjj_month_money[i];
                                ld_month_lx_all[i] = ld_month_lx[i] + ld_gjj_month_lx[i];
                                ld_month_bj_all[i] = ld_month_bj[i] + ld_gjj_month_bj[i];
                                ld_month_sybj_all[i] = ld_month_sybj[i] + ld_gjj_month_sybj[i];
                            } catch (Exception e) {
                                ld_month_money_all[i] = ld_month_money[i];
                                ld_month_lx_all[i] = ld_month_lx[i];
                                ld_month_bj_all[i] = ld_month_bj[i];
                                ld_month_sybj_all[i] = ld_month_sybj[i];
                            }
                        }
                    }

                    editText_group_sylx_show.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money)));
                    editText_group_gjjlx_show.setText(String.valueOf(number_dec.getDecimal(ld_gjj_all_lx_money)));
                    editText_all_lx.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money + ld_gjj_all_lx_money)));
                    editText_all_lj.setText(String.valueOf(number_dec.getDecimal(ld_all_lx_money + ld_sy_money + ld_gjj_all_lx_money + ld_gjj_money)));


                    List_View_Binding.list_show(GroupActivity.this, listView, ll_months, ld_month_money_all, ld_month_lx_all, ld_month_bj_all, ld_month_sybj_all);

                }

            }


        });


    }
}
