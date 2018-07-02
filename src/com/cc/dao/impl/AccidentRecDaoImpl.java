package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IAccidentRecDao;
import com.cc.po.AccidentRec;
@Repository("rec")
public class AccidentRecDaoImpl	extends BaseDaoImpl<AccidentRec,Integer> implements IAccidentRecDao {
	
}
