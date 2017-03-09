package com.hanbit.contentsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contentsapp.R;

public class MemberUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        findViewById(R.id.btGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberUpdateActivity.this,"리스트로 가기",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MemberUpdateActivity.this,MemberListActivity.class));
            }
        });
    }
}
