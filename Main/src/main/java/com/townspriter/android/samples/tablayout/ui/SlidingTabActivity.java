package com.townspriter.android.samples.tablayout.ui;

import java.util.ArrayList;
import com.townspriter.android.base.ui.tablayout.SlidingTabLayout;
import com.townspriter.android.base.ui.tablayout.listener.OnTabSelectListener;
import com.townspriter.android.base.ui.tablayout.widget.MsgView;
import com.townspriter.android.samples.tablayout.R;
import com.townspriter.android.samples.tablayout.utils.ViewFindUtils;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SlidingTabActivity extends AppCompatActivity implements OnTabSelectListener
{
    private final String[] mTitles={"热门","iOS","Android","前端","后端","设计","工具资源"};
    private final Context mContext=this;
    private final ArrayList<Fragment> mFragments=new ArrayList<>();
    private MyPagerAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabxlayoutxactivityxsliding);
        for(String title:mTitles)
        {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }
        View decorView=getWindow().getDecorView();
        ViewPager vp=ViewFindUtils.find(decorView,R.id.vp);
        mAdapter=new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        /** 默认 */
        SlidingTabLayout tabLayout_1=ViewFindUtils.find(decorView,R.id.tabLayoutFirst);
        /** 自定义部分属性 */
        SlidingTabLayout tabLayout_2=ViewFindUtils.find(decorView,R.id.tabLayoutSecond);
        /** 字体加粗,大写 */
        SlidingTabLayout tabLayout_3=ViewFindUtils.find(decorView,R.id.tabLayoutThird);
        /** tab固定宽度 */
        SlidingTabLayout tabLayout_4=ViewFindUtils.find(decorView,R.id.tabLayoutForth);
        /** indicator固定宽度 */
        SlidingTabLayout tabLayout_5=ViewFindUtils.find(decorView,R.id.tabLayoutFifth);
        /** indicator圆 */
        SlidingTabLayout tabLayout_6=ViewFindUtils.find(decorView,R.id.tl_6);
        /** indicator矩形圆角 */
        final SlidingTabLayout tabLayout_7=ViewFindUtils.find(decorView,R.id.tl_7);
        /** indicator三角形 */
        SlidingTabLayout tabLayout_8=ViewFindUtils.find(decorView,R.id.tl_8);
        /** indicator圆角色块 */
        SlidingTabLayout tabLayout_9=ViewFindUtils.find(decorView,R.id.tl_9);
        /** indicator圆角色块 */
        SlidingTabLayout tabLayout_10=ViewFindUtils.find(decorView,R.id.tl_10);
        tabLayout_1.setViewPager(vp);
        tabLayout_2.setViewPager(vp);
        tabLayout_2.setOnTabSelectListener(this);
        tabLayout_3.setViewPager(vp);
        tabLayout_4.setViewPager(vp);
        tabLayout_5.setViewPager(vp);
        tabLayout_6.setViewPager(vp);
        tabLayout_7.setViewPager(vp,mTitles);
        tabLayout_8.setViewPager(vp,mTitles,this,mFragments);
        tabLayout_9.setViewPager(vp);
        tabLayout_10.setViewPager(vp);
        vp.setCurrentItem(4);
        tabLayout_1.showDot(4);
        tabLayout_3.showDot(4);
        tabLayout_2.showDot(4);
        tabLayout_2.showMsg(3,5);
        tabLayout_2.setMsgMargin(3,0,10);
        MsgView rtv_2_3=tabLayout_2.getMsgView(3);
        if(rtv_2_3!=null)
        {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
        tabLayout_2.showMsg(5,5);
        tabLayout_2.setMsgMargin(5,0,10);
        // tabLayout_7.setOnTabSelectListener(new OnTabSelectListener() {
        // @Override
        // public void onTabSelect(int position) {
        // Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
        // }
        //
        // @Override
        // public void onTabReselect(int position) {
        // mFragments.add(SimpleCardFragment.getInstance("后端"));
        // mAdapter.notifyDataSetChanged();
        // tabLayout_7.addNewTab("后端");
        // }
        // });
    }
    
    @Override
    public void onTabSelect(int position)
    {
        Toast.makeText(mContext,"onTabSelect-position:"+position,Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onTabReselect(int position)
    {
        Toast.makeText(mContext,"onTabReselect-position:"+position,Toast.LENGTH_SHORT).show();
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
            return mTitles[position];
        }
        
        @Override
        public Fragment getItem(int position)
        {
            return mFragments.get(position);
        }
    }
}
