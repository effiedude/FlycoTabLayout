package com.townspriter.android.samples.tablayout.ui;

import com.townspriter.android.samples.tablayout.adapter.SimpleHomeAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class SimpleHomeActivity extends AppCompatActivity
{
    private final String[] mItems={"SlidingTabLayout","CommonTabLayout","SegmentTabLayout"};
    private final Class<?>[] mClasses={SlidingTabActivity.class,CommonTabActivity.class,SegmentTabActivity.class};
    private final Context mContext=this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ListView lv=new ListView(mContext);
        lv.setCacheColorHint(Color.TRANSPARENT);
        lv.setFadingEdgeLength(0);
        lv.setAdapter(new SimpleHomeAdapter(mContext,mItems));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id)
            {
                Intent intent=new Intent(mContext,mClasses[position]);
                startActivity(intent);
            }
        });
        setContentView(lv);
    }
}
