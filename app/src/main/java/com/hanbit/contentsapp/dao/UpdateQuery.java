package com.hanbit.contentsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class UpdateQuery extends QueryFactory{
    SQLiteOpenHelper helper;
    public UpdateQuery(Context context) {
        super(context);
        helper=new DatabaseHelper(context);
    }
    @Override
    public SQLiteDatabase getDatabase(){
        return helper.getReadableDatabase();
    }
    public abstract Object findOne(String sql);
}
