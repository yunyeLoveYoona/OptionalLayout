package com.example.administrator.optionallayout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.optionallayout.adapter.OptionalListAdapter;
import com.example.administrator.optionallayout.view.OptionalListView;


public class MainActivity extends ActionBarActivity {
    private OptionalListAdapter adapter;
    private OptionalListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (OptionalListView) findViewById(R.id.listview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);
        adapter = new OptionalListAdapter(this, new String[]{"item1", "item2","item3"
        ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10","item1", "item2","item3"
                ,"item4","item5","item6","item7","item8","item9","item10"});
        listView.setAdapter(adapter);

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
        if (id == R.id.action_check) {
            listView.optional();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
