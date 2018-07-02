package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IFeesManagerDao;
import com.cc.po.FeesManager;
@Repository("Fees")
public class FeesManagerDaoImpl extends BaseDaoImpl<FeesManager,Integer> implements IFeesManagerDao {

}
