package com.hanbit.contentsapp.serviceImpl;

import com.hanbit.contentsapp.domain.MemberBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb2007 on 2017-03-08.
 */

public class MemberServiceImpl implements MemberService {
MemberBean bean=new MemberBean();
    MemberService service=new MemberServiceImpl();
    @Override
    public void add(MemberBean bean) {

    }

    @Override
    public MemberBean findOne(MemberBean bean) {
        MemberBean member =new MemberBean();

        return member;
    }

    @Override
    public List<MemberBean> findSome(MemberBean bean) {
        List<MemberBean> list=new ArrayList<MemberBean>();
        return list;
    }

    @Override
    public List<MemberBean> list(MemberBean bean) {
        List<MemberBean> list=new ArrayList<MemberBean>();
        return list;
    }

    @Override
    public void update(MemberBean bean) {

    }

    @Override
    public void delete(MemberBean bean) {
    }
}
