package com.fei.contentproviderexer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fei.contentproviderexer.database.PersonContract.Person;

/**
 * Created by lee on 2016/7/14.
 */
public class PersonDao {
    private PersonDbHelper mDbHelper;
    public PersonDao(Context context) {
        mDbHelper = new PersonDbHelper(context);
    }
    public long insertPerson(String name, String number) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Person.COLUMN_NAME, name);
        cv.put(Person.COLUMN_NUMBER, number);
        long id = db.insert(Person.TABLE_NAME, null, cv);
        db.close();

        return id;
    }
}
