package com.townspriter.android.samples.tablayout.ui;

import com.townspriter.android.samples.tablayout.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SimpleCardFragment extends Fragment
{
    private String mTitle;
    
    public static SimpleCardFragment getInstance(String title)
    {
        SimpleCardFragment simpleCardFragment=new SimpleCardFragment();
        simpleCardFragment.mTitle=title;
        return simpleCardFragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.tabxlayoutxfragmentxsimplexcard,null);
        TextView cardTitle=view.findViewById(R.id.cardTitle);
        cardTitle.setText(mTitle);
        return view;
    }
}