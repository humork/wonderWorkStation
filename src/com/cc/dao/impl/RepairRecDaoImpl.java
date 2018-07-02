package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IRepairRecDao;
import com.cc.po.RepairRec;
@Repository("Repair")
public class RepairRecDaoImpl extends BaseDaoImpl<RepairRec,Integer> implements IRepairRecDao {
	
}
