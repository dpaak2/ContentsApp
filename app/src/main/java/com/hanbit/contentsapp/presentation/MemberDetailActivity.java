package com.hanbit.contentsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.contentsapp.R;
import com.hanbit.contentsapp.dao.DetailQuery;
import com.hanbit.contentsapp.domain.MemberBean;
import com.hanbit.contentsapp.service.DetailService;

import java.util.HashMap;
import java.util.Map;

public class MemberDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        Intent intent = this.getIntent();
        final MemberDetail mDetail=new MemberDetail(this);
        final String id = getIntent().getExtras().getString("id"); /*getExtras() list받은 아이디로 */
        MemberBean member=new MemberBean();
        Map<String,String>map= new HashMap<>();
        map.put("id",id);
        Toast.makeText(MemberDetailActivity.this,map.get("id"),Toast.LENGTH_LONG).show();
        DetailService service = new DetailService() {
            @Override
            public Object findOne(Map<?, ?> map) {
                MemberBean temp=(MemberBean)mDetail.findOne(
                        "SELECT _id AS id,name phone,age,address,salary"+
                                "FROM Member WHERE -id = '"+map.get("id")+"';");
                return temp;
            }
        };

        member=(MemberBean)service.findOne(map);
        Toast.makeText(MemberDetailActivity.this,member.getName(),Toast.LENGTH_LONG).show();
        TextView tvId= (TextView) findViewById(R.id.tvId);
        tvId.setText(member.getId());
        TextView tvName= (TextView) findViewById(R.id.tvName);
        tvName.setText(member.getName());
        TextView tvAge= (TextView) findViewById(R.id.tvAge);
        tvAge.setText(member.getAge());
        TextView tvPhone= (TextView) findViewById(R.id.tvPhone);
        tvPhone.setText(member.getPhone());
        TextView tvAddress= (TextView) findViewById(R.id.tvAddress);
        tvAddress.setText(member.getAddress());
        TextView tvSalary= (TextView) findViewById(R.id.tvSalary);
        tvSalary.setText(member.getSalary());
        findViewById(R.id.btGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberDetailActivity.this, " ID IS " + id, Toast.LENGTH_LONG).show();

                /*startActivity(new Intent(MemberDetailActivity.this,MemberUpdateActivity.class));*/
            }
        });
    }

    class MemberDetail extends DetailQuery {
        public MemberDetail(Context context) {
            super(context);
        }

        @Override
        public Object findOne(String sql) {
            MemberBean bean = null;
            SQLiteDatabase db = super.getDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                        bean = new MemberBean();
                        bean.setId(cursor.getString(cursor.getColumnIndex("id")));
                        bean.setName(cursor.getString(cursor.getColumnIndex("name")));
                        bean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                        bean.setAge(cursor.getString(cursor.getColumnIndex("age")));
                        bean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        bean.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                    }
                }
                return bean;
            }
        }
    }
