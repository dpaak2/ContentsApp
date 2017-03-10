package com.hanbit.contentsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contentsapp.R;
import com.hanbit.contentsapp.dao.ListQuery;
import com.hanbit.contentsapp.domain.MemberBean;
import com.hanbit.contentsapp.service.ListService;

import java.util.ArrayList;

public class MemberListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        final MemberBean member= new MemberBean();
        findViewById(R.id.btGo).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final MemberList mlist=new MemberList(MemberListActivity.this);
                ListService service=new ListService() {
                    @Override
                    public ArrayList<?> list() {
                        ArrayList<?>list= mlist.list("SELECT _id AS id,name,phone,age,address,salary FROM Member;");
                        return list;
                    }
                };
                ArrayList<?>list=service.list();
                    Toast.makeText(MemberListActivity.this, ((MemberBean)list.get(0)).getName(),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MemberListActivity.this,MemberDetailActivity.class);
                    intent.putExtra("id","");
                    startActivity(intent);
            }
        });

    }
    class MemberList extends ListQuery {
        public MemberList(Context context) {
            super(context);
        }
        @Override
        public ArrayList<MemberBean> list(String sql) {
            ArrayList<MemberBean> list=new ArrayList<>();
            SQLiteDatabase db=super.getDatabase();
            Cursor cursor=db.rawQuery(sql,null);
            MemberBean bean=null;
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        bean=new MemberBean();
                        bean.setId(cursor.getString(cursor.getColumnIndex("id")));
                        bean.setName(cursor.getString(cursor.getColumnIndex("name")));
                        bean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                        bean.setAge(cursor.getString(cursor.getColumnIndex("age")));
                        bean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        bean.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                        list.add(bean);
                    }while(cursor.moveToNext());

                }
            }
            return list;
        }
    }
}
