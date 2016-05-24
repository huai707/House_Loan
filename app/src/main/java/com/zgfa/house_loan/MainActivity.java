package com.zgfa.house_loan;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TabHost;


public class MainActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mainTab;
    private TabHost mTabHost;

    private Intent mHomeIntent;
    private Intent mNewsIntent;
    private Intent mInfoIntent;
    private Intent mSearchIntent;
    private Intent mMoreIntent;

    private final static String TAB_TAG_HOME = "tab_tag_home";
    private final static String TAB_TAG_NEWS = "tab_tag_news";
    private final static String TAB_TAG_INFO = "tab_tag_info";
    private final static String TAB_TAG_SEARCH = "tab_tag_search";
    private final static String TAB_TAG_MORE = "tab_tag_more";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTab = (RadioGroup) findViewById(R.id.main_tab);
        mainTab.setOnCheckedChangeListener(this);
        prepareIntent();
        setupIntent();
    }

    private void prepareIntent() {
        mHomeIntent = new Intent(this, BusinessActivity.class);
        mNewsIntent = new Intent(this, PersonActivity.class);
        mInfoIntent = new Intent(this, GroupActivity.class);
        mSearchIntent = new Intent(this, AdviseActivity.class);
        mMoreIntent = new Intent(this, MoreActivity.class);
    }


    private void setupIntent() {
        this.mTabHost = getTabHost();
        TabHost localTabHost = this.mTabHost;
        localTabHost.addTab(buildTabSpec(TAB_TAG_HOME, R.string.main_home, R.drawable.icon_1_n, mHomeIntent));
        localTabHost.addTab(buildTabSpec(TAB_TAG_NEWS, R.string.main_news, R.drawable.icon_3_n, mNewsIntent));
        localTabHost.addTab(buildTabSpec(TAB_TAG_INFO, R.string.main_my_info, R.drawable.icon_4_n, mInfoIntent));
        localTabHost.addTab(buildTabSpec(TAB_TAG_SEARCH, R.string.menu_search, R.drawable.icon_2_n, mSearchIntent));
        localTabHost.addTab(buildTabSpec(TAB_TAG_MORE, R.string.more, R.drawable.icon_5_n, mMoreIntent));
    }

    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon, final Intent content) {
        return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
                getResources().getDrawable(resIcon)).setContent(content);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_button0:
                this.mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
                break;
            case R.id.radio_button1:
                this.mTabHost.setCurrentTabByTag(TAB_TAG_NEWS);
                break;
            case R.id.radio_button2:
                this.mTabHost.setCurrentTabByTag(TAB_TAG_INFO);
                break;
            case R.id.radio_button3:
                this.mTabHost.setCurrentTabByTag(TAB_TAG_SEARCH);
                break;
            case R.id.radio_button4:
                this.mTabHost.setCurrentTabByTag(TAB_TAG_MORE);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


}
