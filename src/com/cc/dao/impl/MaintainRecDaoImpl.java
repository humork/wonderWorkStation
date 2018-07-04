package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IMaintainRecDao;
import com.cc.po.MaintainRec;
//maintainDao
@Repository("Maintain")
public class MaintainRecDaoImpl extends BaseDaoImpl<MaintainRec,Integer> implements IMaintainRecDao{

}
