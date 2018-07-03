package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IDriverDao;
import com.cc.po.Driver;

/**
 * @Author ChenXiang
 * @Date 2018/6/30,10:57
 */
@Repository("Driver")
public class DriverDaoImpl extends BaseDaoImpl<Driver, Integer> implements IDriverDao {

}
