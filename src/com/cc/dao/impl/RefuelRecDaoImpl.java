package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IRefuelRecDao;
import com.cc.po.RefuelRec;

@Repository("Refuel")
public class RefuelRecDaoImpl extends BaseDaoImpl<RefuelRec,Integer> implements IRefuelRecDao {

}
