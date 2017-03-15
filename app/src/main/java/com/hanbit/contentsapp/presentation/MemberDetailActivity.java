package com.hanbit.contentsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.contentsapp.R;
import com.hanbit.contentsapp.dao.DetailQuery;
import com.hanbit.contentsapp.domain.MemberBean;
import com.hanbit.contentsapp.service.DetailService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        Context context = MemberDetailActivity.this;
        List<Button> buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.btDial));
        buttons.add((Button) findViewById(R.id.btUpdate));
        final Intent intent = this.getIntent();
        final MemberDetail mDetail = new MemberDetail(this);
        final String id = getIntent().getExtras().getString("id"); /*getExtras() list받은 아이디로 */
        final MemberBean member;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        Toast.makeText(MemberDetailActivity.this, map.get("id"), Toast.LENGTH_LONG).show();
        DetailService service = new DetailService() {
            @Override
            public Object findOne(Map<?, ?> map) {
                MemberBean temp = (MemberBean) mDetail.findOne(
                        "SELECT _id AS id,name,phone,age,address,salary FROM Member WHERE _id = '" + map.get("id") + "';");
                return temp;
            }
        };

        member = (MemberBean) service.findOne(map);
        Toast.makeText(MemberDetailActivity.this, member.getName(), Toast.LENGTH_LONG).show();
        TextView tvId = (TextView) findViewById(R.id.tvId);
        tvId.setText(member.getId());
        TextView tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(member.getName());
        TextView tvAge = (TextView) findViewById(R.id.tvAge);
        tvAge.setText(member.getAge());
        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvPhone.setText(member.getPhone());
        TextView tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvAddress.setText(member.getAddress());
        TextView tvSalary = (TextView) findViewById(R.id.tvSalary);
        tvSalary.setText(member.getSalary());

        /*고정으로 되는 문법*/
        new ButtonObserver(context, buttons, map).onClick(findViewById(android.R.id.content));

        /*----- obsever pattern---------*/
        /*map.clear();*/
        map.put("phoneNo", member.getPhone());



   /*     findViewById(R.id.btDial).setOnClickListener(new View.OnClickListener() { *//*button에 대한 객체를 만들었는지*//*
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberDetailActivity.this, " ID IS " + id, Toast.LENGTH_LONG).show();
                Intent intent =new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+member.getPhone()));
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


                *//*startActivity(new Intent(MemberDetailActivity.this,MemberUpdateActivity.class));*//*
            }
        });*/
  /*      findViewById(R.id.btCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + member.getPhone())));
                if (ActivityCompat.checkSelfPermission(MemberDetailActivity.this, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MemberDetailActivity.this, new String[]{
                            android.Manifest.permission.CALL_PHONE
                    }, 2);
                }
            }
        });*/
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

    class ButtonObserver implements View.OnClickListener {
        Context context;
        Map<String, String> map;
        List<Button> buttons;

        public ButtonObserver(Context context, List<Button> buttons, Map<?, ?> map) {
            this.context = context;
            this.buttons = buttons;
            this.map = (Map<String, String>) map;
            for (Button b : buttons) {
                b.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.btDial:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + map.get("phoneNO")));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
                case R.id.btCall:
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + map.get("phoneNO")));
                    if (ActivityCompat.checkSelfPermission(MemberDetailActivity.this, android.Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MemberDetailActivity.this, new String[]{
                                android.Manifest.permission.CALL_PHONE
                        }, 2);
                    }
                    intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case R.id.btUpdate:
                    Toast.makeText(context, "UPDATE" + map.get("id"), Toast.LENGTH_LONG).show();
                    intent = new Intent(context, MemberUpdateActivity.class); /*시작 , 도착지*/
                    intent.putExtra("id", map.get("id"));
                    startActivity(intent);
                    break;
            }

        }
    }
}


