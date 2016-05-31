package jzl.remotecraft.sensor.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
	private static int VERSION = 26;
	private static DataBaseHelper dataBaseHelper= null;
	private static SQLiteDatabase sqliteDataBase = null;
	private Context mContext;
	private static String DATA_BASE_NAME = "default.db";
	
	
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
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
