package com.hanbit.contentsapp.dao;

import com.hanbit.contentsapp.domain.MemberBean;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class MemberDAO {
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
    public List<MemberBean> selectAll(MemberBean){
        List<MemberBean>list=new ArrayList<MemberBean>();
        return list;
    }
    public void update(MemberBean bean){

    }
    public void delete(MemberBean bean){}
}
