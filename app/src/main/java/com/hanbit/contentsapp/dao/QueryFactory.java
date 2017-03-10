package com.hanbit.contentsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hb2007 on 2017-03-10.
 */

public abstract class QueryFactory {
    Context context;
    public  QueryFactory(Context context){
       this.context=context;
    }
    public abstract SQLiteDatabase getDatabase();
}
