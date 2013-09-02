package com.appbase.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class DBManager {

	private AppSqliteHelper sqLiteOpenHelper; // SQLITE Helper instance

	private SQLiteDatabase appSqLiteDatabase; // Database instance

	/**
	 * Constructor initializes the Sqlitehelper
	 * 
	 * @param mContext
	 */
	public DBManager(Context mContext) {
		// TODO Auto-generated constructor stub
		sqLiteOpenHelper = new AppSqliteHelper(mContext);

	}

	/**
	 * Methods opens the database
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		appSqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
		Uri mUril =	 Uri.parse(appSqLiteDatabase.getPath());
		Log.e("URI ",""+mUril.toString());
	}

	/**
	 * Method closes the database.
	 */
	public void close() {
		sqLiteOpenHelper.close();
	}

	/**
	 * Dummy insertion of values to comments table
	 */

	public void insertComments() {
		//for(int i=0;i<100;i++){
		ContentValues values = new ContentValues();
		values.put(AppSqliteHelper.COLUMN_COMMENT, "aabuback: Comment ID ");
		long insertId = appSqLiteDatabase.insert(
				sqLiteOpenHelper.TABLE_COMMENTS, null, values);
		//}
	}

	/**
	 * Fetching all the comments from Comment table.
	 */

	public Cursor fetchComments() {
		
		String[] allColumns = { sqLiteOpenHelper.COLUMN_ID,
				sqLiteOpenHelper.COLUMN_COMMENT };
		Cursor cursor = appSqLiteDatabase.query(AppSqliteHelper.TABLE_COMMENTS,
		        allColumns, null, null,
		        null, null, null);
		Log.e("Curson Size ","== "+cursor.getCount());
		return cursor;
	}
}
