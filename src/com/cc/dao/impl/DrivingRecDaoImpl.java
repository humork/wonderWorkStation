package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IDrivingRecDao;
import com.cc.po.DrivingRec;

@Repository("Rec")
public class DrivingRecDaoImpl extends BaseDaoImpl<DrivingRec, Integer> implements IDrivingRecDao {

}
