package com.example.administrator.optionallayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.administrator.optionallayout.adapter.OptionalListAdapter;

/**
 * Created by Administrator on 15-6-9.
 */
public class OptionalListView extends ListView {
    public OptionalListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((OptionalListAdapter) getAdapter()).onItemClick(position + "");
                getItemView(position,view);
            }
        });
    }
   private void getItemView(int position, View view){
       getAdapter().getView(position, view,this);
   }
    public void setAdapter(OptionalListAdapter adapter) {
        super.setAdapter(adapter);
    }
    public void optional(){
        ((OptionalListAdapter)getAdapter()).optional();
        int start = getFirstVisiblePosition();
        for (int i = start, j = getLastVisiblePosition(); i <= j; i++){
            View view = getChildAt(i - start);
            getAdapter().getView(i, view, this);
        }
    }
}
