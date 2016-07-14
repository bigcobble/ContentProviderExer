package com.fei.contentproviderexer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fei.contentproviderexer.database.PersonDao;
import com.fei.contentproviderexer.database.PersonDbHelper;

public class PersonInfoListActivity extends AppCompatActivity {
    PersonDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_list);
        new PersonDao(this).insertPerson("lsx", "223");
    }
}
