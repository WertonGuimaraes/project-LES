package com.ufcg.les;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseStorage {
	
	public static final String TABLE_TEMPO_INVESTIDO = "TEMPO_INVESTIDO";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_TEMPO_INVESTIDO= "TEMPO_INVESTIDO";
	public static final String COLUMN_DIRECTORY = "DIRECTORY";
	public static final String COLUMN_PRIORITY = "PRIORITY";
	

	private static final String TEMPO_INVESTIDO_CREATE_TABLE = "CREATE TABLE "
			+ TABLE_TEMPO_INVESTIDO + "  ("
			+ COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_NAME + " TEXT NOT NULL,"
			+ COLUMN_TEMPO_INVESTIDO + " REAL NOT NULL,"
			+ COLUMN_DIRECTORY + " TEXT,"
			+ COLUMN_PRIORITY + " INTEGER NOT NULL);";

	private static final String TAG = "DataBaseStorage";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private static final String DB_NAME = "DBP";
	private static final int DATABASE_VERSION = 1;
	private final Context mContext;
	
	
	public static class DatabaseHelper extends SQLiteOpenHelper {

		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
		}

		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(TEMPO_INVESTIDO_CREATE_TABLE);

			Log.w("DataBaseStorage", "DB created sucefully!");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			Log.w(TAG, "Updating database from version " + oldVersion
					+ " to " + newVersion
					+ ", all data will be lost!");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMPO_INVESTIDO);
			onCreate(db);
		}	    	
	}
	
	public DataBaseStorage(Context context) {
		this.mContext = context;
		open();    
	}

	private DataBaseStorage open() throws SQLException {
		mDbHelper = new DatabaseHelper(mContext);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDb.close();
	}
	

}
