package com.example.indexablelistview;

//import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;

import com.monopoly.example.demoindexablelistview.widget.IndexableListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;




public class MainActivity extends Activity {


	private IndexableListView listViewIndexable;
	private MyCustomAdapter awordListAdapter;
	ArrayList<MyDataClass> awordlist;
	ArrayList<MyDataClass> ViewSliderWordList;
	Context context;
	
	TestAdapter mDbHelper = new TestAdapter(this); 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listViewIndexable = (IndexableListView) findViewById(R.id.listViewIndexable);
		ViewSliderWordList = new ArrayList<MyDataClass>();
		context = this;

		 connect_db();
		 
		 ShowIndexAbleList();
        
        
    }

    
    
	
	
	private void ShowIndexAbleList()
	{
		awordlist = mDbHelper.GetDictionaryData();
		 
		 awordListAdapter = new MyCustomAdapter(awordlist, this);
			listViewIndexable.setAdapter(awordListAdapter);
			listViewIndexable.setFastScrollEnabled(true);
			
		    listViewIndexable.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub

					MyDataClass aword = awordListAdapter.getItem(position);
				    String	english = aword.getEnglish_word();
				    
				    //Toast.makeText(this, english, Toast.LENGTH_SHORT).show();
					
				}
			});
		
	}
	
	
    
    
	
	private void connect_db()
	{		
		 mDbHelper.createDatabase();  
	        
         mDbHelper.open();
	}
	
    
}
