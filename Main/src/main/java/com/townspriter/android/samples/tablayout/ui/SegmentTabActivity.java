package com.townspriter.android.samples.tablayout.ui;

import java.util.ArrayList;
import com.townspriter.android.base.ui.tablayout.SegmentTabLayout;
import com.townspriter.android.base.ui.tablayout.listener.OnTabSelectListener;
import com.townspriter.android.base.ui.tablayout.widget.MsgView;
import com.townspriter.android.samples.tablayout.R;
import com.townspriter.android.samples.tablayout.utils.ViewFindUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SegmentTabActivity extends AppCompatActivity
{
    private final ArrayList<Fragment> mFragments=new ArrayList<>();
    private final ArrayList<Fragment> mFragmentsSecond=new ArrayList<>();
    private final String[] mTitles={"首页","消息"};
    private final String[] mTitlesSecond={"首页","消息","联系人"};
    private final String[] mTitlesThird={"首页","消息","联系人","更多"};
    private View mDecorView;
    private SegmentTabLayout mTabLayoutThird;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabxlayoutxactivityxsegment);
        for(String title:mTitlesThird)
        {
            mFragments.add(SimpleCardFragment.getInstance("SwitchViewPager["+title+"]"));
        }
        for(String title:mTitlesSecond)
        {
            mFragmentsSecond.add(SimpleCardFragment.getInstance("SwitchFragment["+title+"]"));
        }
        mDecorView=getWindow().getDecorView();
        SegmentTabLayout tabLayoutFirst=ViewFindUtils.find(mDecorView,R.id.tabLayoutFirst);
        SegmentTabLayout tabLayoutSecond=ViewFindUtils.find(mDecorView,R.id.tabLayoutSecond);
        mTabLayoutThird=ViewFindUtils.find(mDecorView,R.id.tabLayoutThird);
        SegmentTabLayout tabLayoutForth=ViewFindUtils.find(mDecorView,R.id.tabLayoutForth);
        SegmentTabLayout tabLayoutFifth=ViewFindUtils.find(mDecorView,R.id.tabLayoutFifth);
        tabLayoutFirst.setTabData(mTitles);
        tabLayoutSecond.setTabData(mTitlesSecond);
        initTabLayoutThird();
        tabLayoutForth.setTabData(mTitlesSecond,this,R.id.fragmentContainer,mFragmentsSecond);
        tabLayoutFifth.setTabData(mTitlesThird);
        // 显示未读红点
        tabLayoutFirst.showDot(2);
        tabLayoutSecond.showDot(2);
        mTabLayoutThird.showDot(1);
        tabLayoutForth.showDot(1);
        // 设置未读消息红点
        mTabLayoutThird.showDot(2);
        MsgView msgView=mTabLayoutThird.getMsgView(2);
        if(msgView!=null)
        {
            msgView.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }
    
    private void initTabLayoutThird()
    {
        final ViewPager viewPager=ViewFindUtils.find(mDecorView,R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayoutThird.setTabData(mTitlesThird);
        mTabLayoutThird.setOnTabSelectListener(new OnTabSelectListener()
        {
            @Override
            public void onTabSelect(int position)
            {
                viewPager.setCurrentItem(position);
            }
            
            @Override
            public void onTabReselect(int position)
            {}
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels)
            {}
            
            @Override
            public void onPageSelected(int position)
            {
                mTabLayoutThird.setCurrentTab(position);
            }
            
            @Override
            public void onPageScrollStateChanged(int state)
            {}
        });
        viewPager.setCurrentItem(1);
    }
    
    private class MyPagerAdapter extends FragmentPagerAdapter
    {
        public MyPagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }
        
        @Override
        public int getCount()
        {
            return mFragments.size();
        }
        
        @Override
        public CharSequence getPageTitle(int position)
        {
            return mTitlesThird[position];
        }
        
        @Override
        public Fragment getItem(int position)
        {
            return mFragments.get(position);
        }
    }
}
