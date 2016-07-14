package com.fei.contentproviderexer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fei.contentproviderexer.database.PersonContract.Person;
import com.fei.contentproviderexer.database.PersonDao;

import java.util.ArrayList;
import java.util.List;

public class PersonInfoListActivity extends AppCompatActivity {
    private List<String> mPersonInfoList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_list);
        mListView = (ListView) findViewById(R.id.activity_person_info_list_view);
        new PersonDao(this).bulkInsertPerson();
        setupAdapter();

    }

    private void setupAdapter() {
        Cursor c = getContentResolver().query(Person.CONTENT_URI, null, null, null,
                null);
        if (c == null) {
            return;
        }
        while (c.moveToNext()) {
            String info = c.getString(c.getColumnIndex(Person.COLUMN_NAME)) + " : " +
                    c.getString(c.getColumnIndex(Person.COLUMN_NUMBER));
            mPersonInfoList.add(info);
        }
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                mPersonInfoList);
        mListView.setAdapter(mAdapter);
    }
}
