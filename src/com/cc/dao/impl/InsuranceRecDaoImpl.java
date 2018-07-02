package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IInsuranceRecDao;
import com.cc.po.InsuranceRec;
@Repository("Insurance")
public class InsuranceRecDaoImpl extends BaseDaoImpl<InsuranceRec,Integer> implements IInsuranceRecDao {
	
}
