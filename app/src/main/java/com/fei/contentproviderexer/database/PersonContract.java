package com.fei.contentproviderexer.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by lee on 2016/7/14.
 */
public class PersonContract {
    public static final String CONTENT_AUTHORITY = "com.fei.contentproviderexer";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PERSON = "person";

    public static class PersonTable implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PERSON)
                .build();
        public static final String TABLE_NAME = "person";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NUMBER = "number";
    }

}
