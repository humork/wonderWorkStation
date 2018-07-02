package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IDriverDao;
import com.cc.po.Driver;

@Repository("Driver")
public class DriverDaoImpl extends BaseDaoImpl<Driver, Integer> implements IDriverDao {

}
