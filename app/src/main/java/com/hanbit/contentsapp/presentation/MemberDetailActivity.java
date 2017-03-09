package com.hanbit.contentsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hanbit.contentsapp.R;

public class MemberDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        Intent intent=this.getIntent();
        final String id= getIntent().getExtras().getString("id");

        findViewById(R.id.btGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberDetailActivity.this," ID IS "+id,Toast.LENGTH_LONG).show();

                /*startActivity(new Intent(MemberDetailActivity.this,MemberUpdateActivity.class));*/
            }
        });
    }
}
