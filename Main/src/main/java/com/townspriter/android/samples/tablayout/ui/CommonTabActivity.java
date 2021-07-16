package com.townspriter.android.samples.tablayout.ui;

import java.util.ArrayList;
import java.util.Random;
import com.townspriter.android.base.ui.tablayout.CommonTabLayout;
import com.townspriter.android.base.ui.tablayout.listener.CustomTabEntity;
import com.townspriter.android.base.ui.tablayout.listener.OnTabSelectListener;
import com.townspriter.android.base.ui.tablayout.utils.UnreadMsgUtils;
import com.townspriter.android.base.ui.tablayout.widget.MsgView;
import com.townspriter.android.samples.tablayout.R;
import com.townspriter.android.samples.tablayout.entity.TabEntity;
import com.townspriter.android.samples.tablayout.utils.ViewFindUtils;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class CommonTabActivity extends AppCompatActivity
{
    private final Context mContext=this;
    private final ArrayList<Fragment> mFragments=new ArrayList<>();
    private final ArrayList<Fragment> mFragments2=new ArrayList<>();
    private final String[] mTitles={"首页","消息","联系人","更多"};
    private final int[] mIconUnselectIds={R.mipmap.tab_home_unselect,R.mipmap.tab_speech_unselect,R.mipmap.tab_contact_unselect,R.mipmap.tab_more_unselect};
    private final int[] mIconSelectIds={R.mipmap.tab_home_select,R.mipmap.tab_speech_select,R.mipmap.tab_contact_select,R.mipmap.tab_more_select};
    private final ArrayList<CustomTabEntity> mTabEntities=new ArrayList<>();
    Random mRandom=new Random();
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_1;
    private CommonTabLayout mTabLayout_2;
    private CommonTabLayout mTabLayout_3;
    private CommonTabLayout mTabLayout_4;
    private CommonTabLayout mTabLayout_5;
    private CommonTabLayout mTabLayout_6;
    private CommonTabLayout mTabLayout_7;
    private CommonTabLayout mTabLayout_8;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabxlayoutxactivityxcommon);
        for(String title:mTitles)
        {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager "+title));
            mFragments2.add(SimpleCardFragment.getInstance("Switch Fragment "+title));
        }
        for(int i=0;i<mTitles.length;i++)
        {
            mTabEntities.add(new TabEntity(mTitles[i],mIconSelectIds[i],mIconUnselectIds[i]));
        }
        mDecorView=getWindow().getDecorView();
        mViewPager=ViewFindUtils.find(mDecorView,R.id.viewPager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        /** with nothing */
        mTabLayout_1=ViewFindUtils.find(mDecorView,R.id.tabLayoutFirst);
        /** with ViewPager */
        mTabLayout_2=ViewFindUtils.find(mDecorView,R.id.tabLayoutSecond);
        /** with Fragments */
        mTabLayout_3=ViewFindUtils.find(mDecorView,R.id.tabLayoutThird);
        /** indicator固定宽度 */
        mTabLayout_4=ViewFindUtils.find(mDecorView,R.id.tabLayoutForth);
        /** indicator固定宽度 */
        mTabLayout_5=ViewFindUtils.find(mDecorView,R.id.tabLayoutFifth);
        /** indicator矩形圆角 */
        mTabLayout_6=ViewFindUtils.find(mDecorView,R.id.tl_6);
        /** indicator三角形 */
        mTabLayout_7=ViewFindUtils.find(mDecorView,R.id.tl_7);
        /** indicator圆角色块 */
        mTabLayout_8=ViewFindUtils.find(mDecorView,R.id.tl_8);
        mTabLayout_1.setTabData(mTabEntities);
        tl_2();
        mTabLayout_3.setTabData(mTabEntities,this,R.id.fragmentContainer,mFragments2);
        mTabLayout_4.setTabData(mTabEntities);
        mTabLayout_5.setTabData(mTabEntities);
        mTabLayout_6.setTabData(mTabEntities);
        mTabLayout_7.setTabData(mTabEntities);
        mTabLayout_8.setTabData(mTabEntities);
        mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener()
        {
            @Override
            public void onTabSelect(int position)
            {
                mTabLayout_1.setCurrentTab(position);
                mTabLayout_2.setCurrentTab(position);
                mTabLayout_4.setCurrentTab(position);
                mTabLayout_5.setCurrentTab(position);
                mTabLayout_6.setCurrentTab(position);
                mTabLayout_7.setCurrentTab(position);
                mTabLayout_8.setCurrentTab(position);
            }
            
            @Override
            public void onTabReselect(int position)
            {}
        });
        mTabLayout_8.setCurrentTab(2);
        mTabLayout_3.setCurrentTab(1);
        // 显示未读红点
        mTabLayout_1.showDot(2);
        mTabLayout_3.showDot(1);
        mTabLayout_4.showDot(1);
        // 两位数
        mTabLayout_2.showMsg(0,55);
        mTabLayout_2.setMsgMargin(0,-5,5);
        // 三位数
        mTabLayout_2.showMsg(1,100);
        mTabLayout_2.setMsgMargin(1,-5,5);
        // 设置未读消息红点
        mTabLayout_2.showDot(2);
        MsgView rtv_2_2=mTabLayout_2.getMsgView(2);
        if(rtv_2_2!=null)
        {
            UnreadMsgUtils.setSize(rtv_2_2,dp2px(7.5f));
        }
        // 设置未读消息背景
        mTabLayout_2.showMsg(3,5);
        mTabLayout_2.setMsgMargin(3,0,5);
        MsgView rtv_2_3=mTabLayout_2.getMsgView(3);
        if(rtv_2_3!=null)
        {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }
    
    private void tl_2()
    {
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener()
        {
            @Override
            public void onTabSelect(int position)
            {
                mViewPager.setCurrentItem(position);
            }
            
            @Override
            public void onTabReselect(int position)
            {
                if(position==0)
                {
                    mTabLayout_2.showMsg(0,mRandom.nextInt(100)+1);
                    // UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels)
            {}
            
            @Override
            public void onPageSelected(int position)
            {
                mTabLayout_2.setCurrentTab(position);
            }
            
            @Override
            public void onPageScrollStateChanged(int state)
            {}
        });
        mViewPager.setCurrentItem(1);
    }
    
    protected int dp2px(float dp)
    {
        final float scale=mContext.getResources().getDisplayMetrics().density;
        return (int)(dp*scale+0.5f);
    }
    
    private class MyPagerAdapter extends FragmentPagerAdapter
    {
        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        
        @Override
        public int getCount()
        {
            return mFragments.size();
        }
        
        @Override
        public CharSequence getPageTitle(int position)
        {
            return mTitles[position];
        }
        
        @Override
        public Fragment getItem(int position)
        {
            return mFragments.get(position);
        }
    }
}
