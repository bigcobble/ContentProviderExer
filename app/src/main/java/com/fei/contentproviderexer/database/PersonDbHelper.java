package com.fei.contentproviderexer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fei.contentproviderexer.database.PersonContract.PersonTable;


/**
 * Created by lee on 2016/7/14.
 */
public class PersonDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "PersonDbHelper";
    public PersonDbHelper(Context context) {
        super(context, "person", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ............................");
        db.execSQL("create table " + PersonTable.TABLE_NAME + "(" +
                PersonTable._ID + " integer primary key autoincrement," +
                PersonTable.COLUMN_NAME + " varchar(40)," +
                PersonTable.COLUMN_NUMBER + " varchar(40)" +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
