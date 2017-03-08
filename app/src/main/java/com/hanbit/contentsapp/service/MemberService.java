package com.hanbit.contentsapp.service;

import com.hanbit.contentsapp.domain.MemberBean;

import java.util.List;

/**
 * Created by hb2007 on 2017-03-08.
 */

public interface MemberService {
    public void  add(MemberBean bean);
    public MemberBean findOne(MemberBean bean);
    public List<MemberBean> findSome(MemberBean bean);
    public List<MemberBean> list(MemberBean bean);
    public void update(MemberBean bean);
    public void delete(MemberBean bean);
}
