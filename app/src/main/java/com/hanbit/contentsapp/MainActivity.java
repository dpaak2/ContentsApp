package com.hanbit.contentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contentsapp.dao.DatabaseHelper;
import com.hanbit.contentsapp.presentation.MemberListActivity;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { /*butotn 생성*/
                Toast.makeText(MainActivity.this,"리스트로 가기",Toast.LENGTH_LONG).show();
                DatabaseHelper helper=new DatabaseHelper(MainActivity.this); /*class name.this 내가 있는 여기 (위치를 말해준다)*/
                startActivity(new Intent(MainActivity.this, MemberListActivity.class));
            }
        });
    }
}