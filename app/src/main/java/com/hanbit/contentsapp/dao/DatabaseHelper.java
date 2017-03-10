package com.hanbit.contentsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hanbit.contentsapp.domain.MemberBean;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME="hanbit.db";
    final static Integer DATABASE_VERSION=1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
        this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE IF NOT EXISTS Member(\n" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name TEXT,\n" +
                "phone TEXT,\n" +
                "age TEXT,\n" +
                "address TEXT,\n" +
                "salary TEXT \n" +
               ");";
        db.execSQL(sql);

      /*  for(int i=0;i<10;i++){
            db.execSQL(String.format("INSERT INTO Member(name,phone,age,address,salary) VALUES ('%s','%s','%s','%s','%s');", "홍길동"+i,"010-0000-0000"+i,"2"+i,"서울",(i+1)+"00"));
        }*/

        /*더미값을 너어준다 */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Member");
        onCreate(db);
    }
    public void insert(MemberBean bean){

    }
    public MemberBean selectOne(MemberBean bean){
        MemberBean member=new MemberBean();
        return member;
    }
    public List<MemberBean> selectSome(MemberBean bean){
        List<MemberBean>list=new ArrayList<MemberBean>();
        return list;
    }
    public List<MemberBean> selectAll(MemberBean bean){
        List<MemberBean>list=new ArrayList<MemberBean>();
        return list;
    }
    public void update(MemberBean bean){

    }
    public void delete(MemberBean bean){}


}
