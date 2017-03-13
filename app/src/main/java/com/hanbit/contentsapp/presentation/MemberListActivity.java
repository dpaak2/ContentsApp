package com.hanbit.contentsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
        ListView mList= (ListView) findViewById(R.id.mList);
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
    class MemberAdapter extends BaseAdapter{
        ArrayList<?>list; /*text와 image를 한공간에 조합해서 만드는것*/
       /* Context context; *//*getSeveletPath를 쓰지않으려고 한다*/
        LayoutInflater inflater; /*이곳에서 풍선불 장소,주소값을 가지고 있기 때문에 Context를 주지 않아도 됨 */

        public MemberAdapter(ArrayList<?> list, Context context) {
            this.list = list;
            this.inflater=LayoutInflater.from(context); /*?*/
        }

        private int[] photos={ /*무게가 무거우니깐 인트값으로 바꿔서 가지고 온다 , int가 가볍다*/
                R.drawable.cupcake,/*이름은 확장자를 뺀이름으로 가기때문에 되도록이면 jpg로 저장해라*/
                R.drawable.donut,
                R.drawable.froyo,
                R.drawable.gingerbread,
                R.drawable.honeycomb,
                R.drawable.icecream,
                R.drawable.jellybean,
                R.drawable.kitkat,
                R.drawable.lollipop,
        };
        public MemberAdapter(){

        }

        @Override  /*자동으로 만들어야 할것들*/
        public int getCount() {

            return list.size();
        }  /*list's size*/

        @Override
        public Object getItem(int i) {

            return list.get(i);
        }

        @Override
        public long getItemId(int i) {

            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup g) { /*adapter는 자체에서 만들어서 내보내는 serviceImpl과는 다르다*/
            ViewHolder holder;
            if(v==null){
                v=inflater.inflate(R.layout.member_item,null); /*R=res, 객체 하나 던질꺼야!*/
                holder=new ViewHolder();
                holder.profileImg= (ImageView) v.findViewById(R.id.profileImg);
                holder.tvName= (TextView) v.findViewById(R.id.tvName);
                holder.tvPhone= (TextView) v.findViewById(R.id.tvPhone);
            }else{
                holder= (ViewHolder) v.getTag(); /*in holder contains something, item을 완성해야함*/
            }
            holder.profileImg.setImageResource(photos[i]);
            holder.tvName.setText(((MemberBean)list.get(i)).getName());
            holder.tvPhone.setText(((MemberBean) list.get(i)).getName());

            return v; /*외부에서 받은것을 retrun해주는것이 adapter*/
         }

       }
    static class ViewHolder{
        ImageView profileImg;
        TextView tvName;
        TextView tvPhone;
        }
    }


