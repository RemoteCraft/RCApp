package jzl.remotecraft.sensor.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper{
	private static int VERSION = 2;
	private static DataBaseHelper dataBaseHelper= null;
	private static SQLiteDatabase sqliteDataBase = null;
	private Context mContext;
	private static String DATA_BASE_NAME = "default.db";
	public final static int INSERT = 0x01;
	public final static int UPDATE = 0x02;
	
	
	private DataBaseHelper(Context context) {
		super(context, DATA_BASE_NAME, null, VERSION);
		mContext=context;
	}
	
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	
	public static synchronized SQLiteDatabase getDBInstance(
			final Context context) {
		if (sqliteDataBase == null) {
			dataBaseHelper = new DataBaseHelper(context);
			sqliteDataBase = dataBaseHelper.getWritableDatabase();
		}
		return sqliteDataBase;
	}

	public static ArrayList<String> query(String tlb,String[] columns,String where){
		Cursor cursor = null;
		try{
			ArrayList<String> data = new ArrayList<String>();
			cursor = sqliteDataBase.query(tlb,columns,where,null,null,null,null);
			boolean have = cursor.moveToFirst();
			while(have) {
				String row = "";
				for(int i =0; i < columns.length; i ++) {
					row += cursor.getString(cursor.getColumnIndex(columns[i]));
					row += ",";
				}
				row = row.substring(0,row.length()-1);
				data.add(row);
				have = cursor.moveToNext();
			}
			return data;
		}catch(Exception e) {
			return null;
		}
	}

	public static synchronized boolean execute(String sql){
		try{
			sqliteDataBase.execSQL(sql);
			return true;
		}catch(Exception e) {
			return false;
		}
	}


	public static synchronized boolean delete() {
		return true;
	}

	/***
	 * Insert or update records.
	 * @param batch Wither or not insert several records at one time.
	 * @param data Records to be inserted or updated into the corresponding columns.
	 *              Pay attention to the format of data.Each item in data is a record to be inserted or updated.And it is a string value that separated by comma.
	 *              Each element in the string value is corresponding to a column.
	 * @param column Columns to be inserted or updated.
	 * @param tlb Table name.
	 * @param where Where clause.
     * @param IOU Insert or update operation.
     */
	public static synchronized void insertOrUpdate(boolean batch, ArrayList<String> data, String column, String tlb, String where, int IOU) {
		try {
			if(sqliteDataBase == null) {
				throw new Exception("Please initialize this instance!");
			}
			String[] c = column.split(",");
			if(batch)
				sqliteDataBase.beginTransaction();
			for(String d:data) {
				String[] v = d.split(",");
				if (c.length != v.length) {
					throw new Exception("Size of row is not equal to number of columns!");
				}
				ContentValues values = new ContentValues();
				for(int i =0; i < c.length; i ++) {
					values.put(c[i],v[i]);
				}
				if(IOU == INSERT)
					sqliteDataBase.insert(tlb,null,values);
				if(IOU == UPDATE)
					sqliteDataBase.update(tlb,values,where,null);
				values = null;
			}
			if(batch)
				sqliteDataBase.setTransactionSuccessful();

		} catch(Exception e) {

		} finally {
			if(batch) {
				sqliteDataBase.endTransaction();
			}
		}
	}

	public static synchronized void update() {

	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
