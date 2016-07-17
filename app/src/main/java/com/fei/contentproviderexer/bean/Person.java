package com.fei.contentproviderexer.bean;

/**
 * Created by lee on 2016/7/17.
 */
public class Person {
    private int mId;
    private String mName;
    private String mNumber;

    public Person(String name, String number) {
        mName = name;
        mNumber = number;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
