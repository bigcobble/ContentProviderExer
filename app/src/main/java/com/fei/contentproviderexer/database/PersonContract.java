package com.fei.contentproviderexer.database;

import android.provider.BaseColumns;

/**
 * Created by lee on 2016/7/14.
 */
public class PersonContract{

    public static class Person  implements BaseColumns {
        public static final String TABLE_NAME = "person";


        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NUMBER = "number";
    }

}
