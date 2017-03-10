package com.hanbit.contentsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hb2007 on 2017-03-10.
 */

public abstract class DetailQuery extends QueryFactory {
    SQLiteOpenHelper helper;
    public DetailQuery(Context context){
        super(context);
    }

    @Override
    public SQLiteDatabase getDatabase() {
        return helper.getReadableDatabase();
    }
    public abstract Object detail(String sql);
}
