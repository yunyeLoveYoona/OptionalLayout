package com.example.administrator.optionallayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.optionallayout.R;
import com.example.administrator.optionallayout.view.OptionalLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 15-6-9.
 */
public class OptionalListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private String [] strings;
    private boolean isOptional =false;
    private HashMap<String,String> clickItemMap;
    public OptionalListAdapter(Context context, String[] strings){
        layoutInflater = LayoutInflater.from(context);
        this.strings = strings;
        clickItemMap = new HashMap<String,String>();
    }
    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holdView = null;
        if(convertView ==null){
            convertView = layoutInflater.inflate(R.layout.list_item,null);
        }
        holdView=new ViewHolder();
        holdView.text = ((TextView)convertView.findViewById(R.id.text));
        holdView.optionalLayout = ((OptionalLayout)convertView.findViewById(R.id.optionalLayout));
        holdView.text.setText(strings[position]);
        holdView.optionalLayout.visibilityOrGoneCheckButton(isOptional);
        if(clickItemMap.get(position+"")==null){
            holdView.optionalLayout.click(false);
        }else{
            if(clickItemMap.get(position+"").equals("checked")){
                holdView.optionalLayout.click(true);
            }else{
                holdView.optionalLayout.click(false);
            }
        }

        return convertView;
    }
    public void optional(){
        clickItemMap.clear();
        isOptional =!isOptional;
    }
    public void onItemClick(String position){
        if(clickItemMap.get(position)==null){
            clickItemMap.put(position,"checked");
        }else{
            if(clickItemMap.get(position).equals("checked")){
                clickItemMap.put(position,"notChecked");
            }else{
                clickItemMap.put(position,"checked");
            }
        }
    }
   static class ViewHolder{
        TextView text;
        OptionalLayout optionalLayout;
    }
}
