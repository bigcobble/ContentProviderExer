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

    public int bulkInsertPerson() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int returnCount = 0;
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            for (int i = 0; i < 20; i++) {
                cv.put(Person.COLUMN_NAME, "lsx" + i);
                cv.put(Person.COLUMN_NUMBER, "123" + i);
                long id = db.insert(Person.TABLE_NAME, null, cv);
                if (id != -1) {
                    returnCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return returnCount;
    }
}
