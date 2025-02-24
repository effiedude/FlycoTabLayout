package com.townspriter.android.base.ui.tablayout.utils;

import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentChangeManager
{
    private final FragmentManager mFragmentManager;
    private final int mContainerViewId;
    /** Fragment切换数组 */
    private final ArrayList<Fragment> mFragments;
    /** 当前选中的Tab */
    private int mCurrentTab;
    
    public FragmentChangeManager(FragmentManager fm,int containerViewId,ArrayList<Fragment> fragments)
    {
        this.mFragmentManager=fm;
        this.mContainerViewId=containerViewId;
        this.mFragments=fragments;
        initFragments();
    }
    
    /** 初始化fragments */
    private void initFragments()
    {
        for(Fragment fragment:mFragments)
        {
            mFragmentManager.beginTransaction().add(mContainerViewId,fragment).hide(fragment).commit();
        }
        setFragments(0);
    }
    
    /** 界面切换控制 */
    public void setFragments(int index)
    {
        for(int i=0;i<mFragments.size();i++)
        {
            FragmentTransaction fragmentTransaction=mFragmentManager.beginTransaction();
            Fragment fragment=mFragments.get(i);
            if(i==index)
            {
                fragmentTransaction.show(fragment);
            }
            else
            {
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commit();
        }
        mCurrentTab=index;
    }
    
    public int getCurrentTab()
    {
        return mCurrentTab;
    }
    
    public Fragment getCurrentFragment()
    {
        return mFragments.get(mCurrentTab);
    }
}