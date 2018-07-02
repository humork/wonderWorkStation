package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IInspectionRecDao;
import com.cc.po.InspectionRec;
@Repository("RecDao")
public class InspectionRecDaoImpl extends BaseDaoImpl<InspectionRec,Integer> implements IInspectionRecDao {
	
}
