package com.fei.contentproviderexer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fei.contentproviderexer.bean.Person;
import com.fei.contentproviderexer.database.PersonContract;
import com.fei.contentproviderexer.database.PersonContract.PersonTable;
import com.fei.contentproviderexer.database.PersonDao;

import java.util.ArrayList;
import java.util.List;

public class PersonInfoListActivity extends AppCompatActivity {
    private static final String TAG = "PersonInfoListActivity";
    private List<Person> mPersonInfoList = new ArrayList<>();
    private PersonAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_list);
        mListView = (ListView) findViewById(R.id.activity_person_info_list_view);
        //new PersonDao(this).bulkInsertPerson();
        setupAdapter();
    }

    private void setupAdapter() {
        Cursor c = getContentResolver().query(PersonContract.PersonTable.CONTENT_URI, null, null, null,
                null);
        if (c == null) {
            return;
        }
        if (c.getCount() == 0) {
            mPersonInfoList = new ArrayList<>();
        } else {
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex(PersonTable.COLUMN_NAME));
                String number = c.getString(c.getColumnIndex(PersonContract.PersonTable.COLUMN_NUMBER));
                Person person = new Person(name, number);
                mPersonInfoList.add(person);
            }
        }

        mAdapter = new PersonAdapter(mPersonInfoList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_person_infor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_person_info_bulk_add:
                new PersonDao(this).bulkInsertPerson();
                setupAdapter();
                break;
            case R.id.activity_person_info_delete_all:
                getContentResolver().delete(PersonTable.CONTENT_URI, null, null);
                setupAdapter();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private class PersonAdapter extends ArrayAdapter<Person> {
        public PersonAdapter(List<Person> persons) {
            super(PersonInfoListActivity.this, R.layout.list_item_person, persons);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(PersonInfoListActivity.this).inflate(
                        R.layout.list_item_person, parent, false);
            }

            Person person = getItem(position);
            TextView nameTextView = (TextView) convertView.findViewById(
                    R.id.list_item_person_name_text_view);
            nameTextView.setText(person.getName());
            TextView numberTextView = (TextView) convertView.findViewById(
                    R.id.list_item_person_number_text_view);
            numberTextView.setText(person.getNumber());

            return convertView;
        }
    }

}
