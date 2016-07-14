package com.fei.contentproviderexer.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.fei.contentproviderexer.database.PersonContract.Person;

/**
 * Created by lee on 2016/7/14.
 */
public class PersonContentProvider extends ContentProvider {
    private static final int PERSON = 0;
    private static final int PERSON_ONE = 1;
    private PersonDbHelper mDbHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        mDbHelper = new PersonDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case PERSON:
                cursor = db.query(Person.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case PERSON_ONE:
                long id = ContentUris.parseId(uri);
                cursor = db.query(Person.TABLE_NAME, projection, "_id=?", new String[]{id + ""},
                        null, null, null);
            default:
                throw new UnsupportedOperationException("unknown uri: " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = PersonContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, PersonContract.PATH_PERSON, PERSON);
        matcher.addURI(authority, PersonContract.PATH_PERSON + "/#", PERSON_ONE);

        return matcher;
    }
}
