package com.example.indexablelistview;

import java.io.IOException;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestAdapter
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private static SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

  
    
    // this is for show_category
    public TestAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }
    public TestAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }
  
  
  
    public TestAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }  
  
    public void close()
    {
        mDbHelper.close();
    }

   
     
    

    // for indexableListview 
     public static ArrayList<MyDataClass> GetDictionaryData() {
 		
     	ArrayList<MyDataClass> aWordArrayList = new ArrayList<MyDataClass>();

 		try {
 			String[] selections = null;
 			String qry = "Select * from DicData";
 			Cursor cursor = mDb.rawQuery(qry, selections);
 			
 			while (cursor.moveToNext()) {
 				
 				MyDataClass aWord = new MyDataClass();				
 				
 				//aWord.setWord_id((Integer.parseInt(cursor.getString(0))));
 				aWord.setEnglish_word((cursor.getString(1)));
 				aWord.setNative_word((cursor.getString(2)));
 				//aWord.setTranslitaration(cursor.getString(3));
 				//aWord.setMp3_id(cursor.getString(4));
 				
 				aWordArrayList.add(aWord);
 				
 				Log.d("test", ""+aWordArrayList.get(0));

 			}
 			cursor.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}

 		return aWordArrayList;
 	}
     
   
      
    
   
   
     public void AddTestData(String dataToadd)
     {
         try
         {
         
         //String sql ="SELECT * FROM Table1 LIMIT 5";//"SELECT  * FROM " + TABLE_REGISTRATION +" WHERE "+ KEY_NAME +" LIKE '"+ s +"%'" ;
         
             String sql ="insert into myTable(mycolumn1,mycolumn2) values('"+dataToadd+"','"+dataToadd+"')";
                 mDb.execSQL(sql);            
           
         }
         catch (SQLException mSQLException)
         {
             Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
   
   
     public void DeleteTestData(String dataToDelete)
     {
         try
         {
         
         //String sql ="SELECT * FROM Table1 LIMIT 5";//"SELECT  * FROM " + TABLE_REGISTRATION +" WHERE "+ KEY_NAME +" LIKE '"+ s +"%'" ;
         
             String sql ="delete from myTable where mycolumn1='"+dataToDelete+"'";
                 mDb.execSQL(sql);
                         
         }
         catch (SQLException mSQLException)
         {
             Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
    
   
}//end
