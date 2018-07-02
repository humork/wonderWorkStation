package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.ICurrentUnitDao;
import com.cc.po.CurrentUnit;
@Repository("Current")
public class CurrentUnitDaoImpl extends BaseDaoImpl<CurrentUnit, Integer> implements ICurrentUnitDao {

}
